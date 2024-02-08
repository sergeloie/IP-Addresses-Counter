package ru.anseranser;

import java.util.regex.Pattern;

public class IPUtilsTest {

    static String outerPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    static Pattern outerRegexPattern = Pattern.compile(outerPattern);

    public static boolean isIPv4Address(String ipAddress) {
        String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern regexPattern = Pattern.compile(pattern);
        return regexPattern.matcher(ipAddress).matches();
    }

    public static boolean isIPv4AddressOuter(String ipAddress) {

        return outerRegexPattern.matcher(ipAddress).matches();
    }
}
