package ru.anseranser;


import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.anseranser.IPCounter.getIPfromBitSet;
import static ru.anseranser.IPCounter.putIPinBitSet;
import static ru.anseranser.IPUtils.ipToLong;


class IPCounterTest {
    private BitSet ipset0127 = new BitSet(Integer.MAX_VALUE);
    private BitSet ipset128255 = new BitSet(Integer.MAX_VALUE);


    @Test
    void isBiective() {
        String ip1 = "127.0.0.1";
        String ip2 = "192.168.1.1";
        putIPinBitSet(ip1, ipset0127, ipset128255);
        putIPinBitSet(ip2, ipset0127, ipset128255);
        long ipl1 = ipToLong("127.0.0.1");
        long ipl2 = ipToLong("192.168.1.1");
        assertEquals(ip1, getIPfromBitSet(ipl1, ipset0127, ipset128255));
        assertEquals(ip2, getIPfromBitSet(ipl2, ipset0127, ipset128255));
    }

    @Test
    void isSingleOccurrence() {
        String ip1 = "122.5.8.15";
        for (int i = 0; i < 100000; i++) {
            putIPinBitSet(ip1, ipset0127, ipset128255);
        }
        assertEquals(1, ipset0127.cardinality());
        assertEquals(1, ipset0127.cardinality() + ipset128255.cardinality());
    }

    @Test
    void fileTest() {
        String fileName = "src/test/resources/ip.txt";
        long actual = IPCounter.countIPs(fileName, false);
        assertEquals(3, actual);
    }
}
