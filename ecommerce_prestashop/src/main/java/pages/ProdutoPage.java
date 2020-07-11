package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {
	
	private WebDriver driver;

	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}

	private By  labelNomeProdutos = By.cssSelector("h1[itemprop='name']");
	private By  labelPrecoProdutos = By.cssSelector("span[itemprop='price']");
	private By  listSize = By.cssSelector("select[id='group_1']");
	private By  inputColor = By.cssSelector("input[value='11']");
	private By  inputQtd = By.cssSelector("input[id='quantity_wanted']");
	private By  btnAddToCart = By.cssSelector("button[class*='add-to-cart']");
	
	
	public String obterNomeProduto(int indice) {
		return driver.findElement(labelNomeProdutos).getText();
	}

	public String obterPrecoProduto(int indice) {
		return driver.findElement(labelPrecoProdutos).getText();
	}
	
	public List<String> obterOpcoesSelecionadas() {
		List<WebElement> elementosSelecionados = encontrarDropDownSize().getAllSelectedOptions();
		
		List<String> listaOpcoesSelecionadas = new ArrayList();
		for (WebElement elemento : elementosSelecionados) {
			listaOpcoesSelecionadas.add(elemento.getText());
		}
		return listaOpcoesSelecionadas;
	}
	
	public Select encontrarDropDownSize() {
		return new Select(driver.findElement(listSize));	
	}

	public void selecionarOpcaoDropDown(String opcao) {
		encontrarDropDownSize().selectByVisibleText(opcao);
	}

	public void selecionarCorPreta() {
		driver.findElement(inputColor).click();
	}
	
	public void alterarQuantidade(int quantidade) {
		driver.findElement(inputQtd).clear();
		driver.findElement(inputQtd).sendKeys(Integer.toString(quantidade));
	}
	
	public ModalProdutoPage clicarBotaoAddToCart() {
		driver.findElement(btnAddToCart).click();
		return new ModalProdutoPage(driver);
	}

}
