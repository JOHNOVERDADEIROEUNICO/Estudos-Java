public class AppBitwise {
    public static void main(String[] args) throws Exception {
        var value1 = 6;
        var binary1 = Integer.toBinaryString(value1);

        System.out.printf("Primeiro número da operação %s (representação binária %s)\n", value1, binary1);

        var value2 = 5;
        var binary2 = Integer.toBinaryString(value2);

        System.out.printf("Segundo número da operação %s (representação binária %s)\n", value2, binary2);

        //Or bitwise
        var result = value1 | value2;
        var binaryResult = Integer.toBinaryString(result);

        System.out.printf("Or bitwise: %s | %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);

        result = value1 & value2;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("And bitwise: %s & %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);

        result = value1 ^ value2;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("XOR bitwise: %s ^ %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);

        result = ~value1;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("NOT bitwise: ~%s = %s (Representação binária %s)\n", value1, result, binaryResult);

        result = value1 << value2;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("Deslocamento a esquerda bitwise: %s << %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);

        result = value1 >> value2;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("Deslocamento a direita com sinal bitwise: %s >> %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);

        result = value1 >>> value2;
        binaryResult = Integer.toBinaryString(result);

        System.out.printf("Deslocamento a direita sem sinal bitwise: %s >>> %s = %s (Representação binária %s)\n", value1, value2, result, binaryResult);
    }

    /*
    (true = 1 / false = 0)

    (Quando o primeiro numero a esquerda for falso ele não retorna 0, retorna com se aquela casa não existisse mais. Isso ocorre devido a valores bitwise trabalharem da esquerda pra direita com valores, devido a isso por trás dos panos temos 000000000110 por exemplo, por convenção para não mostrar esse monte de 0 os valores bitwise apagam os 0 a esquerda do primeiro 1)

    Or bitwise
    0 = false
    1 = true
    Ex: 110 | 101 = 111

    AND bitwise
    1 & 1 = true
    0 & 0 = false
    1 & 0 =  false
    Ex: 110 & 101 = 100
    
    XOR bitwise
    1 ^ 1 = false
    0 ^ 0 = false
    1 ^ 0 = true
    Ex: 110 ^ 101 = 11

    NOT bitwise
    Inverte 0 e 1, diferente dos outros operadores bitwise, o NOT só trabalha com um valor.
    Ex: ~110 = 11111111111111111111111111111001

    Deslocamento a esquerda
    empurra o numero para a esquerda x vezes, cada vez que o numero é empurrado a esquerda um 0 é adicionado e o numero é multiplicado por 2
    Ex: 6 << 5 ou em binario 110 << 101 = 192 ou em binario 11000000, perceba que o 110 foi empurrado pra esquerda 5 vezes e multipicado por 2 5 vezes, ou seja o binario de 5 aqui é ignorado, mas o valor numerico de 5 é considerado tanto para a multiplicação quanto para os valores binarios.

    Deslocamento com sinal a direita (>>)
    Faz a mesma coisa porém empurra os numeros para a direita, só que com um detalhe se o numero for negativo os numeros a esquerda serão completados com 1 e se o numero for positivo os numeros  a esquerda serão completados por 0.

    Deslocamento sem sinal a direita (>>>)
    A diferença para o anterior é que sempre será 0 a esquerda independente do sinal original do numero ou seja, ele não preserva o sinal.
    */

}
