package Facade;

import Subsistema1.Amplificador;
import Subsistema2.DvdPlayer;
import Subsistema3.Projetor;

public class HomeTheaterFacade {
    private Amplificador amplificador;
    private DvdPlayer dvd;
    private Projetor projetor;

    public HomeTheaterFacade() {
        this.amplificador = Amplificador.getInstancia();
        this.dvd = DvdPlayer.getInstancia();
        this.projetor = Projetor.getInstancia();
    }

    // Método simplificado que coordena todo o subsistema
    public void assistirFilme(String filme) {
        System.out.println("Preparando para assistir filme...");
        amplificador.ligar();
        amplificador.setVolume(10);
        projetor.ligar();
        projetor.wideScreenMode();
        dvd.ligar();
        dvd.reproduzir(filme);
    }
}
