package collectionsAndLamdaFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
    
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

public class ComparableAndComparator {

	 public static void main(String[] args) {
	       
		 ArrayList<Person> people = new ArrayList<>();
	        people.add(new Person("Krishna", 25));
	        people.add(new Person("Praveen", 20));
	        people.add(new Person("Vivek", 30));

	        System.out.println("Before sorting: " + people);
	        Collections.sort(people, new AgeComparator()); 
	        System.out.println("After sorting: " + people);
	    }

	
}
