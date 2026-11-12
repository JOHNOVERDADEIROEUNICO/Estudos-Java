public class StrategyApp {
    public static void main(String[] args) throws Exception {
        ShoppingCart cart = new ShoppingCart();

        // Pagando com Cartão
        cart.setPaymentStrategy(new CreditCardStrategy("1234-5678-9012-3456"));
        cart.checkout(150);

        // Mudando a estratégia dinamicamente para PIX
        cart.setPaymentStrategy(new PixStrategy("suachave@pix.com"));
        cart.checkout(80);
    }
}
