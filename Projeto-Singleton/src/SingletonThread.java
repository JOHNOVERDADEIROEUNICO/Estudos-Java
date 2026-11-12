public class SingletonThread {
    private static volatile SingletonThread instancia;
    private SingletonThread() {}

    public static SingletonThread getInstancia() {
        if (instancia == null) {
            synchronized (SingletonThread.class) {
                if (instancia == null) {
                    instancia = new SingletonThread();
                }
            }
        }
        return instancia;
    }
}
