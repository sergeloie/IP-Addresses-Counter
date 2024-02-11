package bench;

import java.util.regex.Pattern;

public class RegexpTest {

    public static final String OUTER_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    public static final Pattern OUTER_REGEX_PATTERN = Pattern.compile(OUTER_PATTERN);

    public static boolean isIPv4Address(String ipAddress) {
        String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern regexPattern = Pattern.compile(pattern);
        return regexPattern.matcher(ipAddress).matches();
    }

    public static boolean isIPv4AddressOuter(String ipAddress) {
        return OUTER_REGEX_PATTERN.matcher(ipAddress).matches();
    }
}
