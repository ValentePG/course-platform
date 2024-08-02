package dev.valente.course_platform.devs.repository;


import static dev.valente.course_platform.common.DevsConstants.DEVS;

import dev.valente.course_platform.devs.Devs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
public class DevsRepositoryTest {

    @Autowired
    private DevsRepository devsRepository;

    @Autowired
    private TestEntityManager testEntityManager;

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
}
