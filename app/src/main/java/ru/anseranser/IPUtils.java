package ru.anseranser;

import java.util.regex.Pattern;

class IPUtils {


    static long ipToLong(String ipAddress) {
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

    static boolean isIPv4Address(String ipAddress) {
        String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern regexPattern = Pattern.compile(pattern);
        return regexPattern.matcher(ipAddress).matches();
    }
}
