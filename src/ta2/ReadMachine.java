package ta2;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class ReadMachine {
	
	public static void main(String[] args){
		ReadMachine a = new ReadMachine();
		
		
	}
	
	public  ArrayList<String> getQuestionArray(String filePath){
		ArrayList<String> strArray = new ArrayList<String>();
		InputStream is = this.getClass().getResourceAsStream(filePath);
		Scanner input = new Scanner(is);
		while(input.hasNextLine()){
			strArray.add(input.nextLine());
		}
		input.close();
		return strArray;
	}
	
	public Image getQuestionImage(String filePath){
		Image image = null;
		try{
			image = ImageIO.read(QuestionPad.class.getResourceAsStream(filePath));
		}catch(Exception ex){
			try{
				image = ImageIO.read(QuestionPad.class.getResourceAsStream("/image/default.png"));
			}catch(Exception exc){
				System.err.println("default cannot find!");
			}
			System.err.println("Cannot find picture! Use default...");
		}
		return image;
	}
	
	public void playSound(boolean rightOrWrong){
		String s = "/wrong.wav";
		if(rightOrWrong)
			s = "/correct.wav";
		try{
			AudioStream as = new AudioStream(QuestionPad.class.getResourceAsStream(s));         
            AudioPlayer.player.start(as);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
}
