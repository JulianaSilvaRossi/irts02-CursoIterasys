package homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

	ProdutoPage produtoPage;
	@Test
	public void testValidarDetalhesProduto_DescricaoValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProdutoPorIndice(indice);
		String precoProduto_HomePage = homePage.obterNomeProdutoPorIndice(indice);
		
		produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto(indice);
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto(indice);
		
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
		if(!homePage.verificarAutenticacao("Automacao Tests")) {
			testLoginComSucesso_UsuarioLogado();
		}
		testValidarDetalhesProduto_DescricaoValorIguais();
		
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		
		produtoPage.selecionarOpcaoDropDown("M");
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		
		produtoPage.selecionarCorPreta();
		
		produtoPage.alterarQuantidade(2);
	}

}
