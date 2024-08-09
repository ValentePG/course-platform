package dev.valente.course_platform.devs;



import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;

import java.util.UUID;

import static dev.valente.course_platform.common.DevsConstants.*;
import dev.valente.course_platform.devs.Aux;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestPropertySource(properties = "server.port=8081")
public class DevsIT {

    @Autowired
    private RestClient restClient;

    @Autowired
    private Aux aux;

    @Test
    public void saveDev_ReturnsCreated(){

        var teste = restClient
                .post()
                .uri("http://localhost:8081/api/devs")
                .body(DEVS_CREATION_REQUEST_DTO_VALID)
                .retrieve()
                .toEntity(DevsResponseDTO.class).getBody();

        this.aux.setId(teste.id());
        this.aux.setUserName(teste.userName());
        String Output = String.format("Usuário %s foi criado com sucesso!", this.aux.getUserName());
        System.out.println(Output);
        assertThat(teste).isInstanceOf(DevsResponseDTO.class);
        assertThat(teste.userName()).isEqualTo(DEVS_CREATION_REQUEST_DTO_VALID.userName());
    }

    @Test
    public void delete_ReturnsNoContent(){

        var teste = restClient
                .delete()
                .uri("http://localhost:8081/api/devs/{id}", this.aux.getId())
                .retrieve();
        String Output = String.format("Usuário %s foi deletado com sucesso!", this.aux.getUserName());
        System.out.println(Output);
        assertThat(teste.toBodilessEntity().getStatusCode().is2xxSuccessful()).isTrue();
    }
}
