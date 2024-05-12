import java.lang.Math;
import static java.lang.Math.pow;
import java.util.Random;

public class multi {
    static int ctr = 0;     // primitive operation counter for Simple multiplication algorithm
    public static int length_count(long number){
        int n = 0;
        ctr++;  // assignment

        //if the number is zero
        ctr++;  // comparison
        if (number == 0) {
            n = 0;
            ctr++;  // assignment
        }
        else {
            do {
                n++;
                number /= 10;
                ctr += 4;   // addition, division and comparison in each do...while loop
            }
            while (number != 0);
            ctr++;
        }
        ctr++;      // return
        return n;
    }

    public static int PartialProduct (int multiplicand, int no_length,
                                      int digit_mult, int pp){
        //This path is for either 1 digit number multiplication
        //or accumulate the whole partial product to return
        ctr++;  // comparison for if statement
        if (no_length == 1){
            pp = multiplicand * digit_mult % 10 + pp;
            ctr += 4;    // assignment, multiplication, modulo and addition

            ctr++;      // return
            return pp;
        }
        else{
            int temp = multiplicand;
            ctr++;  // assignment
            //reduce the multiplicand to the largest digit of its number to
            //start adding its partial product and carrier to the whole
            int tens = (int) pow(10, (no_length - 1));
            ctr += 4;   // assignment, power, casting, subtraction
            multiplicand = multiplicand / tens;
            ctr += 2;   // assignment, division

            pp = multiplicand * digit_mult % 10 * tens + pp;
            ctr += 5;   // assignment, 2 multiplication, modulo, addition

            //reduce and pass the number to this very same method
            ctr += 4;   // return, method call, modulo, subtraction
            return PartialProduct(temp % tens,
                    no_length - 1, digit_mult, pp);
        }


    }
    public static int PartialProduct (int multiplicand, int no_length,
                                      int digit_mult){
        ctr += 2;   // return, method call
        return PartialProduct(multiplicand, no_length, digit_mult, 0);
    }
    public static int Carrier (int multiplicand, int no_length,
                               int digit_mult, int carr){
        //Part 1: Assign a static element for multiplicand;
        //        If multiplicand is multiplying ones of multiplier,
        //        then count the pp and carr straight.
        ctr++; // comparison for if statement
        if (no_length == 1){
            carr = multiplicand * digit_mult / 10 + carr;
            ctr += 5; // assignment, multiplication, division, addition, return
            return carr;
        }
        //Part 2: If no_length is not ones,or have number larger than 10
        // such as 456 then enter this to reduce the multiplicand in tens
        else{
            int temp = multiplicand;
            ctr++;  // assignment
            //reduce the multiplicand to the largest digit of its number to
            //start adding its partial product and carrier to the whole
            int tens = (int) pow(10, (no_length - 1));
            ctr += 4;   // assignment, casting, power, subtraction
            multiplicand = multiplicand / tens;
            ctr += 2;   // assignment, division

            carr = multiplicand * digit_mult / 10 * tens + carr;
            ctr += 5;   // assignment, 2 multiplication, division, addition

            //reduce and pass the number to this very same method
            ctr += 4;   // return, method call, modulo, subtraction
            return Carrier(temp % tens,
                    no_length - 1, digit_mult, carr);
        }
    }
    public static int Carrier (int multiplicand, int no_length,
                               int digit_mult){
        ctr += 2;   // return, function call
        return Carrier(multiplicand, no_length, digit_mult, 0);
    }
    public static void Mult (int multiplicand, int multiplier){
        //calculate the numbers inside multiplicand/multiplier
        //since both have same number length
        int no_length = length_count(multiplicand);
        ctr += 2;   // assignment, method call // 2

        for (int i = 0; i < no_length; i++) {
            System.out.print(" ");
        }

        System.out.println(multiplicand);

        for (int i = 0; i < no_length; i++) {
            System.out.print(" ");
        }

        System.out.println(multiplier);
        System.out.println("--------------------");

        //Recursive method for calculating partial product and carrier
        int digit = 0;
        long total = 0;
        ctr += 2;   // initialization

        for (int i = 0; i < no_length; i++){
            ctr+= 3;    // assignment, addition, comparison for loop
            //digit is the part of the single number from the multiplier
            //which starts from ones, tens, thousands and above
            digit = multiplier % ((int)pow(10, i+1)) / ((int)pow(10, i));
            ctr += 8;   // assignment, modulo, 2 casting, 2 power, addition, division

            //print out the space
            for(int j = 0; j < (no_length-i); j++) {
                System.out.print(" ");
            }
            //calculate the partial product and
            //print out the 0 not included in the partial product
            long partial_product = PartialProduct(multiplicand, no_length, digit);
            int PP_length = length_count(partial_product);
            ctr += 4;   // 2 initialization, 2 method call

            for(int j=0; j < (no_length - PP_length); j++) {
                System.out.print("0");
            }
            //print out the partial product
            System.out.println(partial_product);

            //print out the space
            for(int j = 0; j < (no_length-i-1); j++) {
                System.out.print(" ");
            }
            //calculate the carrier and
            //print out the 0 not included in the carrier
            long carrier = Carrier(multiplicand, no_length, digit);
            int carrier_length = length_count(carrier);
            ctr += 4;  // 2 initialization, 2 method call

            for(int j=0; j < (no_length - carrier_length); j++) {
                System.out.print("0");
            }
            //print out the carrier
            System.out.println(carrier);

            total+= (long) ((partial_product + (carrier* 10)) * pow(10, i));
            ctr += 6;   // assignment, casting, 2 multiplication, power, addition
        }
        ctr++;

        System.out.println("_____________________");
        long total_length = length_count(total);
        ctr += 2;   // assignment, method call

        for(int i=0; i < (no_length * 2 - total_length); i++) {
            System.out.print(" ");
        }
        System.out.println(total);

    }

    public static void main(String[] args) {
        // Create an instance of Random class
        Random r = new Random();

        // Generate the lower and upper bounds for n digits
        int digit;
        int lowerBound;
        int upperBound;
        int x;
        int y;

        for(int i = 1; i < 100; i++) {

            // Generate a random number within the bounds
            digit = r.nextInt(9) + 1;       // prevent digit = 0
            upperBound = (int) Math.pow(10, digit) - 1;
            lowerBound = (int) Math.pow(10, digit - 1);
            x = (int) r.nextInt(upperBound - lowerBound + 1) + lowerBound;
            y = (int) r.nextInt(upperBound - lowerBound + 1) + lowerBound;

            Mult(x, y);

            System.out.println("Expexted result: " + (long) x * y);
            System.out.println("Operation Counter : " + ctr + "\n");

            ctr = 0;
            // reset the primitive operation counter

        }
    }
}
