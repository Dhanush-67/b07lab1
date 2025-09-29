import java.io.File;
import java.io.IOException;

public class Driver {
public static void main(String [] args) {
    try {
        Polynomial p1 = new Polynomial(new double[]{3, 2, -1}, new int[]{2, 1, 0});
        Polynomial p2 = new Polynomial(new double[]{1, -4}, new int[]{1, 0});

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

        Polynomial sum = p1.add(p2);
        System.out.println("P1 + P2: " + sum);

        String filename = "polynomial.txt";
        p1.saveToFile(filename);
        System.out.println("P1 saved to file: " + filename);

        Polynomial p3 = new Polynomial(new File(filename));
        System.out.println("Polynomial read from file: " + p3);
    } catch (IOException e) {
        System.err.println("IO Error: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}
}