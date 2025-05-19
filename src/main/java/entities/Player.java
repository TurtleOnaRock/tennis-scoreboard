package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Players")
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

}
