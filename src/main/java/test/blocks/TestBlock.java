package test.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestBlock {

    public TestBlock(boolean a) {
        System.out.println("Instantiated with boolean constructor: " + a);
    }

    public TestBlock(int a) {
        System.out.println("Instantiated with int constructor: " + a);
    }

    public TestBlock(int... a) {
        System.out.println("Instantiated with int array constructor: " + String.join(",", Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.toList())));
    }

    public TestBlock(float a) {
        System.out.println("Instantiated with float constructor: " + a);
    }

    public TestBlock(double a) {
        System.out.println("Instantiated with double constructor: " + a);
    }

    public TestBlock(double[] a) {
        System.out.println("Instantiated with double constructor: " + String.join(",", Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.toList())));
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

    public TestBlock(String a, Location location) {
        System.out.println("Instantiated with: " + a + " (" + location.x + ", " + location.y + ", " + location.z + ")");
    }
}