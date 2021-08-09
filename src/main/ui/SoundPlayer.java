package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    private static final String CLICK_STORE = "./sound/click1.wav";
    private Clip click1;

    public SoundPlayer() {
        setFile(CLICK_STORE);
    }

    public void setFile(String path) {
        System.out.println(path);
        try {
            File file = new File(path);
            System.out.println(path);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                click1 = AudioSystem.getClip();
                click1.open(sound);
                click1.loop(click1.LOOP_CONTINUOUSLY);
                System.out.println("Sound loaded");
            } else {
                System.out.println("Can't find file1");
            }
        } catch (Exception e) {
            //
        }
    }

    public void play() {
//        click1.setFramePosition(0);
        click1.start();
        System.out.println("Sound played");
    }

}
