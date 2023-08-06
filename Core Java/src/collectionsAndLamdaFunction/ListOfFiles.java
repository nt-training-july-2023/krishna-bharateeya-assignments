package collectionsAndLamdaFunction;

import java.io.File;

public class ListOfFiles {

	    public static void main(String[] args) {
	    	
	        String path = "C:\\Users\\Krishna Bharateeya\\Documents"; 
	        File directory = new File(path);

	        
	        if (directory.exists() && directory.isDirectory()) {
	        
	            String[] fileList = directory.list();

	        
	            if (fileList != null) {
	                System.out.println("List of files or directories in " + path + ":");
	                for (String file : fileList) {
	                    System.out.println(file);
	                }
	            } else {
	                System.out.println("The directory is empty.");
	            }
	        } else {
	            System.out.println("The specified directory does not exist or is not a directory.");
	        }
	    }
	

}
