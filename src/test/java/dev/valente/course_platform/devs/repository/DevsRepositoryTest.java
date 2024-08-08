package dev.valente.course_platform.devs.repository;


import static dev.valente.course_platform.common.DevsConstants.DEVS;

import dev.valente.course_platform.devs.Devs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
public class DevsRepositoryTest {

    @Autowired
    private DevsRepository devsRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void AfterEach(){
        DEVS.setId(null);

    }

    @Test
    public void findAll_WhenNoDeveloperIsRegistered_ReturnsVoidListOfDevs(){

        List<Devs> listDevs = devsRepository.findAll();

        assertThat(listDevs).isEmpty();

    }

    @Sql(scripts = "/import_devs.sql")
    @Test
    public void findAll_WhenAtLeastOneDeveloperIsRegistered_ReturnsListOfDevs(){
        List<Devs> listDevs = devsRepository.findAll();

        assertThat(listDevs).isNotEmpty();
        assertThat(listDevs.size()).isEqualTo(4);
    }

    @Test
    public void saveDev_WithValidData_ReturnsDevs(){
        Devs devs = devsRepository.save(DEVS);

        Devs sut = testEntityManager.find(Devs.class, devs.getId());

        System.out.println(devs);

        assertThat(sut).isNotNull();
        assertThat(sut.getUserName()).isEqualTo(DEVS.getUserName());
        assertThat(sut.getPassword()).isEqualTo(DEVS.getPassword());
    }

    @Test
    public void saveDev_WithInvalidData_ThrowsException(){

        Devs emptyDevs = new Devs();

        Devs invalidDevs = new Devs("", "");

        assertThatThrownBy(() -> {
            devsRepository.save(invalidDevs);
            devsRepository.flush();
        });

        assertThatThrownBy(() -> {
            devsRepository.save(emptyDevs);
            devsRepository.flush();
        });
    }

    @Test
    public void saveDev_WithExistingName_ThrowsException(){
        Devs devs = testEntityManager.persistAndFlush(DEVS);
        testEntityManager.detach(devs);
        devs.setId(null);

        assertThatThrownBy(() -> {
            devsRepository.save(devs);
            devsRepository.flush();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findDevById_ByExistingId_ReturnsDevs(){
        Devs devs = testEntityManager.persistAndFlush(DEVS);

        Optional<Devs> devsTest = devsRepository.findById(devs.getId());

        assertThat(devsTest).isNotEmpty();
    }

    @Test
    public void findDevById_ByUnexistingId_ReturnsEmpty(){

        Optional<Devs> devsTest = devsRepository.findById(UUID.randomUUID());

        assertThat(devsTest).isEmpty();
    }

    @Test
    public void findDevByUserName_ByExistingUserName_ReturnsDevs(){
        Devs devs = testEntityManager.persistAndFlush(DEVS);

        Optional<Devs> devsTest = devsRepository.findDevsByUserName(devs.getUserName());

        assertThat(devsTest).isNotEmpty();
    }

    @Test
    public void findDevByUserName_ByUnexistingUserName_ReturnsEmpty(){

        Optional<Devs> devsTest = devsRepository.findDevsByUserName(DEVS.getUserName());

        assertThat(devsTest).isEmpty();
    }


    @Test
    public void deleteById_WithIdExistent_ReturnsDevsDeleted(){
        Devs devs = testEntityManager.persistAndFlush(DEVS);

        devsRepository.deleteById(devs.getId());

        Devs removedDevs = testEntityManager.find(Devs.class, devs.getId());

        assertThat(removedDevs).isNull();

    }

}
