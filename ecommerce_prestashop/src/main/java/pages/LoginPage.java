package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By  txtEmail = By.cssSelector("form[id='login-form'] input[name='email']");
	private By  txtPassword = By.cssSelector("form[id='login-form'] input[name='password']");
	private By  btnSignIn = By.cssSelector("form[id='login-form'] button[id='submit-login']");
	
	public void preencherEmail(String email) {
		driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void preencherPassword(String password) {
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	public void clicarBotaoSignIn() {
		driver.findElement(btnSignIn).click();
	}
}
