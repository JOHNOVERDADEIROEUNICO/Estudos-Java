public class InterfaceApp {
    public static void main(String[] args) throws Exception {
        var music = new MusicPlayer();

        music.playMusic();

        music.pauseMusic();

        music.playMusic();

        music.stopMusic();
    }
}
