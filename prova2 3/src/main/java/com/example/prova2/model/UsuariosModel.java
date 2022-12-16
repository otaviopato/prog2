package com.example.prova2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length=30)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String cpf;
    @Column(length=11)
    private String phone;
    private String address;
}