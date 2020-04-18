package converter;

import java.util.Scanner;

import static java.lang.String.format;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final var sourceRadix = readRadix();
        final var sourceNumber = readNumber(sourceRadix);
        final var targetRadix = readRadix();
        sc.close();

        System.out.println(
                new MyNumber(sourceNumber, sourceRadix)
                        .toString(targetRadix));
    }

    static int readRadix() {
        final var radix = sc.nextLine();
        if (radix.matches("\\d{1,2}")) {
            int i = Integer.parseInt(radix);
            if (i > 0 && i < 37) {
                return i;
            }
        }
        System.out.println("Error: Radix has to be number from 1 to 36!");
        System.exit(1);
        return 0;
    }

    static String readNumber(int radix) {
        final var number = sc.nextLine();
        if (!number.matches(getRegexp(radix))) {
            System.out.println("Error: Incorrect format for source number!");
            System.exit(1);
        }
        return number;
    }

    static String getRegexp(int radix) {
        final String range;
        if (radix == 1) {
            range = "1";
        } else if (radix <= 10) {
            range = format("[0-%d]", radix - 1);
        } else {
            range = format("[0-9a-%c]", radix - 11 + 'a');
        }
        return format("%1$s+(\\.%1$s+)?", range);
    }
}
