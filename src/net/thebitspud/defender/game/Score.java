package net.thebitspud.defender.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//keeps track of game stats and history

public class Score {
	private long points, lastScore, highScore;
	private String dir;
	
	public Score(int startPoints) {
		points = startPoints;
	}
	
	public void save(String path) {
		//setting up the file path
		
		dir = System.getProperty("user.dir");
		path = dir + "/info/" + path + ".txt";
		
		try {
			//setting up the buffered writer
			
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//writing the file
			
			bw.append(points + "");
			bw.newLine();
			
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHighScore(String path) {
		//setting up the file path
		
		dir = System.getProperty("user.dir");
		path = dir + "/info/" + path + ".txt";
		
		try {
			//setting up the buffered reader
					
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
					
			//doing the reading
					
			String line = ""; long score;
			while((line = br.readLine()) != null) {
				if(line.equals("")) line = "0";
				if((score = Integer.parseInt(line)) > highScore) highScore = score;
			}

			br.close(); //breaking the connection to the file
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getHighScore() {
		return highScore;
	}
	
	public void reset() {
		this.points = 0;
	}

	//getters and setters
	
	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points += points;
	}
	
	public long getLastScore() {
		return lastScore;
	}

	public void setLastScore() {
		this.lastScore = points;
	}
}