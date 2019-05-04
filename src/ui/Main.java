package ui;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class Main extends JFrame {



    public static void main(String[] args) throws IOException{
        ToDoList todo = new ToDoList();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = null;
                try {
                    {   // cited from alvinalexander.com!! https://alvinalexander.com/java/java-audio-example-java-au-play-sound
                        // open the sound file as a Java input stream
                        String music = "/Users/daryus/Documents/CPSC 210/testing/src/song.wav";
                        InputStream in = new FileInputStream(music);

                        // create an audiostream from the inputstream
                        AudioStream audioStream = new AudioStream(in);

                        // play the audio clip with the audioplayer class
                        AudioPlayer.player.start(audioStream);

                        frame = new MainFrame("homework!", todo);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.setSize(700, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}