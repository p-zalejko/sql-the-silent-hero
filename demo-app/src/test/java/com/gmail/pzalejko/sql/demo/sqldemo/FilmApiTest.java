package com.gmail.pzalejko.sql.demo.sqldemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmApiTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @ParameterizedTest
    @ValueSource(strings = {"/v1/search/filmsById/1", "/v2/search/filmsById/1"})
    void getById(String url) {
        JsonNode actual = callApi(url);
        JsonNode expected = toJsonNode("""
                {"id":1,"title":"Academy Dinosaur","length":86}
                """);

        assertThat(actual).isEqualTo(expected);
    }

    private JsonNode callApi(String url) {
        var responseEntity = restTemplate.getForEntity(url, JsonNode.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        return responseEntity.getBody();
    }

    @SneakyThrows
    private JsonNode toJsonNode(String asString) {
        return objectMapper.readTree(asString);
    }
}
