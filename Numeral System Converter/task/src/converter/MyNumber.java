package converter;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.pow;
import static java.util.stream.IntStream.range;

class MyNumber {
    final boolean isFloat;
    final long integerPart;
    final double fractionalPart;

    MyNumber(final String number, int radix) {
        final String[] parts = number.split("\\.");
        isFloat = parts.length == 2 && radix > 1;

        integerPart = radix == 1 ? parts[0].length() : parseLong(parts[0], radix);

        fractionalPart = isFloat ? range(0, parts[1].length())
                .mapToDouble(i -> parseInt(parts[1].substring(i, i + 1), radix) / pow(radix, i + 1.))
                .sum() : 0;
    }

    String toString(int radix) {
        if (radix == 1) {
            return "1".repeat((int) integerPart);
        }

        var result = new StringBuilder(Long.toString(integerPart, radix));

        if (isFloat) {
            double fraction = fractionalPart;
            result.append('.');
            for (int maxDigits = 5; maxDigits > 0 && fraction > 0; --maxDigits) {
                fraction *= radix;
                result.append(Integer.toString((int) fraction, radix));
                fraction -= (int) fraction;
            }
        }
        return result.toString();
    }
}