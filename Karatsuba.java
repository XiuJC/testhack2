/// Java Program to Implement Karatsuba Algorithm

// Importing Random class from java.util packahge
import java.util.Random;

// MAin class
public class Karatsuba {

    static int ctr = 0;     // primitive operation counter for Karatsuba algorithm

    // Main driver method
    public static long mult(long x, long y) {

        // Checking only if input is within range
        ctr += 3;  // 2 comparison, 1 logical operation(&&)
        if (x < 10 && y < 10) {
            // Multiplying the inputs entered
            ctr++;  // multiplication
            return x * y;
        }

        // Declaring variables in order to
        // Find length of both integer
        // numbers x and y
        int noOneLength = numLength(x);
        ctr += 2;   // assignment, method call
        int noTwoLength = numLength(y);
        ctr += 2;   // assignment, method call

        // Finding maximum length from both numbers
        // using math library max function
        int maxNumLength
                = Math.max(noOneLength, noTwoLength);
        ctr += 2;   // assignment, method call

        // Rounding up the divided Max length
        Integer halfMaxNumLength
                = (maxNumLength / 2) + (maxNumLength % 2);
        ctr += 4;   // assignment, addition, division, modulo

        // Multiplier
        long maxNumLengthTen
                = (long)Math.pow(10, halfMaxNumLength);
        ctr += 3;   // assignment, casting, power

        // Compute the expressions
        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;
        ctr += 8;   // 4 assignment, 2 modulo, 2 divide


        // Compute all mutilpying variables
        // needed to get the multiplication
        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);
        ctr += 8;   // 3 assignment, 3 method call, 2 addition


        long ans = (z0 * (long)Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2));
        ctr += 12;   // assignment, 2 addition, 2 casting, 2 power, 3 multiplication, 2 subtraction,

        ctr++;  // return
        return ans;

    }

    // Method 1
    // To calculate length of the number
    public static int numLength(long n)
    {
        int noLen = 0;
        ctr++;  // assignment

        ctr++;  // comparison
        while (n > 0) {
            noLen++;
            n /= 10;
            ctr += 3;   // increment, division, comparison
        }

        // Returning length of number n
        ctr++;  // return
        return noLen;
    }

    // Method 2
    // Main driver function
    public static void main(String[] args)
    {
        // Showcasing karatsuba multiplication
        long expectedProduct;
        long actualProduct;

        Integer digit;
        Integer x = null;
        Integer y = null;
        int upperbound;
        int lowerbound;

        // Boe creating an object of random class
        // inside main() method
        Random r = new Random();

        for (int i = 0; i < 100; i++) {

            digit = (int) r.nextInt(9) + 1;     // prevent digit = 0
            upperbound = (int) Math.pow(10, digit) - 1;
            lowerbound = (int) Math.pow(10, digit - 1);
            x = (int) r.nextInt(upperbound - lowerbound + 1) + lowerbound;
            y = (int) r.nextInt(upperbound - lowerbound + 1) + lowerbound;

            expectedProduct = (long) x * y;

            if (i == 99) {

                // Prove assertions catch the bad stuff.
                expectedProduct = 1;
            }
            actualProduct = mult(x, y);

            // Again printing the expected and
            // corresponding actual product
            System.out.println("digit, n = " + digit);
            System.out.println("Multiplier: " + x + ", Multiplicand: " + y);
            System.out.println("Expected: " + expectedProduct);
            System.out.println("Actual: " + actualProduct);
            System.out.println("Primitive operation: " + ctr + "\n\n");

            ctr = 0;    // reset counter

            assert(expectedProduct == actualProduct);
        }
    }
}