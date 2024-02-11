package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.anseranser.IPUtils.ipToLong;
import static ru.anseranser.IPUtils.longToIp;

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

}
