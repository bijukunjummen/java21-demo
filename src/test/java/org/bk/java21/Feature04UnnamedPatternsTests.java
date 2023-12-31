package org.bk.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// JEP 443: Unnamed Patterns and Variables (Preview)
class Feature04UnnamedPatternsTests {
    @Test
    void testMotivation() {
        Address address = new Address("1 Bowerman Dr", "Beaverton", "OR");
        Person person = new Person("John Smith", "", address);

        if (person instanceof Person(var name, var phone, Address(var st, var city, var state))) {
            assertThat(st).isEqualTo("1 Bowerman Dr");
            assertThat(city).isEqualTo("Beaverton");
        }
    }

    @Test
    void testUnNamedPatterns() {
        Address address = new Address("1 Bowerman Dr", "Beaverton", "OR");
        Person person = new Person("John Smith", "", address);

        if (person instanceof Person(_, _, Address(var st, var city, _))) {
            assertThat(st).isEqualTo("1 Bowerman Dr");
            assertThat(city).isEqualTo("Beaverton");
        }
    }

    record Address(String street, String city, String state) {
    }

    record Person(String name, String phone, Address address) {
    }
}
