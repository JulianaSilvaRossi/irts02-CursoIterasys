package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutoPage {
	
	private WebDriver driver;

	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}

	private By  labelNomeProdutos = By.cssSelector("h1[itemprop='name']");
	private By  labelPrecoProdutos = By.cssSelector("span[itemprop='price']");
	
	public String obterNomeProduto(int indice) {
		return driver.findElement(labelNomeProdutos).getText();
	}

	public String obterPrecoProduto(int indice) {
		return driver.findElement(labelPrecoProdutos).getText();
	}

}
