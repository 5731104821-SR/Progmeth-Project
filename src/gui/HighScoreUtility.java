package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class HighScoreUtility {

	public static class HighScoreRecord implements Comparable<HighScoreRecord> {
		private String name = "";
		private int score = 0;
		
		public HighScoreRecord(String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		public HighScoreRecord(String record) throws ScoreParsingException {
			int colon = record.indexOf(":");
			if(colon==-1) throw new ScoreParsingException(1);
			String playerName = record.substring(0, colon);
			String playerScore = record.substring(colon+1);
			try{
				score = Integer.parseInt(playerScore);
				name = playerName;
			}
			catch(NumberFormatException ne){
				throw new ScoreParsingException(0);
			}
		}
		
		private String getRecord(){
			String a = ":" + score;
			try {
				String recordName = name.trim()+":"+score;
				return recordName;
			}
			catch(NullPointerException e){
				return a;
			}
		}
		
		private static String[] defaultRecord(){
			return new String[]{"AtomicBoyZ:800","Angry_Bird:350","Kirby:300",
					"Mario:250","Superman:200","Nyan_Cat:100",
					"Flappy_Bird:40","Pedobear:30","Rilakkuma:20","Chocobo:10"};
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			return Integer.compare(o.score, this.score);
		}
	}
	
	private static HighScoreRecord[] highScoreRecord = null;

 	private static String readFileName = "highscore";
 	
	public static void recordHighScore(int score){
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null , "Error loading highscore record" , "Error" , JOptionPane.ERROR_MESSAGE);
			return;
		}
		int index=highScoreRecord.length;
		for(int i=0; i<highScoreRecord.length; i++){
			if(score > highScoreRecord[i].score){
				index = i;
				break;
			}
		}
		if(index >= highScoreRecord.length){
			JOptionPane.showMessageDialog(null , "Game over\nYour score is " + score , "Game over" , JOptionPane.INFORMATION_MESSAGE);
		}else{
			for(int i=highScoreRecord.length-1; i>=index+1; i--){
				highScoreRecord[i] = highScoreRecord[i-1];
			}
			String name = JOptionPane.showInputDialog(null, "Congratulation, you are ranked " + (index+1) + "\nPlease enter your name", "High score", JOptionPane.INFORMATION_MESSAGE);
			highScoreRecord[index] = new HighScoreRecord(name , score);
			try {
				
				BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
				String recordData = "";
				for(HighScoreRecord hsr : highScoreRecord){
					recordData += hsr.getRecord() + "\n";
				}
				out.write(getXORed(recordData));
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Error saving high score record", 
						"Error", JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}
	
	public static void displayTop10(){
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null , "Error loading highscore record" , "Error" , JOptionPane.ERROR_MESSAGE);
			return;
		}
		String msg = "======= Top 10 players ======="+System.getProperty("line.separator");
		int rank = 1;
		for(HighScoreRecord record : highScoreRecord){
			msg += rank+" "+record.getRecord()+System.getProperty("line.separator");
			rank++;
		}
		JOptionPane.showMessageDialog(null, msg.trim(), "Top 10", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static boolean loadHighScore(){
		File f = new File(readFileName);
		//If no high score, create default
		if(!f.exists()){
			if(!createDefaultScoreFile()) return false;
		}
		//Read high score -- if fail: delete the file, create default one and read it again 
		if(!readAndParseScoreFile(f)){
			f.delete();
			if(!createDefaultScoreFile()) return false;
			return readAndParseScoreFile(f);
		}
		return true;
		
	}
	
	private static boolean readAndParseScoreFile(File f){
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			highScoreRecord = new HighScoreRecord[10];
			String str = "";
			int c;
			while((c = in.read()) != -1){
				str += (char)c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for(int i=0; i<highScoreRecord.length; i++){
				try{
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				}catch(ScoreParsingException e){
					System.err.println("Error parsing line "+(i+1)+", "+e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}
	
	private static boolean createDefaultScoreFile(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for(String s : HighScoreRecord.defaultRecord()){
				str += s+"\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}
	
	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();
	
	//This method does both encryption and decryption 
	private static String getXORed(String in){
		byte[] inData = in.getBytes();
		for(int i = 0; i < inData.length ; i++){
			inData[i] ^= key[i%key.length];
		}
		return new String(inData);
	}

	public static void setReadFileName(String name){
		readFileName = name;
	}	
}

