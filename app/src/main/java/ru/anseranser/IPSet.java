package ru.anseranser;

import static ru.anseranser.IPUtils.ipToLong;

public class IPSet {

    private static final int STORAGE_SIZE = 67_108_864;

    private long[] storage;

    public IPSet() {
        this.storage = new long[STORAGE_SIZE];
    }

    public static int getCellNumber(long value) {
        return Math.toIntExact(value / 64);
    }

    public static int getCellIndex(long value) {
        return Math.toIntExact(value % 64);
    }

    /**
     * @param value - bit position in bitset
     * @return true if bit is set, false otherwise
     */
    public boolean get(long value) {
        int cellNumber = getCellNumber(value);
        int cellIndex = getCellIndex(value);
        long mask = 1L << cellIndex;
        return (storage[cellNumber] & mask) != 0;
    }

    public static long setTrue(long value, int position) {
        return value | 1L << position;
    }

    public static long setFalse(long value, int position) {
        return value & ~(1L << position);
    }

    /**
     * @param value - bit position in bitset
     * @param bit - true if bit should be set, false otherwise
     */
    public void set(long value, boolean bit) {
        int cellNumber = getCellNumber(value);
        int cellIndex = getCellIndex(value);
        storage[cellNumber] = bit
                ? setTrue(storage[cellNumber], cellIndex)
                : setFalse(storage[cellNumber], cellIndex);
    }

    /**
     * @param ipAddress - ip address in string format to store in bitset
     */
    public void set(String ipAddress) {
        set(ipToLong(ipAddress), true);
    }


    public void set(long ipAddress) {

        set(ipAddress, true);
    }

    /**
     * @return number of bits set in this bitset
     */
    public long cardinality() {
        long sum = 0;
        for (int i = 0; i < STORAGE_SIZE; i++) {
            sum += Long.bitCount(storage[i]);
        }
        return sum;
    }



}
