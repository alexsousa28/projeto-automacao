package com.example.demo.api.apiServest.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
