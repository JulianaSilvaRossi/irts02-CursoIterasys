package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import homepage.ProdutoPage;


public class HomePage {
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
		
	List<WebElement> listaProdutos = new ArrayList<WebElement>();
	private By  listProdutos = By.cssSelector("div[class='product-description']");
	private By  labelProdutoCarrinho = By.cssSelector("span[class='cart-products-count']");
	private By  labelDescricaoProdutos = By.cssSelector("div[class='product-description'] a");
	private By  labelPrecoProdutos = By.cssSelector("span[class='price']");
	private By  btnSignIn = By.cssSelector("div[class='user-info'] a");
	private By  labelLogado = By.cssSelector("div[class='user-info'] span");

	public int contarProdutos() {
		carregarListaProdutos();
		return listaProdutos.size();
	}
	
	private void carregarListaProdutos() {
		listaProdutos = driver.findElements(listProdutos);
	}

	public int obterQuantidadeProdutosCarrinho() {
		String qtdProdutosCarrinho = driver.findElement(labelProdutoCarrinho).getText();
		
		qtdProdutosCarrinho = qtdProdutosCarrinho.replace("(", "");
		qtdProdutosCarrinho = qtdProdutosCarrinho.replace(")", "");
		
		int qtdProdutosCarrinhoInt = Integer.parseInt(qtdProdutosCarrinho);
		
		return qtdProdutosCarrinhoInt;
	}

	public String obterNomeProdutoPorIndice(int indice) {
		return driver.findElements(labelDescricaoProdutos).get(indice).getText();
	}

	public String obterPrecoProdutoPorIndice(int indice) {
		return driver.findElements(labelPrecoProdutos).get(indice).getText();
	}

	public ProdutoPage clicarProduto(int indice) {
		driver.findElements(labelDescricaoProdutos).get(indice).click();
		return new ProdutoPage(driver);
	}
	
	public LoginPage clicarBotaoSignIn() {
		driver.findElement(btnSignIn).click();
		return new LoginPage(driver);
	}
	
	public boolean verificarAutenticacao(String texto) {
		return texto.contentEquals(driver.findElement(labelLogado).getText());
	}

}
