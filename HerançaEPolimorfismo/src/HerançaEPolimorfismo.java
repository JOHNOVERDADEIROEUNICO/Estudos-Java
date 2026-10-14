import java.text.DecimalFormat;

public class HerançaEPolimorfismo {
    public static void main(String[] args) throws Exception {
        DecimalFormat df = new DecimalFormat("0.00"); // Define 2 casas decimais fixas
        var empregado = new Employee();
        var gerente = new Manager();

        empregado.setName("Carlos");
        empregado.setSalary(1800);

        System.out.println(empregado.getName());
        System.out.println(empregado.getSalary());

        gerente.setName("João");
        gerente.setLogin("@John");
        gerente.setPassword("1234");
        gerente.setSalary(5, 3000);

        System.out.println(gerente.getName());
        System.out.println(gerente.getLogin());
        System.out.println(gerente.getPassword());
        System.out.println(df.format(gerente.getSalary()));



    }
}
