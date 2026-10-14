public class MusicPlayer implements IMusicPlayer {
    @Override 
    public void playMusic(){
        System.err.println("dando play em: " + music);
    }
    @Override 
    public void pauseMusic(){
        System.err.println("dando pause em: " + music);
    }
    @Override 
    public void stopMusic(){
        System.err.println("parando de tocar: " + music);
    }
    
}
