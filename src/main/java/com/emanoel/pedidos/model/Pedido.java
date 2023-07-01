package com.emanoel.pedidos.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Pedido {
    private Long numeroControle; //TODO: este número não pode ser cadastrado novamente; auto generate se não for enviado?
    private LocalDateTime dataCadastro; //opcional
    private String name;
    private Long valorUnitario;
    private Integer quantidade; //opcional, 1 por padrão
    private Long codigoCliente;
}
