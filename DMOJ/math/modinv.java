import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Andi Gu
 */
public class modinv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger a = sc.nextBigInteger(), b = sc.nextBigInteger();
        System.out.println(a.modInverse(b));
    }
}
