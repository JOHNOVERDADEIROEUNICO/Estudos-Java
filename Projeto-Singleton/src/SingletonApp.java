public class SingletonApp {
    public static void main(String[] args) throws Exception {
        SingletonClass lazy = SingletonClass.getInstancia();

        System.out.println(lazy);

        lazy = SingletonClass.getInstancia();

        System.out.println(lazy);


        SingletonApressado apressado = SingletonApressado.getInstancia();

        System.out.println(apressado);
        

        SingletonHolder holder = SingletonHolder.getInstancia();

        System.out.println(holder);
    }
}
