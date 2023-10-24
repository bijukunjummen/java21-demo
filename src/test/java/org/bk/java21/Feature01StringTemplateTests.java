package org.bk.java21;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;
import static org.assertj.core.api.Assertions.assertThat;

// JEP - 430: String Templates (Preview)
class Feature01StringTemplateTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testStringTemplateMotivation() {
        // String s = x + " plus " + y + " equals " + (x + y);
        int x = 10;
        int y = 20;
        String s = new StringBuilder()
                .append(x)
                .append(" plus ")
                .append(y)
                .append(" equals ")
                .append(x + y)
                .toString();
        assertThat(s).isEqualTo("10 plus 20 equals 30");
    }

    @Test
    void testStringTemplate() {
        int x = 10;
        int y = 20;
        String s = STR. "\{ x } plus \{ y } equals \{ x + y }" ;
        assertThat(s).isEqualTo("10 plus 20 equals 30");
    }

    @Test
    void basicInterpolation() {
        String first = "John";
        String last = "Smith";
        String info = STR. "My first name is \{ first } and my last name is \{ last }" ;
        assertThat(info).isEqualTo("My first name is John and my last name is Smith");
    }

    @Test
    void basicStringTemplate() {
        String first = "John";
        String last = "Smith";
        StringTemplate st = RAW. "My first name is \{ first } and my last name is \{ last }" ;
        System.out.println(STR. "Fragments = \{ st.fragments() }" );
        System.out.println(STR. "Values = \{ st.values() }" );
        String result = String.join("\\{}", st.fragments());
        System.out.println(result);
        assertThat(st.interpolate()).isEqualTo("My first name is John and my last name is Smith");
    }

    @Test
    void testBlocks() throws Exception {
        String json = """
                   {
                        "name": "John Smith",
                        "address": {
                            "street": "1 Bowerman Dr",
                            "city": "Beaverton",
                            "state": "OR"
                        }
                   }             
                """;

        Person person = objectMapper.readValue(json, Person.class);

        assertThat(person)
                .isEqualTo(
                        new Person("John Smith", null,
                                new Address("1 Bowerman Dr", "Beaverton", "OR")));
    }

    @Test
    void testBlockInterpolation() throws Exception {
        String street = "1 Bowerman Dr";
        String name = "John Smith";
        String city = "Beaverton";

        String json = STR. """
                   {
                        "name": "\{ name }",
                        "address": {
                            "street": "\{ street }",
                            "city": "\{ city }",
                            "state": "OR"
                        }
                   }
                """ ;

        Person person = objectMapper.readValue(json, Person.class);

        assertThat(person)
                .isEqualTo(
                        new Person("John Smith", null,
                                new Address("1 Bowerman Dr", "Beaverton", "OR")));
    }


    @Test
    void interpolationWithExpression() {
        String name = "John Smith";
        int age = 19;
        String info = STR. "My name is \{ age > 18 ? "Mr. " + name : name }" ;
        assertThat(info).isEqualTo("My name is Mr. John Smith");
    }

    @Test
    void interpolationWithFormatting() {
        Rectangle[] zone = new Rectangle[]{
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };
        String table = FMT. """
            Description     Width    Height     Area
            %-12s\{ zone[0].name }  %7.2f\{ zone[0].width }  %7.2f\{ zone[0].height }     %7.2f\{ zone[0].area() }
            %-12s\{ zone[1].name }  %7.2f\{ zone[1].width }  %7.2f\{ zone[1].height }     %7.2f\{ zone[1].area() }
            %-12s\{ zone[2].name }  %7.2f\{ zone[2].width }  %7.2f\{ zone[2].height }     %7.2f\{ zone[2].area() }
            \{ " ".repeat(28) } Total %7.2f\{ zone[0].area() + zone[1].area() + zone[2].area() }
        """ ;

        System.out.println(table);
    }

    @Test
    void customProcessor() {
        var INTER = StringTemplate.Processor.of((StringTemplate st) -> {
            StringBuilder sb = new StringBuilder();
            Iterator<String> fragIter = st.fragments().iterator();
            for (Object value : st.values()) {
                sb.append(fragIter.next().toUpperCase());
                sb.append(value);
            }
            sb.append(fragIter.next());
            return sb.toString();
        });
        int x = 10, y = 20;
        String s = INTER. "\{ x } plus \{ y } equals \{ x + y }" ;
        assertThat(s).isEqualTo("10 PLUS 20 EQUALS 30");
    }

    record Rectangle(String name, double width, double height) {
        double area() {
            return width * height;
        }
    }


    record Address(String street, String city, String state) {
    }

    record Person(String name, String phone, Address address) {
    }

}
