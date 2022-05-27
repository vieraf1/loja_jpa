package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import loja.dao.CategoriaDAO;
import loja.dao.ClienteDAO;
import loja.dao.PedidoDAO;
import loja.dao.ProdutoDAO;
import loja.model.Categoria;
import loja.model.Cliente;
import loja.model.ItemPedido;
import loja.model.Pedido;
import loja.model.Produto;
import loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l);

		em.close();
		System.out.println(pedido.getCliente().getDadosPessoais().getNome());
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES", "Tipo 1");
		Categoria videogames = new Categoria("VIDEOGAMES", "Tipo 1");
		Categoria informatica = new Categoria("INFORMATICA", "Tipo 1");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

		Cliente cliente = new Cliente("Rodrigo", "123456");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido, macbook));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		PedidoDAO PedidoDAO = new PedidoDAO(em);

		em.getTransaction().begin();

		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);

		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(macbook);

		clienteDAO.cadastrar(cliente);

		PedidoDAO.cadastrar(pedido);
		PedidoDAO.cadastrar(pedido2);

		em.getTransaction().commit();
		em.close();
	}

}
