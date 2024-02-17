package ru.anseranser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static ru.anseranser.IPConverter.textToNumericFormatV4;

public final class QuadSet {

    private static final int SET_SIZE = 1_073_741_824; // четверть от общего числа IP адресов, 2 в 30 степени

    private BitSet ipset0;
    private BitSet ipset1;
    private BitSet ipset2;
    private BitSet ipset3;

    private List<BitSet> omniSet;

    public QuadSet() {
        this.ipset0 = new BitSet();
        this.ipset1 = new BitSet();
        this.ipset2 = new BitSet();
        this.ipset3 = new BitSet();
        this.omniSet = new ArrayList<>();
        this.omniSet.add(this.ipset0);
        this.omniSet.add(this.ipset1);
        this.omniSet.add(this.ipset2);
        this.omniSet.add(this.ipset3);
    }

    /**
     * @param ipAddress - String representation of IP address i.e. "192.168.1.1"
     */
    public void set(String ipAddress) {
        set(textToNumericFormatV4(ipAddress));
    }

    /**
     * @param ipNumericValue - long value of IP address i.e. 4294967295 for 255.255.255.255
     */
    public void set(long ipNumericValue) {
        int setPosition = Math.toIntExact(ipNumericValue & (SET_SIZE - 1)); // остаток от деления на setSize
        int setNumber = Math.toIntExact(ipNumericValue >> 30); // целочисленное деление на setSize
        omniSet.get(setNumber).set(setPosition);
    }

    /**
     * @return cardinality
     */
    public long cardinality() {

        long result = 0;
        for (BitSet bitset : omniSet) {
            result += bitset.cardinality();
        }
        return result;
    }
}
