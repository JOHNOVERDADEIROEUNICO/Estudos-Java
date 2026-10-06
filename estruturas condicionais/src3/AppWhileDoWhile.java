import java.util.Scanner;

public class AppWhileDoWhile {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            var name = "";
            while(!name.equals("exit")){
                System.out.println("Informe um nome (digite exit para sair): ");
                scanner.next();

                System.out.println(name);
            }

            do{
                System.out.println("Informe um nome (digite exit para sair): ");
                scanner.next();

                System.out.println(name);
            }while(!name.equals("exit"));
        }
    }
}
