package collectionsAndLamdaFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InvertContents {

public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		try {
		
			System.out.println("Enter Source file name with fullpath and extension: ");
			String sourceFile=scan.nextLine();
			
			System.out.println("Enter Destination file name with fullpath and extension: ");
			String destinationFile=scan.nextLine();
			
			invertFile(sourceFile,destinationFile);
			System.out.println("File Revert Successfully ..");
			
		}catch(IOException e) {
			System.out.println("Invilid Input .\n"+ e.getMessage());
		}catch (Exception e) {
			
			System.out.println("Unexcepted problem Occured.\n"+ e.getMessage());
		}
		 
		
	}

public static void invertFile(String sourceFile,String destinationFile) throws IOException {
	
	BufferedReader reader=null;
	BufferedWriter writer=null;
	try {
		reader=new BufferedReader(new FileReader(sourceFile));	
		writer=new BufferedWriter(new FileWriter(destinationFile));
		
		ArrayList<String> list=new ArrayList<String>();
		String line;
		
		while((line=reader.readLine())!=null) {
			
			list.add(line);
		}
		Collections.reverse(list);
		
		for(String reversedLine : list) {
            writer.write(reversedLine);
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
