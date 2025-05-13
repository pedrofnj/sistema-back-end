package com.igrejasobrenatural.sistemas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_usuario")
public class RoleUsuario {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private String role;
}
