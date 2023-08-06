package opps;


final class FinalClassEx {
    void display() {
        System.out.println("This is a final class.");
    }
}

// Error: Cannot inherit from final 'FinalClassExample'
//class Subclass extends FinalClassEx {
//}

public class FinalClass {
    public static void main(String[] args) {
        FinalClassEx finalObj = new FinalClassEx();
        finalObj.display();
    }
}
