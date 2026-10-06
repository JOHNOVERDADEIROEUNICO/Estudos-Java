import java.util.Scanner;
public class AppSwitchCase{
    public static void main(String[] args) throws Exception {
        //Coloque este codigo dentro do src principal caso queira testar
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Informe um numero de 1 a 7");
            var option = scanner.nextInt();

            if(option >= 1 && option <= 7){
                switch (option) {
                    case 1:
                        System.out.println("Domingo");
                        break;
                    case 2:
                        System.out.println("Segunda");
                        break;
                    case 3:
                        System.out.println("Terça");
                        break;
                    case 4:
                        System.out.println("Quarta");
                        break;
                    case 5:
                        System.out.println("Quinta");
                        break;
                    case 6:
                        System.out.println("Sexta");
                        break;
                    case 7:
                        System.out.println("Sábado");
                        break;
                
                    default:
                        break;
                }
            }
            else
                System.out.println("Digite um numero válido.");
        }
    }
}

