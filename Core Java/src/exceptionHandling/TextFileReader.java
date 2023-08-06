package exceptionHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TextFileReader {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter File Path with file name :");
		String path=scan.nextLine();
		
		try {
			
			BufferedReader reader=new BufferedReader(new FileReader(path));
			String line;
			
			while((line=reader.readLine())!= null) {
				System.out.println(line);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occurred while reading the file.\n"+e.getMessage());
		}catch(SecurityException e) {
			System.out.println("No Permission To Read File.\n"+e.getMessage());
		}catch(IOException e) {
			System.out.println("Something Went Wrong.\n"+e.getMessage());
		}catch (Exception e) {
			System.out.println("Unexcepted Problem occured.\n"+e.getMessage());
		}
	}
}
