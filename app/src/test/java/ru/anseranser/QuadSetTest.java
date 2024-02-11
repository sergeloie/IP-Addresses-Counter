package ru.anseranser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadSetTest {

    @Test
    void set() {

        QuadSet quadset = new QuadSet();
        quadset.set("0.0.0.0");
        assertEquals(1, quadset.cardinality());
        quadset.set("255.255.255.255");
        assertEquals(2, quadset.cardinality());
        quadset.set("127.0.0.1");
        quadset.set("127.0.0.1");
        quadset.set("127.0.0.1");
        quadset.set("127.0.0.1");
        assertEquals(3, quadset.cardinality());
    }
}
