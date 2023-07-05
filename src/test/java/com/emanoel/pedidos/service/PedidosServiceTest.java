package com.emanoel.pedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.emanoel.pedidos.dto.PedidoRequest;
import com.emanoel.pedidos.model.Pedido;

public class PedidosServiceTest {

    @Autowired
    private PedidosService pedidosService;

    @Test
    public void shouldGive5PercentDiscount_whenAmountIsBiggerThan5() {
        PedidoRequest requestFive = PedidoRequest.builder()
        .quantidade(5)
        .build();

        PedidoRequest requestSix = PedidoRequest.builder()
        .quantidade(6)
        .build();

        PedidoRequest requestNine = PedidoRequest.builder()
        .quantidade(9)
        .build();

        // ------------

        Pedido pedidoFive = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestFive))
        .build();

        Pedido pedidoSix = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestSix))
        .build();

        Pedido pedidoNine = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestNine))
        .build();

        // ------------

        assertNotNull(pedidoFive.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.05), pedidoFive.getPorcentagemDesconto());

        assertNotNull(pedidoSix.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.05), pedidoSix.getPorcentagemDesconto());

        assertNotNull(pedidoNine.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.05), pedidoNine.getPorcentagemDesconto());
    }

    @Test
    public void shouldGive10PercentDiscount_whenAmountIsBiggerThan10() {
        PedidoRequest requestTen = PedidoRequest.builder()
        .quantidade(10)
        .build();

        PedidoRequest requestHundred = PedidoRequest.builder()
        .quantidade(100)
        .build();

        PedidoRequest requestNines = PedidoRequest.builder()
        .quantidade(9999)
        .build();

        // ------------

        Pedido pedidoTen = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestTen))
        .build();

        Pedido pedidoHundred = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestHundred))
        .build();

        Pedido pedidoNines = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestNines))
        .build();

        // ------------

        assertNotNull(pedidoTen.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.10), pedidoTen.getPorcentagemDesconto());

        assertNotNull(pedidoHundred.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.10), pedidoHundred.getPorcentagemDesconto());

        assertNotNull(pedidoNines.getPorcentagemDesconto());
        assertEquals(BigDecimal.valueOf(0.10), pedidoNines.getPorcentagemDesconto());
    }

    @Test
    public void shoulNotdGiveDiscount_whenAmountIsLessThan5() {
        PedidoRequest requestOne = PedidoRequest.builder()
        .quantidade(1)
        .build();

        PedidoRequest requestFour = PedidoRequest.builder()
        .quantidade(4)
        .build();

        // ------------

        Pedido pedidoOne = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestOne))
        .build();

        Pedido pedidoFour = Pedido.builder()
        .porcentagemDesconto(pedidosService.setPorcentagemDesconto(requestFour))
        .build();

        // ------------

        assertNotNull(pedidoOne.getPorcentagemDesconto());
        assertEquals(BigDecimal.ZERO, pedidoOne.getPorcentagemDesconto());

        assertNotNull(pedidoFour.getPorcentagemDesconto());
        assertEquals(BigDecimal.ZERO, pedidoFour.getPorcentagemDesconto());
    }

    @Test
    public void shouldCalculateCorrectValue_whenSingleItemInOrder() {
        PedidoRequest requestOneItem = PedidoRequest.builder()
        .valorUnitario(BigDecimal.TEN)
        .quantidade(1)
        .build();

        Pedido pedidoOneItem = Pedido.builder()
        .quantidade(requestOneItem.getQuantidade())
        .valorUnitario(requestOneItem.getValorUnitario())
        .valorPedido(pedidosService.calcularValorPedido(requestOneItem))
        //.valorTotal(pedidosService.calcularValorTotal(pedidoOneItem)) //TODO: correct initialization problem
        .build();

        assertNotNull(pedidoOneItem.getValorPedido());
        assertNotNull(pedidoOneItem.getValorTotal());
        assertEquals(BigDecimal.valueOf(10), pedidoOneItem.getValorPedido());
        assertEquals(BigDecimal.valueOf(10), pedidoOneItem.getValorTotal());
    }

    @Test
    public void shouldCalculateCorrectValue_whenMultipleItemsInOrder() {
        PedidoRequest requestThreeItems = PedidoRequest.builder()
        .valorUnitario(BigDecimal.TEN)
        .quantidade(3)
        .build();

        Pedido pedidoThreeItems = Pedido.builder()
        .quantidade(requestThreeItems.getQuantidade())
        .valorUnitario(requestThreeItems.getValorUnitario())
        .valorPedido(pedidosService.calcularValorPedido(requestThreeItems))
        //.valorTotal(pedidosService.calcularValorTotal(pedidoThreeItem)) //TODO: correct initialization problem
        .build();

        assertNotNull(pedidoThreeItems.getValorPedido());
        assertNotNull(pedidoThreeItems.getValorTotal());
        assertEquals(BigDecimal.valueOf(30), pedidoThreeItems.getValorPedido());
        assertEquals(BigDecimal.valueOf(30), pedidoThreeItems.getValorTotal());
    }

    /*

    @Test
    public void shouldCalculateCorrectDiscount_whenDiscountPercentIsGiven() {

    }

    @Test
    public void shouldCalculateCorrectDiscount_whenNoDiscountIsGiven() {

    }

    @Test
    public void shouldCalculateTotalAmount_whenDiscountIsGiven() {

    }

    @Test
    public void shouldCalculateTotalAmount_whenNoDiscountIsGiven() {

    }

    */
}
