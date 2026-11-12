public class SingletonClass {
    //Singleton preguiçoso
    private static SingletonClass instancia;

    private SingletonClass(){
        super();
    }

    public static SingletonClass getInstancia(){
        if(instancia == null)
            instancia = new SingletonClass();
        
        return instancia;
    }
}