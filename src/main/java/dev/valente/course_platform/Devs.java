package dev.valente.course_platform;

import dev.valente.course_platform.DTOs.DevsRequestDTO;
import jakarta.persistence.*;

@Table(name = "devs")
@Entity(name = "devs")
public class Devs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Devs(DevsRequestDTO devs){
        this.name = devs.name();
        this.password = devs.password();
    }

    public Devs(){

    }
}
