package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Texttonumericformatv4Test {


    // returns the correct numeric format for a valid IPv4 address string
    @Test
    void testValidIpv4Address() {
        assertEquals(17171717L, IPConverter.textToNumericFormatV4("1.6.5.5"));
        assertEquals(3232238090L, IPConverter.textToNumericFormatV4("192.168.10.10"));
        assertEquals(0L, IPConverter.textToNumericFormatV4("0.0.0.0"));
        assertEquals(4294967295L, IPConverter.textToNumericFormatV4("255.255.255.255"));
    }

    // correctly handles IPv4 addresses with leading zeros
    @Test
    void testIpv4AddressWithLeadingZeros() {
        assertEquals(17171717L, IPConverter.textToNumericFormatV4("001.006.005.005"));
        assertEquals(3232238090L, IPConverter.textToNumericFormatV4("192.168.010.010"));
        assertEquals(0L, IPConverter.textToNumericFormatV4("000.000.000.000"));
    }

    // correctly handles IPv4 addresses with consecutive dots
    @Test
    void testIpv4AddressWithConsecutiveDots() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192..168.1.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168..1.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1..1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1.1."));
    }

    // returns -1 for IPv4 addresses with more than 4 octets
    @Test
    void testIpv4AddressWithMoreThan4Octets() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1.1.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1.1.1.1"));
    }

    // returns -1 for IPv4 addresses with less than 4 octets
    @Test
    void testIpv4AddressWithLessThan4Octets() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192"));
    }

    // returns -1 for IPv4 addresses with octets greater than 255
    @Test
    void testIpv4AddressWithOctetsGreaterThan255() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("256.168.1.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.1684.1.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.256.1"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("192.168.1.256"));
    }

    @Test
    void testIpv4AddressTooLong() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("0001.168.111.111"));
    }

    @Test
    void testIpv4AddressWithLetters() {
        assertEquals(-1L, IPConverter.textToNumericFormatV4("1i1.168.111.111"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("111.16a.111.111"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("111.168.11b.111"));
        assertEquals(-1L, IPConverter.textToNumericFormatV4("111.168.111.c11"));
    }

}
