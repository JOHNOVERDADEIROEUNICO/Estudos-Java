public class PixStrategy implements IPaymentStrategy{
    private String chavePix;

    public PixStrategy(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Pago R$ " + amount + " via PIX (" + chavePix + ").");
    }
}
