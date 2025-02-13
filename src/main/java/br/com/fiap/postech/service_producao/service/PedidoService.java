package br.com.fiap.postech.service_producao.service;

import br.com.fiap.postech.service_producao.entity.Pedido;
import br.com.fiap.postech.service_producao.enums.StatusPedido;
import br.com.fiap.postech.service_producao.exception.PedidoNotFoundException;
import br.com.fiap.postech.service_producao.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(String id) throws PedidoNotFoundException {
        return pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
    }

    public Pedido receberPedido(String id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setStatus(StatusPedido.RECEBIDO);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarStatusPedido(String id, StatusPedido status) throws PedidoNotFoundException {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
}
