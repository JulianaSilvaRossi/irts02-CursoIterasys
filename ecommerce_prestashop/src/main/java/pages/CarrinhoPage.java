package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {
	
	private WebDriver driver;

	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Linha do Produto
	private By  lblNomeProdutoCarrinho = By.cssSelector("div[class='product-line-grid'] a[class*='label']");
	private By  lblPrecoProdutoCarrinho = By.cssSelector("div[class='product-line-grid'] div[class='current-price'] span");
	private By  lblSizeCarrinho = By.cssSelector("div[class='product-line-grid'] div[class*='product-line-grid-body'] div:nth-child(4) span[class='value']");
	private By  lblColorCarrinho = By.cssSelector("div[class='product-line-grid'] div[class*='product-line-grid-body'] div:nth-child(5) span[class='value']");
	private By  inputQtdCarrinho = By.cssSelector("div[class='product-line-grid'] input[name='product-quantity-spin']");
	private By  lblSubtotalCarrinho = By.cssSelector("div[class='product-line-grid'] span[class='product-price'] strong");
	
	// Detalhes Total Carrinho
	private By  lblItensDetalheCarrinho = By.cssSelector("div[class='cart-detailed-totals'] div[id='cart-subtotal-products'] span[class*='subtotal']");
	private By  lblShippingDetalheCarrinho = By.cssSelector("div[class='cart-detailed-totals'] div[id='cart-subtotal-shipping'] span[class='value']");
	private By  lblTotalSemTaxaDetalheCarrinho = By.cssSelector("div[class='cart-detailed-totals'] div[class='card-block cart-summary-totals'] div:nth-child(1) span[class='value']");
	private By  lblTotalComTaxaDetalheCarrinho = By.cssSelector("div[class='cart-detailed-totals'] div[class='card-block cart-summary-totals'] div:nth-child(2) span[class='value']");
	private By  lblImpostosDetalheCarrinho = By.cssSelector("div[class='cart-detailed-totals'] div[class='card-block cart-summary-totals'] div:nth-child(3) span[class='value sub']");
	
	private By  btnProceedToCheckout = By.cssSelector("button[class*='add-to-cart']");
	
	public String obter_lblNomeProdutoCarrinho(){
	    return driver.findElement(lblNomeProdutoCarrinho).getText();
	}
	public String obter_lblPrecoProdutoCarrinho(){
	    return driver.findElement(lblPrecoProdutoCarrinho).getText();
	}
	public String obter_lblSizeCarrinho(){
	    return driver.findElement(lblSizeCarrinho).getText();
	}
	public String obter_lblColorCarrinho(){
	    return driver.findElement(lblColorCarrinho).getText();
	}
	public String obter_inputQtdCarrinho(){
	    return driver.findElement(inputQtdCarrinho).getAttribute("value");
	}
	public String obter_lblSubtotalCarrinho(){
	    return driver.findElement(lblSubtotalCarrinho).getText();
	}
	
	// Detalhes Total Carrinho
	public String obter_lblItensDetalheCarrinho(){
	    return driver.findElement(lblItensDetalheCarrinho).getText();
	}
	public String obter_lblShippingDetalheCarrinho(){
	    return driver.findElement(lblShippingDetalheCarrinho).getText();
	}
	public String obter_lblTotalSemTaxaDetalheCarrinho(){
	    return driver.findElement(lblTotalSemTaxaDetalheCarrinho).getText();
	}
	public String obter_lblTotalComTaxaDetalheCarrinho(){
	    return driver.findElement(lblTotalComTaxaDetalheCarrinho).getText();
	}
	public String obter_lblImpostosDetalheCarrinho(){
	    return driver.findElement(lblImpostosDetalheCarrinho).getText();
	}
	
	public CheckoutPage clicar_btnProceedToCheckout(){
		driver.findElement(btnProceedToCheckout).click();
		return new CheckoutPage(driver);
	}
	
	
	
	
}
