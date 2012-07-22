package is2.rubrica.test;

import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;
import is2.rubrica.Rubrica;

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
		assertEquals(att, window.getTextBox("       Cosa vuoi fare?                                    ").isVisible());
		assertEquals(att, window.getButton("Crea rubrica").isVisible());
		assertEquals(att, window.getButton("Visualizza contatti").isVisible());
		assertEquals(att, window.getButton("Cerca Contatti").isVisible());
		assertEquals(att, window.getButton("esci").isVisible());
		assertEquals(att, window.getTextBox("                              ").isVisible());

	}
	
	private void pannelloCercaVisible(boolean att) {
		Window window = getMainWindow();
		assertEquals(att, window.getTextBox("Inserisci il nome della rubrica        ").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());
		assertEquals(att, window.getTextBox("Puoi cercare per Nome-Cognome-Numero-indirizzo").isVisible());
		assertEquals(att, window.getTextBox("inCerca").isVisible());
		assertEquals(att, window.getButton("Cerca").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());
	}
   
	private void pannelloRisultatiVisible(boolean att) {
		Window window = getMainWindow();
		assertEquals(att, window.getTextBox("risultati").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());
	}

	private void pannelloInput(boolean att) {
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
//	
//	void pannelloOutput(boolean att) {
//		this.stmNome.setVisible(att);
//		this.stmCognome.setVisible(att);
//		this.stmNumero.setVisible(att);
//		this.stmIndirizzo.setVisible(att);
//		this.avanti.setVisible(att);
//		this.back.setVisible(att);
//		this.indietro.setVisible(att);
//	}
//	
//	void pannelloRubIns(boolean att) {
//		this.testoStp.setVisible(false);
//		this.testoIns.setVisible(att);
//		this.inNameRub.setVisible(att);
//		this.invio.setVisible(att);
//		this.invio_.setVisible(false);
//	}
//	
//	void pannelloRubStp(boolean att) {
//		this.testoIns.setVisible(false);
//		this.testoStp.setVisible(att);
//		this.inNameRub.setVisible(att);
//		this.invio.setVisible(false);
//		this.invio_.setVisible(att);
//		this.back.setVisible(att);
//	}
//	


}
