package ru.anseranser;

public class IPUtils {


    public static long ipToLong(String ipAddress) {
        String[] octetStrings = ipAddress.split("\\.");
        long result = 0;

        for (int i = 0; i < 4; i++) {
            long octetValue = Long.parseLong(octetStrings[i]);
            result += octetValue << (8 * (3 - i));
        }
        return result;
    }

    static String longToIp(long ipAddress) {
        StringBuilder builder = new StringBuilder();

        for (int i = 3; i >= 0; i--) {
            long octetValue = (ipAddress >> (8 * i)) & 0xFF;
            builder.append(octetValue);
            if (i != 0) {
                builder.append(".");
            }
        }
        return builder.toString();
    }
}
