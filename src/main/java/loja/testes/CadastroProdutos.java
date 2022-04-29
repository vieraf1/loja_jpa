package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import loja.dao.CategoriaDAO;
import loja.dao.ProdutoDAO;
import loja.model.Categoria;
import loja.model.Produto;
import loja.util.JPAUtil;

public class CadastroProdutos {

	public static void main(String[] args) {
		Categoria categoria = new Categoria("Celulares");

		Produto celular = new Produto("Xiaomi Redmi 8 pro", "Celular da empresa Xiaomi", new BigDecimal(2000),
				categoria);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);
		
		produtoDao.remover(celular);
		categoriaDao.remover(categoria);
		
		em.getTransaction().commit();
		em.close();
	}

}
