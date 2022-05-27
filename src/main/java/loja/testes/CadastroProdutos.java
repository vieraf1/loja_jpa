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
		cadastrarProduto();
		Long id = 1l;
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		
		Produto p = produtoDao.buscarPorId(id);
		System.out.println(p);
		
		produtoDao.buscarTodos().forEach(p2 -> System.out.println(p2));
		produtoDao.buscarPorNome("Xiaomi Redmi 8 pro").forEach(p2 -> System.out.println(p2));
		produtoDao.buscarPorNome("Xiaomi Redmi").forEach(p2 -> System.out.println(p2));
		produtoDao.buscarPorNomeCategoria("Celulares").forEach(p2 -> System.out.println(p2));
	
		System.out.println(produtoDao.buscarPorNomeLimitado("Xiaomi Redmi 8 pro"));
	}

	private static void cadastrarProduto() {
		Categoria categoria = new Categoria("Celulares", "Tipo 1");

		Produto celular = new Produto("Xiaomi Redmi 8 pro", "Celular da empresa Xiaomi", new BigDecimal(2000),
				categoria);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
