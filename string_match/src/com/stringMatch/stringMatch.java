//package com.stringMatch;
//package uk.ac.shef.wit.simmetrics;

import java.io.*;
import java.util.ArrayList;
import uk.ac.shef.wit.simmetrics.similaritymetrics.*;

public class stringMatch {
	static ArrayList<String> zagot = new ArrayList<String>();
	static ArrayList<String> fodors = new ArrayList<String>();
	
	static int[] zagotLine = new int[1000];
	static int[] fodorsLine = new int[1000];
	


	
	public static void readFile1(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		int i=0;
		while ((line = br.readLine()) != null) {
			String[] lineNum = line.split("-");
			zagotLine[++i] = Integer.parseInt(lineNum[0]);
			
			String tmpStr = line.substring(line.indexOf("---")+3);
			zagot.add(tmpStr);
		}
		br.close();
	}
	
	public static void readFile2(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		int i= 0;
		while ((line = br.readLine()) != null) {
			String[] lineNum = line.split("-");
			fodorsLine[++i] = Integer.parseInt(lineNum[0]);
			
			String tmpStr = line.substring(line.indexOf("---")+3);
			fodors.add(tmpStr);
		}
		br.close();
	}
	
	public static void similarityOutput(String filename1, String filename2, String outFile){
        int i=0;
        int j=0;
        int maxIndex = 0;;
        float maxValue = -1;
        String s = "";
        String s1="";
        String s2="";
        
        AbstractStringMetric jaro = new JaroWinkler();
        AbstractStringMetric lev = new Levenshtein();
        AbstractStringMetric smith = new SmithWaterman();
        AbstractStringMetric sound = new Soundex();
        AbstractStringMetric jac = new JaccardSimilarity();
        AbstractStringMetric needle = new NeedlemanWunch();
        
		try {
			String filename= outFile;
			FileWriter fw = new FileWriter(filename,false);
        
	        for(String f1: zagot){
	        	i++;
	        	maxValue = -1;
	        	j =0;
	        	for(String f2:fodors) {
	        		j++;
	        		float resultJaro = jaro.getSimilarity(f1, f2);
	        		float resultSmith = smith.getSimilarity(f1, f2);
	        		float resultNeedle = needle.getSimilarity(f1, f2);
	        		
	        		if(resultJaro > maxValue) {
	        			maxIndex = j;
	        			maxValue = resultJaro;
	        			s = "Jaro";
	        			s1 = f1;
	        			s2 = f2;
	        		}
	        		if(resultSmith > maxValue) {
	        			maxIndex = j;
	        			maxValue = resultSmith;
	        			s = "Smith";
	        			s1 = f1;
	        			s2 = f2;
	        		}
	        		if(resultNeedle > maxValue) {
	        			maxIndex = j;
	        			maxValue = resultNeedle;
	        			s = "Needle";
	        			s1 = f1;
	        			s2 = f2;
	        		}
	        		
	        	}
	
	        	if(maxValue > 0.88){
	        	    fw.write(filename1+":"+ zagotLine[i]+ "\t"+filename2+":"+ fodorsLine[maxIndex]+"\n");
	        	}
	        }
	        fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}

	}
	
	public static void main(String[] args)throws IOException {
		
		//System.out.println("**********Zagot.txt*********");
		File fin1 = new File(args[0]);
		readFile1(fin1);
		
		//System.out.println("\n\n\n\n**********Fodors.txt*********");
		File fin2 = new File(args[1]);
		readFile2(fin2);
	
		//System.out.println(args[0]+"\n"+args[1]);
		
		/*for(String s: zagot)
			System.out.println(s);
		
		for(String s: fodors)
			System.out.println(s);*/
		

		
		similarityOutput(args[0],args[1],args[2]);
		
		
		
	}
}
