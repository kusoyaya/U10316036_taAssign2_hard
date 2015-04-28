package ta2;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ReadMachine {
	private File file = null;
	
	
	public ArrayList<String> getQuestionArray(String filePath){
		file = new File(filePath);
		ArrayList<String> strArray = new ArrayList<String>();
		try {
			Scanner input = new Scanner(file);
			while(input.hasNextLine()){
				strArray.add(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			for(int i=0; i<5;i++){
				strArray.add("error");
			}
		}
		return strArray;
	}
	
	public Image getQuestionImage(String filePath){
		Image image = null;
		try{
			image = ImageIO.read(new File(filePath));
		}catch(Exception ex){
			try{
				image = ImageIO.read(new File("question image/default.png"));
			}catch(Exception exc){
				
			}
		}
		return image;
	}
	
}
