package com.emanoel.pedidos.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.emanoel.pedidos.dto.PedidoRequest;
import com.emanoel.pedidos.model.Pedido;

@Service
public class PedidosService {
    private final Integer AMOUNT_FIVE = 5;
    private final Integer AMOUNT_TEN = 10;

    private final BigDecimal PERCENT_FIVE = BigDecimal.valueOf(0.05);
    private final BigDecimal PERCENT_TEN = BigDecimal.valueOf(0.10);

    public BigDecimal setPorcentagemDesconto(PedidoRequest request) {
        if (request.getQuantidade() >= AMOUNT_TEN) {
           return PERCENT_TEN;
        }

        if (request.getQuantidade() >= AMOUNT_FIVE) {
            return PERCENT_FIVE;
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal calcularValorPedido(PedidoRequest request) {
        return request.getValorUnitario().multiply(BigDecimal.valueOf(request.getQuantidade()));
    }

    public BigDecimal calcularValorDesconto(Pedido pedido) {
        return pedido.getValorPedido().multiply(pedido.getPorcentagemDesconto());
    }

    public BigDecimal calcularValorTotal(Pedido pedido) {
        return pedido.getValorPedido().subtract(pedido.getValorDesconto());
    }
}
