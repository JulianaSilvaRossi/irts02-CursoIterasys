package homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

//Annotation Test
import org.junit.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;
import util.Funcoes;

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
	
	ModalProdutoPage modalProdutoPage;
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
		
		modalProdutoPage = produtoPage.clicarBotaoAddToCart();
		
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
	
	// VALORES ESPERADOS
	String esperado_lblNomeProdutoCarrinho = "Hummingbird printed t-shirt";
	Double esperado_lblPrecoProdutoCarrinho = 19.12;
	String esperado_lblSizeCarrinho = "M";
	String esperado_lblColorCarrinho = "Black";
	int esperado_inputQtdCarrinho = 2;
	Double esperado_lblSubtotalCarrinho = esperado_lblPrecoProdutoCarrinho * esperado_inputQtdCarrinho;
	
	int esperado_lblItensDetalheCarrinho = esperado_inputQtdCarrinho;
	Double esperado_lblShippingDetalheCarrinho = esperado_lblSubtotalCarrinho;
	Double esperado_lblTotalSemTaxaDetalheCarrinho = 7.00;
	Double esperado_lblTotalComTaxaDetalheCarrinho = esperado_lblSubtotalCarrinho + esperado_lblTotalSemTaxaDetalheCarrinho ;
	Double esperado_lblImpostosDetalheCarrinho = esperado_lblTotalComTaxaDetalheCarrinho;
	Double esperado_btnProceedToCheckout = 0.00;
	
	CarrinhoPage carrinhoPage;
	
	@Test
	public void testIrParaCarrinho_InformacoesPersistidas() {
		testIncluirProdutoNoCarrinho_ProdutoIncluidoComSucesso();
		carrinhoPage = modalProdutoPage.clicarBotaoProceesToChekout();
		
		System.out.println("TELA DO CARRINHO ");
		
		System.out.println(carrinhoPage.obter_lblNomeProdutoCarrinho());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblPrecoProdutoCarrinho()));
		System.out.println(carrinhoPage.obter_lblSizeCarrinho());
		System.out.println(carrinhoPage.obter_lblColorCarrinho());
		System.out.println(carrinhoPage.obter_inputQtdCarrinho());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblSubtotalCarrinho()));
		
		System.out.println("TELA DO CARRINHO DETALHES");
		System.out.println(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_lblItensDetalheCarrinho()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblShippingDetalheCarrinho()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblTotalSemTaxaDetalheCarrinho()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblTotalComTaxaDetalheCarrinho()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblImpostosDetalheCarrinho()));
		
		assertEquals(esperado_lblNomeProdutoCarrinho, carrinhoPage.obter_lblNomeProdutoCarrinho());
		assertEquals(esperado_lblPrecoProdutoCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblPrecoProdutoCarrinho()));
		assertEquals(esperado_lblSizeCarrinho, carrinhoPage.obter_lblSizeCarrinho());
		assertEquals(esperado_lblColorCarrinho, carrinhoPage.obter_lblColorCarrinho());
		assertEquals(esperado_inputQtdCarrinho, Integer.parseInt(carrinhoPage.obter_inputQtdCarrinho()));
		assertEquals(esperado_lblSubtotalCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblSubtotalCarrinho()));
		
		/*assertEquals(esperado_lblItensDetalheCarrinho, Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_lblItensDetalheCarrinho()));
		assertEquals(esperado_lblShippingDetalheCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblTotalSemTaxaDetalheCarrinho()));
		assertEquals(esperado_lblTotalSemTaxaDetalheCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblTotalSemTaxaDetalheCarrinho()));
		assertEquals(esperado_lblTotalComTaxaDetalheCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblTotalComTaxaDetalheCarrinho()));
		assertEquals(esperado_lblImpostosDetalheCarrinho, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblImpostosDetalheCarrinho()));
		*/
		//assertEquals(esperado_btnProceedToCheckout, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_lblImpostosDetalheCarrinho()));
		
	}
	
	// VALORES ESPERADO
	String esperado_nomeCliente = "Automacao Tests";
	
	CheckoutPage checkoutPage;
	
	public void testIrParaCheckout_FreteMeioPagamentoEnderecoListado() {
		testIrParaCarrinho_InformacoesPersistidas();
		
		// Clicar no botão
		checkoutPage = carrinhoPage.clicar_btnProceedToCheckout();
		
		// Preencher info
		
		// Validar info
		assertEquals(esperado_nomeCliente, checkoutPage.obter_lblNomeClienteCheckout());
		
		checkoutPage.clicar_btnContinueCheckout();
	}

}
