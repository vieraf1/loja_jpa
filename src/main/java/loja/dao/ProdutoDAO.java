package loja.dao;

import java.math.BigDecimal;
import java.util.List;

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
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p where p.nome = :nome";
		
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPorNomeLimitado(String nome) {
		String jpql = "SELECT p.preco FROM Produto p where p.nome = :nome";
		
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorNomeCategoria(String nomeCategoria) {
		String jpql = "SELECT p FROM Produto p where p.categoria.nome = :nome";
		
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nomeCategoria)
				.getResultList();
	}
}
