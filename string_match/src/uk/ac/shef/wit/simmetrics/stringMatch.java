package uk.ac.shef.wit.simmetrics;

import java.io.*;
import java.util.ArrayList;
//import uk.ac.shef.wit.simmetrics.similaritymetrics.*;

public class stringMatch {
	static ArrayList<String> zagot = new ArrayList<String>();
	static ArrayList<String> fodors = new ArrayList<String>();
	
	public static void readFile1(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String tmpStr = line.substring(line.indexOf("---")+3);
			zagot.add(tmpStr);
		}
		br.close();
	}
	
	public static void readFile2(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String tmpStr = line.substring(line.indexOf("---")+3);
			fodors.add(tmpStr);
		}
		br.close();
	}
	
	public static void main(String[] args)throws IOException {
		
		System.out.println("**********Zagot.txt*********");
		File fin1 = new File("src/InputFiles/zagat.txt");
		readFile1(fin1);
		
		System.out.println("\n\n\n\n**********Fodors.txt*********");
		File fin2 = new File("src/InputFiles/fodors.txt");
		readFile2(fin2);
		
		
		for(String s: zagot)
			System.out.println(s);
		
		for(String s: fodors)
			System.out.println(s);
		
	}
}
