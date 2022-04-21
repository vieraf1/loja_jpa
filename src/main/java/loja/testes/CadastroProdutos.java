package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import loja.model.Produto;

public class CadastroProdutos {
	
	public static void main(String[] args) {
		Produto celular = new Produto(); 
		celular.setNome("Xiaomi Redmi 8 pro");
		celular.setDescricao("Celular da empresa Xiaomi");
		celular.setPreco(new BigDecimal(2000));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}
	
}
