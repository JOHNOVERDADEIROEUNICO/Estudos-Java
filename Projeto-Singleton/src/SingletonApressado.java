public class SingletonApressado {
    //Singleton apressado
    private static SingletonApressado instancia = new SingletonApressado();

    private SingletonApressado(){
        super();
    }

    public static SingletonApressado getInstancia(){
        return instancia;
    }
}
