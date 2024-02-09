package ru.anseranser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

//import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;
import static ru.anseranser.IPUtils.ipToLong;

public class QuadSet {

    private final int setSize = 1_073_741_824; // четверть от общего числа IP адресов, 2 в 30 степени

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
     * @param ipAddress - IP address
     */
    public void set(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
//        int setPosition = (int) (ipNumericValue % setSize);
        int setPosition = Math.toIntExact(ipNumericValue & (setSize - 1)); // остаток от деления на setSize
//        int setNumber = (int) (ipNumericValue / setSize);
        int setNumber = Math.toIntExact(ipNumericValue >> 30); // целочисленное деление на setSize

        omniSet.get(setNumber).set(setPosition);
    }


    public void set(long ipNumericValue) {
        int setPosition = Math.toIntExact(ipNumericValue & (setSize - 1)); // остаток от деления на setSize
        int setNumber = Math.toIntExact(ipNumericValue >> 30); // целочисленное деление на setSize
        omniSet.get(setNumber).set(setPosition);
    }

    /**
     * @return cardinality
     */
    public long cardinality() {

        int setCounter = 0;
        long result = 0;
        for (BitSet bitset : omniSet) {
//            System.out.printf("Bitset %d Hash = %d | BitSet cardinality = %,d | BitSet size = %s%n",
//                    setCounter++,
//                    bitset.hashCode(),
//                    bitset.cardinality(),
//                    byteCountToDisplaySize(bitset.size() / 8));

            result += bitset.cardinality();
        }
        return result;
    }
}
