package com.emanoel.pedidos.model;

import java.math.BigDecimal;
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
    private String nome;
    private Long codigoCliente;
    private Integer quantidade; //opcional, 1 por padrão
    private BigDecimal valorUnitario;

    private BigDecimal porcentagemDesconto;
    private BigDecimal valorDesconto;
    private BigDecimal valorPedido;
    private BigDecimal valorTotal;
}