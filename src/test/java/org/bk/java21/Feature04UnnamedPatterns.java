package org.bk.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Feature04UnnamedPatterns {
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
