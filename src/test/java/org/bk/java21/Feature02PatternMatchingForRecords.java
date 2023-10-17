package org.bk.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Feature02PatternMatchingForRecords {

    @Test
    void testPatternMatchingForRecords() {
        Address address = new Address("John Smith", "1 Bowerman Dr", "Beaverton");

        if (address instanceof Address(var name, var st, var city)) {
            assertThat(name).isEqualTo("John Smith");
            assertThat(st).isEqualTo("1 Bowerman Dr");
            assertThat(city).isEqualTo("Beaverton");
        }
    }

    @Test
    void testRecordPatternsSwitch() {
        Address address = new Address("John Smith", "1 Bowerman Dr", "Beaverton");
        switch (address) {
            case Address(String n, String s, String c) -> {
                assertThat(n).isEqualTo("John Smith");
                assertThat(s).isEqualTo("1 Bowerman Dr");
                assertThat(c).isEqualTo("Beaverton");
            }
        }
    }

    record Address(String name, String street, String city) {
    }
}
