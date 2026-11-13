package one.digitalinnovation.gof.service.impl;

import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.Exceptions.ClienteNaoEncontradoException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	
	private EnderecoRepository enderecoRepository;
	
	private ViaCepService viaCepService;

	//Removi o Autowired e coloquei no lugar injeção por construtor
	public ClienteServiceImpl(
		ClienteRepository clienteRepository,
		EnderecoRepository enderecoRepository,
		ViaCepService viaCepService){
			this.clienteRepository = clienteRepository;

			this.enderecoRepository = enderecoRepository;

			this.viaCepService = viaCepService;
		}
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Cliente> buscarTodos() {
		// Buscar todos os Clientes.
			return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// Buscar Cliente por ID.
		return clienteRepository.findById(id)
        	.orElseThrow(() ->
            	new ClienteNaoEncontradoException(id)
			);
		
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		return salvarClienteComCep(cliente);
	}

	@Override
	public Cliente atualizar(Long id, Cliente cliente) {
		// Buscar Cliente por ID, caso exista:
		Cliente clienteDb = clienteRepository.findById(id)
			.orElseThrow(() -> 
				new ClienteNaoEncontradoException(id)
			);

		clienteDb.setNome(cliente.getNome());
		clienteDb.setEndereco(cliente.getEndereco());
		
		return salvarClienteComCep(clienteDb);
	}

	@Override
	public void deletar(Long id) {
		// Deletar Cliente por ID.
		if(!clienteRepository.existsById(id))
			throw new ClienteNaoEncontradoException(id);

		clienteRepository.deleteById(id);
	}

	private Cliente salvarClienteComCep(Cliente cliente) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		if(cliente == null || cliente.getEndereco() == null){
			throw new IllegalArgumentException("O cliente e seu endereço são obrigatórios.");
		}

		String cep = cliente.getEndereco().getCep();

		if(cep == null || !cep.matches("\\d{8}")){
			throw new IllegalArgumentException("CEP inválido. Informe 8 dígitos");
		}

		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);

			if(novoEndereco == null){
				throw new IllegalArgumentException("CEP não encontrado");
			}

			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		return clienteRepository.save(cliente);
	}

}
