public class CreditCardStrategy implements IPaymentStrategy {
     private String cardNumber;

    public CreditCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Pago R$ " + amount + " com Cartão de Crédito (" + cardNumber + ").");
    }
}
