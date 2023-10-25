package ru.anseranser;

import java.util.BitSet;

import static ru.anseranser.IPUtils.ipToLong;
import static ru.anseranser.IPUtils.longToIp;

public class IPStorage {

    private BitSet ipSet0127;
    private BitSet ipSet128255;

    public IPStorage() {
        this.ipSet0127 = new BitSet(Integer.MAX_VALUE);
        this.ipSet128255 = new BitSet(Integer.MAX_VALUE);
    }

    /**
     * @param ipAddress - IP address to put in storage
     */
    public void put(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
        if (ipNumericValue < Integer.MAX_VALUE) {
            this.ipSet0127.set((int) ipNumericValue);
        } else {
            this.ipSet128255.set((int) (ipNumericValue - Integer.MAX_VALUE));
        }
    }

    /**
     * @param ipAddress - IP address to check in storage
     * @return true if IP address is in storage, false otherwise
     */
    public boolean check(String ipAddress) {
        long ipNumericValue = ipToLong(ipAddress);
        if (ipNumericValue < Integer.MAX_VALUE) {
            return this.ipSet0127.get((int) ipNumericValue);
        } else {
            return this.ipSet128255.get((int) (ipNumericValue - Integer.MAX_VALUE));
        }
    }

    /**
     * @param ipNumericValue - numeric value of IP address to get from storage
     * @return String representation of IP address from storage,
     * or String "%IP address% not in the set" if IP address is not in storage
     */
    public String get(long ipNumericValue) {
        String result = longToIp(ipNumericValue);
        if (ipNumericValue < Integer.MAX_VALUE && ipSet0127.get((int) ipNumericValue)) {
            return result;
        } else if (ipNumericValue > Integer.MAX_VALUE && ipSet128255.get((int) (ipNumericValue - Integer.MAX_VALUE))) {
            return result;
        } else {
            return String.format("%s is not in the set", result);
        }
    }

    /**
     * @return number of IP addresses in storage
     */
    public long count() {
        return this.ipSet0127.cardinality() + this.ipSet128255.cardinality();
    }
}
