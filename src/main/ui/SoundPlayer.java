package ui;

import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;
import java.io.*;


// represents a sound player

public class SoundPlayer {

//    void playMusic(String musicLocation) {
//        try {
//            File musicPath = new File(musicLocation);
//
//            if (musicPath.exists()) {
//                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInput);
//                clip.start();
//                clip.loop(Clip.LOOP_CONTINUOUSLY);
//
//            } else {
//                System.out.println("Can't find file");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//
//    }








//    public void soundActionPerformed(java.awt.event.ActionEvent evt) {
//        InputStream in;
//        try {
//            in = new FileInputStream(new File("C:\\Users\\saqib\\CPSC210Summerlabs\\quiz2partb\\sound\\click1.wav"));
//            AudioStream audios = new AudioStream(in);
//            AudioPlayer.player.start(audios);
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//
//        }
//
//    }
    private static final String CLICK_STORE = "./resources/click1.wav";
    Clip click1;


    // // EFFECTS: constructs a sound player
    public SoundPlayer() {
        setFile(CLICK_STORE);
    }





    // EFFECTS: sets the music playing functionality
    public void setFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                click1 = AudioSystem.getClip();
                click1.open(sound);
                System.out.println("Sound loaded");
            } else {
                System.out.println("Can't find file1");
            }
        } catch (Exception e) {
            //
        }
    }




    // EFFECTS: starts the music when a button (action) is clicked
    public void play() {
        click1.setFramePosition(0);
        click1.start();

        System.out.println("Sound played");
    }

}
