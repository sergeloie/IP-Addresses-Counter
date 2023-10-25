package ru.anseranser;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.anseranser.IPUtils.ipToLong;


class IPCounterTest {

    private IPStorage ipStorage = new IPStorage();

    @Test
    void isBiective() {
        String ip1 = "127.0.0.1";
        String ip2 = "192.168.1.1";
        ipStorage.put(ip1);
        ipStorage.put(ip2);
        long ipl1 = ipToLong("127.0.0.1");
        long ipl2 = ipToLong("192.168.1.1");
        assertEquals(ip1, ipStorage.get(ipl1));
        assertEquals(ip2, ipStorage.get(ipl2));
    }

    @Test
    void isSingleOccurrence() {
        String ip1 = "122.5.8.15";
        for (int i = 0; i < 100000; i++) {
            ipStorage.put(ip1);
        }
        assertTrue(ipStorage.check(ip1));
        assertEquals(1, ipStorage.count());
    }

    @Test
    void fileTest() {
        String fileName = "src/test/resources/ip.txt";
        long actual = IPCounter.countIPs(fileName, false);
        assertEquals(3, actual);
    }

    @Test
    void getTest() {
        String ip1 = "253.1.1.1";
        String ip2 = "127.0.0.1";
        String expected = "127.0.0.1 is not in the set";
        long ipl1 = ipToLong(ip1);
        long ipl2 = ipToLong(ip2);
        ipStorage.put(ip1);
        assertEquals(ip1, ipStorage.get(ipl1));
        assertEquals(expected, ipStorage.get(ipl2));
    }
}
