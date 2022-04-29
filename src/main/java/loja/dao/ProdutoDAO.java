package loja.dao;

import javax.persistence.EntityManager;

import loja.model.Produto;

public class ProdutoDAO {
	
	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		em.persist(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		em.remove(produto);
	}
}
