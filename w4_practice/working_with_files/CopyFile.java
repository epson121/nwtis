import java.io.*;

public class CopyFile{

	public static void main(String[] args) throws IOException{
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);

		FileInputStream in = new FileInputStream(inputFile);
		FileOutputStream out = new FileOutputStream(outputFile);
		
		int c;

		while((c = in.read()) != -1 )
			out.write(c);

		System.out.println("Done!");

		in.close();
		out.close();
	}
}