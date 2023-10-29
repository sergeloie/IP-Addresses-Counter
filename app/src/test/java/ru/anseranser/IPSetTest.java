package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.anseranser.IPSet.setFalse;
import static ru.anseranser.IPSet.setTrue;
import static ru.anseranser.IPUtils.ipToLong;

class IPSetTest {

    @Test
    void testFlipBit() {
        long l1 = 100L;
        long l2 = setTrue(l1, 1);
        long l3 = setFalse(l1, 5);
        assertEquals("1100100", Long.toBinaryString(l1));
        assertEquals("1100110", Long.toBinaryString(l2));
        assertEquals("1000100", Long.toBinaryString(l3));
    }

    @Test
    void testCheck() {
        long zero = 0L;
        long huge = ipToLong("255.255.255.255");
        assertEquals(0, IPSet.getCellNumber(zero));
        assertEquals(0, IPSet.getCellIndex(zero));
        assertEquals(67108863, IPSet.getCellNumber(huge));
        assertEquals(63, IPSet.getCellIndex(huge));

    }

    @Test
    void testStorage() {
        IPSet ipSet = new IPSet();
        long test1 = ipToLong("192.168.1.1");
        ipSet.set("192.168.1.1");
        assertTrue(ipSet.get(test1));
        assertFalse(ipSet.get(test1 + 1));
        assertFalse(ipSet.get(test1 - 1));
        assertEquals(1, ipSet.cardinality());
    }

//    @Test
//    void testFullFill() {
//        IPSet ipSet = new IPSet();
//        StringBuilder sb = new StringBuilder();
//        for (int o1 = 0; o1 <=255; o1++) {
//            for (int o2 = 0; o2 <=255; o2++) {
//                for (int o3 = 0; o3 <=255; o3++) {
//                    for (int o4 = 0; o4 <=255; o4++) {
//                        sb.append(o1).append(".").append(o2).append(".").append(o3).append(".").append(o4);
//                        ipSet.set(sb.toString());
//                        sb.setLength(0);
//                    }
//                }
//            }
//            System.out.println(o1);
//        }
//        System.out.println(ipSet.cardinality());
//    }
}
