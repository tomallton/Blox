package test.blocks;

public class Location {

    private final double x, y, z;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

}