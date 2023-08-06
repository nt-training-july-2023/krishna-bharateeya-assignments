package collectionsAndLamdaFunction;

import java.util.Scanner;
import java.util.function.Function;

public class ReplaceVowels {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Please Enter a String :");
		String str=scan.nextLine();
		
		String result= processString(str);
		
		System.out.println("Original String :"+str);
		System.out.println("After Replacing the Vowels :"+result);
	}
	
	
	private static String processString(String str) {
		 Function<String, String> replaceVowels = (string) -> str.replaceAll("[aeiouAEIOU]", "#");

	        String result = replaceVowels.apply(str);

		return result;
	}
}

