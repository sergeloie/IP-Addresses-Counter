package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.anseranser.IPConverter.textToNumericFormatV4;

public class TestNewMethod {
    @Test
    public void test1() {
        assertEquals(17171717L, textToNumericFormatV4("1.6.5.5"));
        assertEquals(3232238090L, textToNumericFormatV4("192.168.10.10"));
        assertEquals(0L, textToNumericFormatV4("0.0.0.0"));
        assertEquals(4294967295L, textToNumericFormatV4("255.255.255.255"));

        assertEquals(-1L, textToNumericFormatV4("192.168.124.256"));
        assertEquals(-1L, textToNumericFormatV4("192.1684.1.1"));
        assertEquals(-1L, textToNumericFormatV4("192.168.1."));
        assertEquals(-1L, textToNumericFormatV4(".192.168.1.1"));
        assertEquals(-1L, textToNumericFormatV4("192.168.-1.1"));
    }
}
