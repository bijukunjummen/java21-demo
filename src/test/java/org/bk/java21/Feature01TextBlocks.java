package org.bk.java21;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class Feature01TextBlocks {

    @Autowired
    private ObjectMapper objectMapper;

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

    record Address(String street, String city, String state) {
    }

    record Person(String name, String phone, Address address) {
    }

}
