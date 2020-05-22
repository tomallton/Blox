package test.blocks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBlock {

    public TestBlock(boolean a) {
        System.out.println("Instantiated with boolean constructor: " + a);
    }

    public TestBlock(int a) {
        System.out.println("Instantiated with int constructor: " + a);
    }
    
    public TestBlock(int... a) {
        System.out.println("Instantiated with int constructor: " + String.join(",", Stream.of(a).map(Object::toString).collect(Collectors.toList())));
    }
    
    public TestBlock(float a) {
        System.out.println("Instantiated with float constructor: " + a);
    }

    public TestBlock(double a) {
        System.out.println("Instantiated with double constructor: " + a);
    }

    public TestBlock(double[] a) {
        System.out.println("Instantiated with double constructor: " + String.join(",", Stream.of(a).map(Object::toString).collect(Collectors.toList())));
    }

    public TestBlock(String a) {
        System.out.println("Instantiated with the string constructor: " + a);
    }

    public TestBlock(String... a) {
        System.out.println("Instantiated with string array constructor: " + String.join(",", a));
    }

    public TestBlock(List<String> a) {
        System.out.println("Instantiated with string list constructor: " + String.join(",", a));
    }

}