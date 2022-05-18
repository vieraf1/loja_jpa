package loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import loja.vo.RelatorioVendasVo;

public class CadastroPedidos {

	public static void main(String[] args) {
		cadastrarProduto();
		cadastrarCliente();
		cadastrarPedido();
	}

	private static void cadastrarProduto() {
		Categoria categoria = new Categoria("Celulares");

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
	
	private static void cadastrarCliente() {
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		Cliente cliente = new Cliente("Lucas", "12345");
		
		em.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void cadastrarPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		em.getTransaction().begin();
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("total vendido: " + totalVendido);
		
		List<RelatorioVendasVo> relatorio = pedidoDao.relatorioVendas();
		relatorio.forEach(System.out::println);
		
		em.close();
	}

}
