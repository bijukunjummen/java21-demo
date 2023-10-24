package org.bk.java21;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedSet;

import static org.assertj.core.api.Assertions.assertThat;

// JEP 431: Sequenced Collections
class Feature05SequencedCollectionsTests {

    @Test
    void testMotivation() {
        List<String> list = List.of("one", "two", "three", "four", "five");

        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(list.size() - 1)).isEqualTo("five");
    }

    @Test
    void testListViaSequencedCollections() {
        SequencedCollection<String> list = List.of("one", "two", "three", "four", "five");

        assertThat(list.getFirst()).isEqualTo("one");
        assertThat(list.getLast()).isEqualTo("five");
    }

    @Test
    void testSequencedSet() {
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addAll(List.of("one", "two", "three"));
        assertThat(set.getFirst()).isEqualTo("one");
        assertThat(set.getLast()).isEqualTo("three");
    }
}
