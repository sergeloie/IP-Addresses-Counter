package ru.anseranser;

public class DeepSeek {

    public static long DeepSeek_textToNumericFormatV4(String src) {
        if (!src.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            return -1L;
        }

        long ipValue = 0;
        int octetCounter = 0;
        int digitCounter = 0;
        int currentOctet = 0;

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (digitCounter == 0 || digitCounter > 3 || currentOctet > 255) {
                    return -1L;
                }
                ipValue = (ipValue << 8) | currentOctet;
                currentOctet = 0;
                digitCounter = 0;
                octetCounter++;
            } else {
                int digit = c - '0';
                if (digitCounter == 0 && digit == 0 && i < src.length() - 1 && src.charAt(i + 1) != '.') {
                    return -1L;
                }
                currentOctet = currentOctet * 10 + digit;
                digitCounter++;
                if (digitCounter > 3 || currentOctet > 255) {
                    return -1L;
                }
            }
        }

        if (octetCounter != 3 || digitCounter == 0 || currentOctet > 255) {
            return -1L;
        }
        ipValue = (ipValue << 8) | currentOctet;
        return ipValue;
    }
}
