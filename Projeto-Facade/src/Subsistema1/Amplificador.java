package Subsistema1;

public class Amplificador {
    private static Amplificador instancia = new Amplificador();

    private Amplificador(){
        super();
    }

    public static Amplificador getInstancia(){
        return instancia;
    }

    public void ligar() { 
        System.out.println("Amplificador ligado"); 
    }

    public void setVolume(int v) { 
        System.out.println("Volume em " + v); 
    }
}