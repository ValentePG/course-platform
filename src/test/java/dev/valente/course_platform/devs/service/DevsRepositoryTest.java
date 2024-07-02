//package dev.valente.course_platform.devs.service;
//
//import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
//import dev.valente.course_platform.devs.Devs;
//import dev.valente.course_platform.devs.repository.DevsRepository;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@DataJpaTest
//@ActiveProfiles("test")
//class DevsRepositoryTest {
//
//    @Autowired
//    EntityManager entityManager;
//
//    @Autowired
//    DevsRepository devsRepository;
//
//    @Test
//    void getAllDevs() {
//
//    }
//
//    @Test
//    @DisplayName("Should save Dev succesfully from DB")
//    void saveDevSucess() {
//
//        // AAA
//
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }
//
//    @Test
//    @DisplayName("Should return a error")
//    void saveDevFail() {
//        // AAA
//
//        // PRÓXIMO PASSO //
//
//        // Assert
//
//    }
//
//
//    @Test
//    @DisplayName("Should get Dev successfully from DB")
//    void findDevByIdCaseSuccess() {
//
//        DevsCreationRequestDTO data = new DevsCreationRequestDTO("GABRIEL", "303030");
//        Devs dev = this.createUser(data);
//
//        Optional<Devs> result = this.devsRepository.findById(dev.getId());
//
//        assertThat(result.isPresent()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Should not get Dev when dev not exists")
//    void findDevByIdCaseFail() {
//
//        UUID id = UUID.randomUUID();
//
//        Optional<Devs> result = this.devsRepository.findById(id);
//
//        assertThat(result.isEmpty()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Should get Dev successfully from DB")
//    void findDevByUserNameCaseSucess(){
//        DevsCreationRequestDTO data = new DevsCreationRequestDTO("GABRIEL", "303030");
//        Devs dev = this.createUser(data);
//
//        Optional<Devs> result = this.devsRepository.findDevsByUserName(dev.getUserName());
//
//        assertThat(result.isPresent()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Should get Dev successfully from DB")
//    void findDevByUserNameCaseFail(){
//
//
//        Optional<Devs> result = this.devsRepository.findDevsByUserName("GABRIEL");
//
//        assertThat(result.isEmpty()).isTrue();
//    }
//
//
//    private Devs createUser(DevsCreationRequestDTO data){
//        Devs newDevs = new Devs(data.userName(), data.password());
//        this.entityManager.persist(newDevs);
//        return newDevs;
//    }
//}
