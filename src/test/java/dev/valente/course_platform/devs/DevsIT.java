package dev.valente.course_platform.devs;



import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import static dev.valente.course_platform.common.DevsConstants.*;
import dev.valente.course_platform.common.HolderData;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestPropertySource(properties = "server.port=8081")
public class DevsIT {

    @Autowired
    private RestClient restClient;

    @Autowired
    private HolderData aux;

    @Test
    public void saveDev_ReturnsCreated(){

        ResponseEntity<DevsResponseDTO> teste = restClient
                .post()
                .uri("http://localhost:8081/api/devs")
                .body(DEVS_CREATION_REQUEST_DTO_VALID)
                .retrieve()
                .toEntity(DevsResponseDTO.class);

        var response = teste.getBody();

        this.aux.setId(response.id());
        this.aux.setUserName(response.userName());

        String Output = String.format("Usuário %s foi criado com sucesso!", this.aux.getUserName());
        System.out.println(Output);

        assertThat(teste.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void delete_ReturnsNoContent(){

        ResponseEntity<Void> teste = restClient
                .delete()
                .uri("http://localhost:8081/api/devs/{id}", this.aux.getId())
                .retrieve()
                .toBodilessEntity();

        String Output = String.format("Usuário %s foi deletado com sucesso!", this.aux.getUserName());
        System.out.println(Output);

        assertThat(teste.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
