package loja.dao;

import javax.persistence.EntityManager;

import loja.model.Pedido;

public class PedidoDAO {
	
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido Pedido) {
		em.persist(Pedido);
	}
}
