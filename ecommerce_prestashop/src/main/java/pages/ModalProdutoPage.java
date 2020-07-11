package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ModalProdutoPage {
	
	private WebDriver driver;

	public ModalProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By  lblMensagemSucessoNoCarrinho = By.cssSelector("div[class='modal-dialog'] h4[id='myModalLabel']");
	private By  lblDescricaoProduto = By.cssSelector("div[class='modal-dialog'] h6[class*='product-name']");
	private By  lblPrecoProduto = By.cssSelector("div[class='modal-dialog'] p[class*='product-price']");
	private By  listCaracteristicaProduto = By.cssSelector("div[class='modal-dialog'] div[class*='col-md-6']:nth-child(2) span strong");
	private By  lblSubtotal = By.cssSelector("div[class='modal-dialog'] div[class='cart-content'] p:nth-child(2) span[class='value']");
	
	public String obterMensagemProdutoAdicionado() {
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(1000))
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(lblMensagemSucessoNoCarrinho));
		
		return driver.findElement(lblMensagemSucessoNoCarrinho).getText();
	}
	
	public String obterDescricaoProduto() {
		return driver.findElement(lblDescricaoProduto).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(lblPrecoProduto).getText();
	}
	
	public String obterTamanhoProdutoAdicionado() {
		return driver.findElements(listCaracteristicaProduto).get(0).getText();
	}
	
	public String obterCorProdutoAdicionado() {
		return driver.findElements(listCaracteristicaProduto).get(1).getText();
	}
	
	public String obterQtdProdutoAdicionado() {
		return driver.findElements(listCaracteristicaProduto).get(2).getText();
	}
	
	public String obterSubtotal() {
		return driver.findElement(lblSubtotal).getText();
	}

}
