package opps;


interface Printer{
	
	void print();
}

interface scanner{
	void scan();
}

class HpPrinter implements Printer,scanner{
	
	public void print() {
		System.out.println("Printing the document.");
	}
	public void scan() {
		System.out.println("Scanning the document.");
	}
}

public class Multiple_Inheritance {

	public static void main(String[] args) {
		HpPrinter printer=new HpPrinter();
		
		printer.print();
		printer.scan();
	}
}
