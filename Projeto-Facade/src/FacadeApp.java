import Facade.HomeTheaterFacade;

public class FacadeApp {
    public static void main(String[] args) throws Exception {
        // O cliente só interage com a fachada
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        
        homeTheater.assistirFilme("Matrix");
    }
}
