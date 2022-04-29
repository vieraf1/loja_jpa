package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import loja.dao.ProdutoDAO;
import loja.model.Produto;
import loja.model.enums.CategoriaEnum;
import loja.util.JPAUtil;

public class CadastroProdutos {

	public static void main(String[] args) {
		Produto celular = new Produto("Xiaomi Redmi 8 pro", "Celular da empresa Xiaomi", new BigDecimal(2000),
				CategoriaEnum.CELULAR);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);

		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}
