package ta2;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

public class ZipReadMachine extends ReadMachine{
	ZipFile zipFile;
	Enumeration<? extends ZipEntry> entries;
	
	public ZipReadMachine(String path){
		try{
			zipFile = new ZipFile(path);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public  ArrayList<String> getQuestionArray(String filePath){
		entries = zipFile.entries();
		ArrayList<String> strArray = new ArrayList<String>();
		InputStream stream = null;
		
		filePath = filePath.substring(1);
		
		while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        if(entry.getName().equals(filePath)){
	        	try{
	        		stream = zipFile.getInputStream(entry);
	        		Scanner input = new Scanner(stream);
	        		while(input.hasNextLine()){
	        			strArray.add(input.nextLine());
	        		}
	        		input.close();
	        	}catch(IOException e){
	        		e.printStackTrace();
	        		stream = this.getClass().getResourceAsStream(filePath);
	        	}finally{
	        		Scanner input = new Scanner(stream);
	        		while(input.hasNextLine()){
	        			strArray.add(input.nextLine());
	        		}
	        		input.close();
	        	}
	        }
	    }
		return strArray;
	}
	
	public Image getQuestionImage(String filePath){
		entries = zipFile.entries();
		InputStream stream = null;
		Image image = null;
		
		filePath = filePath.substring(1);
		
		try{
			image = ImageIO.read(QuestionPad.class.getResourceAsStream("/image/default.png"));
		}catch(Exception exc){
			System.err.println("default cannot find!");
		}
		
		while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        if(entry.getName().equals(filePath)){
	        	try{
	        		stream = zipFile.getInputStream(entry);
	        		image = ImageIO.read(stream);
	        	}catch(IOException e){
	        		e.printStackTrace();
	        		System.err.println("Cannot find picture! Use default...");
	        		
	        	}
	        }
	    }
		
		return image;
	}
	
	public static int getHowMany(String path){
		int i = 0;
		ArrayList<InputStream> a = new ArrayList<InputStream>();
		
		try{
			ZipFile zipFile = new ZipFile(path);
			Enumeration<? extends ZipEntry> entries= zipFile.entries();
			while(entries.hasMoreElements()){
		        ZipEntry entry = entries.nextElement();
		        try{
		        	InputStream stream = zipFile.getInputStream(entry);
		        	a.add(stream);
		        }catch(IOException ex){
		        	ex.printStackTrace();
		        }
			    
			}
			zipFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		i = a.size();
		
		return i;
	}
	
	public static boolean isSafe(String path , int questionNumber){
		boolean safe = true;
		InputStream stream = null;
		int howManyText = 0;
		
		try{
			ZipFile zipFile = new ZipFile(path);
			Enumeration<? extends ZipEntry> entries= zipFile.entries();
			while(entries.hasMoreElements()){
				ZipEntry entry = entries.nextElement();
				if(entry.getName().matches("text\\/.*\\.txt")){
					for(int i = 1; i <= questionNumber; i++){
						if(entry.getName().equals("text/"+i+".txt")){
							ArrayList<String> strArray= new ArrayList<String>();
							try{
								stream = zipFile.getInputStream(entry);
								Scanner input = new Scanner(stream);
								while(input.hasNextLine()){
									strArray.add(input.nextLine());
									
								}
								if(strArray.size()<6){
									safe = false;
								}
								input.close();
							}catch(Exception ex){
								safe = false;
							}
							howManyText ++;
						}
					}
				}
			}
			
			zipFile.close();
		}catch(IOException e){
			e.printStackTrace();
			safe = false;
		}
		if(howManyText != questionNumber)
			safe = false;
		return safe;
	}
	
	
}
