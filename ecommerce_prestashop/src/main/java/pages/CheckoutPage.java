package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By  lblTotalTaxaInclusaCheckout = By.cssSelector("section[id='js-checkout-summary'] div[class='card-block cart-summary-totals'] div:nth-child(2) span[class='value']");
	private By  lblNomeClienteCheckout = By.cssSelector("article[id='id-address-delivery-address-80'] div[class='address']");
	private By  btnContinueCheckout = By.cssSelector("button[name='confirm-addresses']");
	
	public String obter_totalTaxIncTotal() {
		return driver.findElement(lblTotalTaxaInclusaCheckout).getText();
	}
	
	public String obter_lblNomeClienteCheckout() {
		return driver.findElement(lblNomeClienteCheckout).getText();
	}
	public void clicar_btnContinueCheckout() {
		driver.findElement(btnContinueCheckout).click();
	}

}
