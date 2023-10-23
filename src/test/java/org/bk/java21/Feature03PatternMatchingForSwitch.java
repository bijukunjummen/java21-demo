package org.bk.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Feature03PatternMatchingForSwitch {

    @Test
    void testMotivation() {
        String formatted = "unknown";
        Object obj = 10;
        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        assertThat(formatted).isEqualTo("int 10");
    }

    @Test
    void testPatternSwitch() {
        Object obj = 10;
        String formatted = switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };

        assertThat(formatted).isEqualTo("int 10");
    }

    @Test
    void testPatternSwitchWithNull() {
        Object obj = null;
        String formatted = switch (obj) {
            case null -> "Oops!";
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };

        assertThat(formatted).isEqualTo("Oops!");
    }

    @Test
    void testCaseRefinementWithGuardedCaseLabel() {
        Object response = "NO";
        switch (response) {
            case null -> {
            }
            case String s -> {
                if (s.equalsIgnoreCase("YES"))
                    System.out.println("You got it");
                else if (s.equalsIgnoreCase("NO"))
                    System.out.println("Shame");
                else
                    System.out.println("Sorry?");
            }
            default -> throw new IllegalStateException("Unexpected type: " + response);
        }


        switch (response) {
            case null -> {
            }
            case String s when s.equalsIgnoreCase("YES") -> {
                System.out.println("You got it");
            }
            case String s when s.equalsIgnoreCase("NO") -> {
                System.out.println("Shame");
            }
            case String s -> {
                System.out.println("Sorry?");
            }
            default -> throw new IllegalStateException("Unexpected type: " + response);
        }
    }

    @Test
    void testExhaustiveness1() {
        Object o = "hello";

        String r = switch (o) {
            case String s -> "A string: " + s;
            case CharSequence cs -> "A char sequence of length: " + cs.length();
            default -> "Unknown";
        };
    }


    sealed interface S permits A, B, C {
    }

    final class A implements S {
    }

    final class B implements S {
    }

    record C(int i) implements S {
    }

    @Test
    void testExhaustivenessWithSealedClasses() {
        S s = new C(10);
        int r = switch (s) {
            case A a -> 1;
            case B b -> 2;
            case C c -> 3;
        };
        assertThat(r).isEqualTo(3);
    }

    @Test
    void testRecordPatternsSwitch() {
        Address address = new Address("1 Bowerman Dr", "Beaverton", "OR");
        switch (address) {
            case Address(String street, String city, String state) -> {
                assertThat(street).isEqualTo("1 Bowerman Dr");
                assertThat(city).isEqualTo("Beaverton");
                assertThat(state).isEqualTo("OR");
            }
        }
    }

    record Address(String street, String city, String state) {
    }

    record Person(String name, String phone, Address address) {
    }
}
