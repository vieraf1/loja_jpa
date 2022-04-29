package loja.dao;

import javax.persistence.EntityManager;

import loja.model.Categoria;

public class CategoriaDAO {
	
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}
}
