package ta2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTest {
	ZipFile zipFile;
	Enumeration<? extends ZipEntry> entries;
	
	public static void main(String[] args) throws IOException{
		String test = "text/1.txt";
		System.out.println(test.matches("text\\/.*\\.txt"));
		System.out.println(ZipReadMachine.getHowMany("/Users/nasirho/Desktop/default/default.zip"));
		System.out.println(ZipReadMachine.isSafe("/Users/nasirho/Desktop/default/default.zip",10));
		ZipReadMachine a = new ZipReadMachine("/Users/nasirho/Desktop/default/default.zip");
		ArrayList<String > s = a.getQuestionArray("text/1.txt");
		for(String ss : s)
			System.out.println(ss);
		
	    
	    
	}
	
	public void go() throws IOException{
		zipFile = new ZipFile("/Users/nasirho/Desktop/default/default.zip");
		
	    entries = zipFile.entries();

	    ArrayList<InputStream> a = new ArrayList<InputStream>();
	    
	    while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        System.out.println(entry.getName());
	        if(entry.getName().equals("text/1.txt")){
	        	InputStream stream = zipFile.getInputStream(entry);
		        a.add(stream);
	        }
	        
	        
	    }
	    
	    System.out.println(a.size());
	    Scanner s = new Scanner(a.get(0));
        while(s.hasNextLine()){
        	System.out.println(s.nextLine());
        }
        
	}
	
}
