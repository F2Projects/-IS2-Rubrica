package is2.rubrica.test;

import java.awt.Component;

import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;
import is2.rubrica.Rubrica;

public class PanelInserimentoTest extends UISpecTestCase {
	
	protected void setUp() {
		this.setAdapter(new MainClassAdapter(Rubrica.class, new String[0]));
	
	}
	
	public void testGoToPannelloCerca() {
		this.pannelloIndexVisible(true);
		for (Component c : this.getMainWindow().getAwtComponent().getComponents())
			System.out.println(c.getName());
		this.getMainWindow().getButton("Cerca Contatti").click();
		this.pannelloIndexVisible(false);
		this.pannelloCercaVisible(true);
		this.getMainWindow().getButton("Indietro").click();
		this.pannelloIndexVisible(true);
		this.pannelloCercaVisible(false);
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
   
//	void pannelloRisultati(boolean att) {
//		this.risultati.setVisible(att);
//		this.back.setVisible(att);
//	}
//	
//	void pannelloInput(boolean att) {
//		this.nome.setVisible(att);
//		this.inNome.setVisible(att);
//		this.Cognome.setVisible(att);
//		this.inCognome.setVisible(att);
//		this.Numero.setVisible(att);
//		this.inNumero.setVisible(att);
//		this.Indirizzo.setVisible(att);
//		this.inIndirizzo.setVisible(att);
//		
//		this.ok.setVisible(att);
//		this.reset.setVisible(att);
//		this.savExit.setVisible(att);
//	}
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
