package homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Annotation Test
import org.junit.Test;

import base.BaseTests;
import pages.LoginPage;
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

	@Test
	public void testValidarDetalhesProduto_DescricaoVAlorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		ProdutoPage produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto(indice);
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto(indice);
		
		assertEquals(nomeProduto_ProdutoPage, nomeProduto_HomePage.toUpperCase());
		assertEquals(precoProduto_ProdutoPage, precoProduto_HomePage.toUpperCase());
	}
	
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		LoginPage loginPage = homePage.clicarBotaoSignIn();
		loginPage.preencherEmail("automacao@tests.com");
		loginPage.preencherPassword("123456");
		loginPage.clicarBotaoSignIn();
		assertTrue(homePage.verificarAutenticacao("Automacao Tests"));
	}

}
