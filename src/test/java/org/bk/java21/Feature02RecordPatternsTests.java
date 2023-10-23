package org.bk.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// JEP 440
class Feature02RecordPatternsTests {

    @Test
    void testMotivation() {
        Object s = "hello";
        if (s instanceof String) {
            String r = (String) s;
            assertThat(r).isEqualTo("hello");
        } else {
            throw new RuntimeException("Should not reach this point!!");
        }

        if (s instanceof String r) {
            assertThat(r).isEqualTo("hello");
        } else {
            throw new RuntimeException("Should not reach this point!!");
        }

    }
    @Test
    void testPatternMatchingForRecords() {
        Address address = new Address("1 Bowerman Dr", "Beaverton", "OR");

        if (address instanceof Address(var st, var city, var state)) {
            assertThat(st).isEqualTo("1 Bowerman Dr");
            assertThat(city).isEqualTo("Beaverton");
            assertThat(state).isEqualTo("OR");
        }
    }
    
    record Address(String street, String city, String state) {
    }

    record Person(String name, String phone, Address address) {
    }
}
