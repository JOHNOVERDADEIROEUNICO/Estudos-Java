import java.util.Scanner;

public class AppIfElse {
    public static void main(String[] args) throws Exception {
        try(Scanner scanner = new Scanner(System.in)){
            /*Com o try logo acima evitamos a necessidade do uso de um scanner.close ao fim do seu uso. */
            //var scanner = new Scanner(System.in);

            System.out.println("Informe seu nome: ");
            var name = scanner.next();

            System.out.println("Informe sua idade: ");
            var age = scanner.nextInt();

            if(age >= 18 && age < 62)
                System.err.printf("%s tem %s e já é maior de idade.\n", name, age);
            else if(age >= 62)
                System.err.printf("%s tem %s e já pode se aposentar.\n", name, age);
            else
                System.err.printf("%s tem %s e é menor de idade.\n", name, age);

        // scanner.close();
        }
    }
}
