package homepage;

import static org.junit.Assert.assertEquals;
//Annotation Test
import org.junit.Test;

import base.BaseTests;

public class HomePageTests extends BaseTests {
	
	@Test
	public void testContarProdutos_OitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertEquals(8, homePage.contarProdutos());
	}

	

}
