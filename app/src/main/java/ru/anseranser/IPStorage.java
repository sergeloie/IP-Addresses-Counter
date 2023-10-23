package ru.anseranser;

import java.util.BitSet;

import static ru.anseranser.IPUtils.ipToLong;

public class IPStorage {

    private BitSet ipSet0127;
    private BitSet ipSet128255;

    public IPStorage() {
        this.ipSet0127 = new BitSet(Integer.MAX_VALUE);
        this.ipSet128255 = new BitSet(Integer.MAX_VALUE);
    }

    public void put(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
        if (ipNumericValue < Integer.MAX_VALUE) {
            this.ipSet0127.set((int) ipNumericValue);
        } else {
            this.ipSet128255.set((int) (ipNumericValue - Integer.MAX_VALUE));
        }
    }

    public boolean check(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
        if (ipNumericValue < Integer.MAX_VALUE) {
            return this.ipSet0127.get((int) ipNumericValue);
        } else {
            return this.ipSet128255.get((int) (ipNumericValue - Integer.MAX_VALUE));
        }
    }

    public long count() {
        return this.ipSet0127.cardinality() + this.ipSet128255.cardinality();
    }
}
