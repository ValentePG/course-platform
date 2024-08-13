package dev.valente.course_platform.devs;



import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestClient;

import static dev.valente.course_platform.common.DevsConstants.*;
import dev.valente.course_platform.common.DataHolder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestPropertySource(properties = "server.port=8081")
@Sql(scripts = {"/import_devssql.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/remove_devssql.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DevsIT {

    @Autowired
    private RestClient restClient;

    @Autowired
    private DataHolder dataHolder;

    @Value("${devs.test-uuid}")
    private String defaultUUID;

    @Test
    public void saveDev_ReturnsCreated(){

        ResponseEntity<DevsResponseDTO> teste = restClient
                .post()
                .uri("http://localhost:8081/api/devs")
                .body(DEVS_CREATION_REQUEST_DTO_VALID)
                .retrieve()
                .toEntity(DevsResponseDTO.class);

        var response = teste.getBody();
        this.dataHolder.setId(response.id());
        this.dataHolder.setUserName(response.userName());

        assertThat(response.id()).isNotNull();
        assertThat(response.userName()).isEqualTo(DEVS_CREATION_REQUEST_DTO_VALID.userName());
        assertThat(response).isInstanceOf(DevsResponseDTO.class);
        assertThat(teste.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void findDevById_ReturnsDevsResponseDTO(){
        UUID id = UUID.fromString(defaultUUID);

        ResponseEntity<DevsResponseDTO> teste = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(8081)
                        .path("/api/devs/id")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .toEntity(DevsResponseDTO.class);


        var response = teste.getBody();

        assertThat(response.userName()).isEqualTo("JOHN");
        assertThat(response).isInstanceOf(DevsResponseDTO.class);
        assertThat(teste.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete_ReturnsNoContent(){
        UUID id = UUID.fromString(defaultUUID);

        ResponseEntity<Void> teste = restClient
                .delete()
                .uri("http://localhost:8081/api/devs/{id}", id)
                .retrieve()
                .toBodilessEntity();

        assertThat(teste.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
