package opps;

public class Static_Variable {

	public static int count;
	
	static class Marketing{
		
		void Cuatmor_in() {
			count++;
		}
		int totalCustmor() {
			return count;
		}
	}
	public static void main(String[] args) {
		
		Marketing mr= new Marketing();
		mr.Cuatmor_in();
		mr.Cuatmor_in();
		mr.Cuatmor_in();
		System.out.println("Total Number of Custmar : "+count);		
		
	}
}
