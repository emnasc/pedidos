package com.emanoel.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoRequest {
    private Long numeroControle; //TODO: este número não pode ser cadastrado novamente; auto generate se não for enviado?
    private LocalDateTime dataCadastro; //opcional
    private String nome;
    private Long codigoCliente;
    private Integer quantidade; //opcional, 1 por padrão
    private BigDecimal valorUnitario;
}
