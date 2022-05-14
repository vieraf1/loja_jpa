package loja.dao;

import javax.persistence.EntityManager;

import loja.model.Cliente;

public class ClienteDAO {
	
	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente Cliente) {
		em.persist(Cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
}
