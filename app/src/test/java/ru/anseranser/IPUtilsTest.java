package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.anseranser.IPUtils.ipToLong;
import static ru.anseranser.IPUtils.longToIp;
import static ru.anseranser.IPUtils.isIPv4Address;

class IPUtilsTest {

    @Test
    void ipToLongTest() {
        String ip1 = "192.168.1.1";
        assertEquals(3232235777L, ipToLong(ip1));
    }

    @Test
    void longToIpTest() {
        long long1 = 2130706433L;
        assertEquals("127.0.0.1", longToIp(long1));
    }

    @Test
    void isIPv4AddressTest() {
        String ip1 = "192.168.1.1";
        String ip2 = "192.168.302.1";
        assertTrue(isIPv4Address(ip1));
        assertFalse(isIPv4Address(ip2));
    }
}
