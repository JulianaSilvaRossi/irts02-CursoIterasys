package base;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;

import pages.HomePage;

public class BaseTests {
	
	private static WebDriver driver;
	protected HomePage homePage;
	
	@BeforeAll
	public static void start() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chrome\\83");
	}
	
	@BeforeEach
	public void carregarPaginaInicial() {
		driver.get("https://marcelodebittencourt.com/demoprestashop/");
		homePage = new HomePage(driver);
	}
	
	@AfterAll
	public static void finish() {
		driver.quit();
	}

}
