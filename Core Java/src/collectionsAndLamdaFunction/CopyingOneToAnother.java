package collectionsAndLamdaFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CopyingOneToAnother {


	
	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		try {
		
			System.out.println("Enter Source file name with fullpath and extension: ");
			String sourceFile=scan.nextLine();
			
			System.out.println("Enter Destination file name with fullpath and extension: ");
			String destinationFile=scan.nextLine();
			
			copyContent(sourceFile,destinationFile);
			System.out.println("File Copied Successfully ..");
			
		}catch(IOException e) {
			System.out.println("Invilid Input .\n"+ e.getMessage());
		}catch (Exception e) {
			
			System.out.println("Unexcepted problem Occured.\n"+ e.getMessage());
		}
		 
		
	}

	public static void copyContent(String sourceFile,String destinationFile) throws IOException {
		
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try {
			reader=new BufferedReader(new FileReader(sourceFile));
			writer=new BufferedWriter(new FileWriter(destinationFile));
			String line;
			
			while((line=reader.readLine())!=null) {
				writer.append(line);
				writer.newLine();
			}
			
		} catch (FileNotFoundException e) {
		
			System.out.println("File Not Found.\n"+e.getMessage());
		}catch (IOException e) {

			System.out.println("Invilid Input .\n"+e.getMessage());
		}catch(Exception e) {
			
			System.out.println("UnExcepted Problem Occured.\n"+e.getMessage());
		}finally {
			reader.close();
			writer.close();
			
		}
	}
}
