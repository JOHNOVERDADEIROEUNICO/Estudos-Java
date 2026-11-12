package Subsistema3;

public class Projetor {
    private static Projetor instancia = new Projetor();

    private Projetor(){
        super();
    }

    public static Projetor getInstancia(){
        return instancia;
    }

    public void ligar() { 
        System.out.println("Projetor ligado"); 
    }
    
    public void wideScreenMode() { 
        System.out.println("Modo Widescreen ativado"); 
    }
}
