package ru.anseranser;

public class Openjdk {
    public static long Openjdk_textToNumericFormatV4(String src)
    {

        long octetValue = 0;

        long tmpValue = 0;
        int currByte = 0;
        boolean newOctet = true;
        int innerOctetCounter = 0;

        int len = src.length();
        if (len < 7 || len > 15) {
            return -1L;
        }

        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (newOctet || tmpValue < 0 || tmpValue > 0xff || currByte == 3) {
                    return -1L;
                }

                octetValue += tmpValue << (8 * (3 - currByte));
                tmpValue = 0;
                innerOctetCounter = 0;
                newOctet = true;
                currByte++;
            } else {
                int digit = Character.digit(c, 10);
                if (digit < 0) {
                    return -1L;
                }
                innerOctetCounter++;
                if (innerOctetCounter > 3) {
                    return -1L;
                }
                tmpValue *= 10;
                tmpValue += digit;
                newOctet = false;
            }
        }
        if (newOctet || tmpValue < 0 || tmpValue >= (1L << ((4 - currByte) * 8))) {
            return -1L;
        }
        octetValue += ((tmpValue) & 0xff);
        return octetValue;
    }
}
