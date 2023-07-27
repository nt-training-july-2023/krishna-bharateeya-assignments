package exceptionHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReadingUsingStream {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter File name with full path and extension :");
		String path=scan.nextLine();
		File file=new File(path);
		
		try (Stream<String> lineStream=Files.lines(file.toPath())){
			
			lineStream.forEach(line->{
				
				System.out.println(line);
				
			});
		}catch(IOException e) {
		
			System.out.println("please Enter the Valid Path.\n"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("An Unexpected problem occured.\n"+e.getMessage());
		}finally {
			scan.close();
		}
		
	}
}
