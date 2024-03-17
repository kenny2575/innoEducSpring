package ru.inno.educ.springfilereadwrite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logins_id_gen")
    @SequenceGenerator(name = "logins_id_gen", sequenceName = "logins_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "access_date")
    private Date accessDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    private String application;

}
