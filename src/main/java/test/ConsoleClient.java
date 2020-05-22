package test;

public class ConsoleClient {

    public String getName() {
        return "CONSOLE";
    }

    public void message(String... message) {
        for (String a : message) {
            System.out.println(a);
        }
    }
}