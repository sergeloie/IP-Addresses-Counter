package ru.anseranser;

public class IPConverter {

    public static long textToNumericFormatV4(String src) {

        long octetValue = 0;
        long tempValue = 0;
        int octetNumber = 3;
        boolean newOctet = true;
        int inOctetDigitCounter = 0;
        int length = src.length();

        if (length < 7 || length > 15) {
            return -1L;
        }

        for (int i = 0; i < length; i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (newOctet || tempValue < 0 || tempValue > 255 || octetNumber == 0) {
                    return -1L;
                }

                octetValue += tempValue << 8 * octetNumber;
                tempValue = 0;
                inOctetDigitCounter = 0;
                newOctet = true;
                octetNumber--;
            } else {
                int digit = Character.digit(c, 10);
                if (digit < 0) {
                    return -1L;
                }
                inOctetDigitCounter++;
                if (inOctetDigitCounter > 3) {
                    return -1L;
                }
                tempValue *= 10;
                tempValue += digit;
                newOctet = false;
            }
        }
        if (newOctet || tempValue < 0 || tempValue > 255 || octetNumber > 0) {
            return -1L;
        }
        octetValue += tempValue;
        return octetValue;
    }
}
