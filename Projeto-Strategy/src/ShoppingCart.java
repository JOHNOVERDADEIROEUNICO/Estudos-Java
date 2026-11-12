public class ShoppingCart {
    private IPaymentStrategy paymentStrategy;

    // Permite definir ou trocar a estratégia
    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de pagamento definida!");
        }
        paymentStrategy.pay(amount);
    }
}
