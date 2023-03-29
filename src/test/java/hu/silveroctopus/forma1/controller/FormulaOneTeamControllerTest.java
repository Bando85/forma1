package hu.silveroctopus.forma1.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.silveroctopus.forma1.dto.FormulaOneTeamDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.datasource.url=jdbc:h2:mem:test"
})
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureDataJpa
class FormulaOneTeamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testListAllTeams() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/formulaoneteams"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<FormulaOneTeamDto> results = objectMapper.readValue(response, new TypeReference<>() {});

        assertEquals( 6, results.size());
    }

    @Test
    void testGetById() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/formulaoneteam/1"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        FormulaOneTeamDto results = objectMapper.readValue(response, new TypeReference<>() {});

        assertEquals( 1L, results.id());
    }
}
