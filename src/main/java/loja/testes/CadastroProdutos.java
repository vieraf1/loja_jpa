package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import loja.dao.ProdutoDAO;
import loja.model.Produto;
import loja.util.JPAUtil;

public class CadastroProdutos {
	
	public static void main(String[] args) {
		Produto celular = new Produto(); 
		celular.setNome("Xiaomi Redmi 8 pro");
		celular.setDescricao("Celular da empresa Xiaomi");
		celular.setPreco(new BigDecimal(2000));
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
	
}
