package opps;

class BaseClass {
    final void display() {
        System.out.println("This is a final method.");
    }
}

class SubClass extends BaseClass {
    // Error: Cannot override the final method from 'BaseClass'
    // void display() { ... }
}

final class AnotherFinalClass {

    void show() {
        System.out.println("This is a method in a final class.");
    }
}

public class FinalMethodClass {
    public static void main(String[] args) {
        BaseClass baseObj = new BaseClass();
        baseObj.display();

        AnotherFinalClass finalObj = new AnotherFinalClass();
        finalObj.show();
    }
}
