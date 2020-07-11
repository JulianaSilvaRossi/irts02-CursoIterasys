package homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

//Annotation Test
import org.junit.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {
	
	@Test
	public void testContarProdutos_OitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertEquals(8, homePage.contarProdutos());
	}
	
	@Test
	public void testValidarCarrinhoZerado_ZeroItensCarrinho() {
		int produtosCarrinho = homePage.obterQuantidadeProdutosCarrinho();
		assertEquals(0, produtosCarrinho);
	}

	ProdutoPage produtoPage;
	String nomeProduto_ProdutoPage;
	String precoProduto_ProdutoPage;
	@Test
	public void testValidarDetalhesProduto_DescricaoValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProdutoPorIndice(indice);
		String precoProduto_HomePage = homePage.obterPrecoProdutoPorIndice(indice);
		
		produtoPage = homePage.clicarProduto(indice);
		
		nomeProduto_ProdutoPage = produtoPage.obterNomeProduto(indice);
		precoProduto_ProdutoPage = produtoPage.obterPrecoProduto(indice);
		
		assertEquals(nomeProduto_ProdutoPage, nomeProduto_HomePage.toUpperCase());
		assertEquals(precoProduto_ProdutoPage, precoProduto_HomePage.toUpperCase());
	}
	
	LoginPage loginPage;
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		loginPage = homePage.clicarBotaoSignIn();
		loginPage.preencherEmail("automacao@tests.com");
		loginPage.preencherPassword("123456");
		loginPage.clicarBotaoSignIn();
		assertTrue(homePage.verificarAutenticacao("Automacao Tests"));
		
		carregarPaginaInicial();
		
	}
	
	@Test
	public void testIncluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		
		String tamanhoProduto = "M";
		String corProduto = "Black";
		int qtdProduto = 2;
		
		if(!homePage.verificarAutenticacao("Automacao Tests")) {
			testLoginComSucesso_UsuarioLogado();
		}
		testValidarDetalhesProduto_DescricaoValorIguais();
		
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		
		produtoPage.selecionarOpcaoDropDown(tamanhoProduto);
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		
		produtoPage.selecionarCorPreta();
		produtoPage.alterarQuantidade(qtdProduto);
		
		ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddToCart();
		
		assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));
		
		assertEquals(modalProdutoPage.obterDescricaoProduto().toUpperCase(), nomeProduto_ProdutoPage);
		
		String precoProdutoString = modalProdutoPage.obterPrecoProduto();
		precoProdutoString = precoProdutoString.replace("$", "");
		Double precoProduto = Double.parseDouble(precoProdutoString);
		
		assertEquals(modalProdutoPage.obterTamanhoProdutoAdicionado(), tamanhoProduto);
		assertEquals(modalProdutoPage.obterCorProdutoAdicionado(), corProduto);
		assertEquals(modalProdutoPage.obterQtdProdutoAdicionado(), Integer.toString(qtdProduto));
		
		String subtotalString = modalProdutoPage.obterSubtotal();
		subtotalString = subtotalString.replace("$", "");
		Double subtotal = Double.parseDouble(subtotalString);
		
		Double subtotalCalculado = qtdProduto * precoProduto;
		assertEquals(subtotalCalculado, subtotal);
		
	}

}
