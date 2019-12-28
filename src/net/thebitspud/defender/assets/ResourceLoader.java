package net.thebitspud.defender.assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

//the class that allows loading resources from within a jar file

public class ResourceLoader {
	//loading images
	
    public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ResourceLoader.class.getResource("/textures/" + path + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
    }
    
    //loading the game display icons

    private ArrayList<Image> icons;
    
    public void loadIcons(JFrame f) { 	
    	icons = new ArrayList<Image>();

    	icons.add(loadImage("icons/icon 16x"));
    	icons.add(loadImage("icons/icon 32x"));
    	icons.add(loadImage("icons/icon 64x"));
    	
    	f.setIconImages(icons);
    }
    
    //loading sounds
    
    private AudioInputStream stream;
    private Clip clip, activeTrack;
    private String[] tracks;
    
    public void getAudio(String path, boolean repeat) {
    	loadAudio(clip, path, repeat);
    }
    
    public void loadAudio(Clip c, String path, boolean repeat) {
    	try {
			stream = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource("/audio/" + path + ".wav"));
			
			c = AudioSystem.getClip();
			c.open(stream);
			
			if(repeat) {
				c.loop(Clip.LOOP_CONTINUOUSLY);
				activeTrack = c;
			}
			
			c.start();
			
			Thread.sleep(1);
			
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void initTracks() {
    	tracks = new String[2];
    	
    	tracks[0] = "soundtracks/digital_ether";
    	tracks[1] = "soundtracks/space_battle";
    }
    
    public void playTrack(int index) {
    	if(activeTrack != null) activeTrack.close();
    	loadAudio(activeTrack, tracks[index], true);
    }

	public void resetAudio() {
		if(clip != null) clip.close();
		if(activeTrack != null) activeTrack.close();
	}
	
	public String[] getTracks() {
		return tracks;
	}
}
