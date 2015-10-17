import java.io.*;





public class stringMatch {
	private static void readFile(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line.substring(line.indexOf("---")+3));
		}
	 
		br.close();
	}
	
	public static void main(String[] args)throws IOException {
		
		System.out.println("Hello");
		
		File fin = new File("../zagat.txt");
		readFile(fin);
	}
}
