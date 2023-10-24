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
        }

        if (s instanceof String r) {
            assertThat(r).isEqualTo("hello");
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

    @Test
    void testMoreExtensiveRecordPattern() {
        Address address = new Address("1 Bowerman Dr", "Beaverton", "OR");
        Person person = new Person("John Smith", "", address);

        if (person instanceof Person(var name, var phone, Address(var st, var city, var state))) {
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
