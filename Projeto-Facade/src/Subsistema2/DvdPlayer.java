package Subsistema2;

public class DvdPlayer {
    private static DvdPlayer instancia = new DvdPlayer();

    private DvdPlayer(){
        super();
    }

    public static DvdPlayer getInstancia(){
        return instancia;
    }

    public void ligar() { 
        System.out.println("DVD ligado"); 
    }

    public void reproduzir(String filme) { 
        System.out.println("Reproduzindo: " + filme); 
    }
}
