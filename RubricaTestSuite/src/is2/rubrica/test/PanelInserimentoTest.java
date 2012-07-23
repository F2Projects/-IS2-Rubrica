package is2.rubrica.test;

import is2.rubrica.Rubrica;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public class PanelInserimentoTest extends UISpecTestCase {
	
	protected void setUp() {
		this.setAdapter(new MainClassAdapter(Rubrica.class, new String[0]));
	
	}
	
	public void testGoToPannelloCerca() {
		pannelloIndexVisible(true);
		
		getMainWindow().getButton("Cerca Contatti").click();
		
		pannelloIndexVisible(false);
		pannelloCercaVisible(true);
		
		getMainWindow().getButton("Indietro").click();
		
		pannelloIndexVisible(true);
		pannelloCercaVisible(false);
	
	}
	
	private void pannelloIndexVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("index").isVisible());
		
		assertEquals(att, window.getButton("Crea rubrica").isVisible());
		assertEquals(att, window.getButton("Visualizza contatti").isVisible());
		assertEquals(att, window.getButton("Cerca Contatti").isVisible());
		assertEquals(att, window.getButton("esci").isVisible());
		
		assertEquals(att, window.getTextBox("                              ").isVisible());

	}
	
	private void pannelloCercaVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());
		assertEquals(att, window.getTextBox("infoCerca").isVisible());
		assertEquals(att, window.getTextBox("inCerca").isVisible());
		
		assertEquals(att, window.getButton("Cerca").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());
	
	}
   
	private void pannelloRisultatiVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("risultati").isVisible());
		
		assertEquals(att, window.getButton("Indietro").isVisible());
	
	}

	private void pannelloInputVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("                Nome").isVisible());
		assertEquals(att, window.getTextBox("inNome").isVisible());
		assertEquals(att, window.getTextBox("                Cognome").isVisible());
		assertEquals(att, window.getTextBox("inCognome").isVisible());
		assertEquals(att, window.getTextBox("                Numero Casa").isVisible());
		assertEquals(att, window.getTextBox("inNumero").isVisible());
		assertEquals(att, window.getTextBox("                Indirizzo").isVisible());
		assertEquals(att, window.getTextBox("inIndirizzo").isVisible());
		
		assertEquals(att, window.getButton("Add").isVisible());
		assertEquals(att, window.getButton("Reset").isVisible());
		assertEquals(att, window.getButton("Save & Back").isVisible());

	}
	
	private void pannelloOutputVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("Nome:").isVisible());
		assertEquals(att, window.getTextBox("Cognome:").isVisible());
		assertEquals(att, window.getTextBox("Numero:").isVisible());
		assertEquals(att, window.getTextBox("Indirizzo:").isVisible());

		assertEquals(att, window.getButton(">>").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());
		assertEquals(att, window.getButton("<<").isVisible());
		
	}
	
	private void pannelloRubInsVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("testoIns").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());

		assertEquals(att, window.getButton("Invio").isVisible());
		assertFalse(window.getButton("Invio ").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());

	}
	
	private void pannelloRubStp(boolean att) {
		Window window = getMainWindow();

		assertFalse(window.getTextBox("testoIns").isVisible());
		assertEquals(att, window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());
		
		assertEquals(att, window.getButton("Invio ").isVisible());
		assertFalse(window.getButton("Invio").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());

	}
	


}
