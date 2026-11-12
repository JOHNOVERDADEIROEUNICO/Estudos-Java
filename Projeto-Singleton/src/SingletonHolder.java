public class SingletonHolder {
    //LazyHolder
    private static class Holder{
        public static SingletonHolder instancia = new SingletonHolder();
    }

    private SingletonHolder(){
        super();
    }

    public static SingletonHolder getInstancia(){
        return Holder.instancia;
    }
}
