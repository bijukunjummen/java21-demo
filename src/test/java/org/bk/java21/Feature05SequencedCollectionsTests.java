package org.bk.java21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Feature05SequencedCollectionsTests {

    @Test
    void testListExtractElements() {
        List<String> list = List.of("one", "two", "three", "four", "five");

        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(list.size() - 1)).isEqualTo("five");
    }

    @Test
    void testListViaSequencedCollections() {
        List<String> list = List.of("one", "two", "three", "four", "five");

        assertThat(list.getFirst()).isEqualTo("one");
        assertThat(list.getLast()).isEqualTo("five");
    }
}
