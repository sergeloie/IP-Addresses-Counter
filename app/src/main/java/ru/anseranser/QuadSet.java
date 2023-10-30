package ru.anseranser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static ru.anseranser.IPUtils.ipToLong;

public class QuadSet {

    private final int setSize = 1_073_741_824; // четверть от общего числа IP адресов

    private BitSet ipset0;
    private BitSet ipset1;
    private BitSet ipset2;
    private BitSet ipset3;

    private List<BitSet> omniSet;

    public QuadSet() {
        this.ipset0 = new BitSet(setSize);
        this.ipset1 = new BitSet(setSize);
        this.ipset2 = new BitSet(setSize);
        this.ipset3 = new BitSet(setSize);
        this.omniSet = new ArrayList<>();
        this.omniSet.add(this.ipset0);
        this.omniSet.add(this.ipset1);
        this.omniSet.add(this.ipset2);
        this.omniSet.add(this.ipset3);
    }

    /**
     * @param ipAddress - IP address
     */
    public void set(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
//        int setPosition = (int) (ipNumericValue % setSize);
        int setPosition = Math.toIntExact(ipNumericValue & (setSize - 1));
//        int setNumber = (int) (ipNumericValue / setSize);
        int setNumber = Math.toIntExact(ipNumericValue >> 30);

        omniSet.get(setNumber).set(setPosition);
    }

    /**
     * @return cardinality
     */
    public long size() {
        long result = 0;
        for (BitSet bitset : omniSet) {
            System.out.printf("Bitset Hash = %d | BitSet size = %,d%n", bitset.hashCode(), bitset.cardinality());
            result += bitset.cardinality();
        }
        return result;
    }

}
