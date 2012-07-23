package is2.rubrica.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import is2.rubrica.Rubrica;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public class PanelInserimentoTest extends UISpecTestCase {
	
	private final static String thisTestRubName = "GuiTest-" + new Random().nextInt();
	
	protected void setUp() {
		this.setAdapter(new MainClassAdapter(Rubrica.class, new String[0]));
	
	}
	
	/**
	 * Testing path
	 * PANNELLO INIZIALE -> bottone "Cerca Contatti" -> PANNELLO RICERCA -> bottone "Indietro" -> PANNELLO INIZIALE
	 */
	public void testGoToPannelloCerca() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in cerca contatti
		getMainWindow().getButton("Cerca Contatti").click();
		
		// ci sono davvero?
		pannelloIndexVisible(false);
		pannelloCercaVisible(true);
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
		// me ne sono andato?
		pannelloIndexVisible(true);
		pannelloCercaVisible(false);
	
	}
	
	/**
	 * Testing path
	 * PANNELLO INIZIALE -> bottone "Visualizza contatti" -> PANNELLO INSERIMENTO NOME RUBRICA -> bottone "Indietro" -> PANNELLO INIZIALE
	 */
	public void testGoToVisualizzaContatti() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in visualizza contatti
		getMainWindow().getButton("Visualizza contatti").click();
		
		// ci sono davvero?
		pannelloIndexVisible(false);
		pannelloRubStpVisible(true);
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
		// me ne sono andato?
		pannelloIndexVisible(true);
		pannelloRubStpVisible(false);
	
	}
	
	/**
	 * Testing path
	 * PANNELLO INIZIALE -> bottone "Crea Rubrica" -> PANNELLO CREAZIONE RUBRICA -> bottone "Indietro" -> PANNELLO INIZIALE
	 */
	public void testGoToCreazioneRubrica(){
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in crea rubrica
		getMainWindow().getButton("Crea rubrica").click();
		
		// ci sono davvero?
		pannelloRubInsVisible(true);
		pannelloIndexVisible(false);
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
		// me ne sono andato?
		pannelloRubInsVisible(false);
		pannelloIndexVisible(true);
	}
	
	/**
	 * Testing if the user submit a void name into Creazione Rubrica
	 */
	public void testVoidNameCreazioneRubrica(){
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in creazione rubrica
		getMainWindow().getButton("Crea rubrica").click();
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText("");
		getMainWindow().getButton("Invio").click();
		
		// non mi sono mosso da creazione rubrica
		pannelloRubInsVisible(true);
		pannelloInputVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("Nome File non corretto.Riprova                        ", getMainWindow().getTextBox("testoIns").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
	}

	/**
	 * Testing if the user submit a valid name into Creazione Rubrica
	 */
	public void testValidNameCreazioneRubrica(){
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in creazione rubrica
		getMainWindow().getButton("Crea rubrica").click();
		
		// setto inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName + "_no_items");
		getMainWindow().getButton("Invio").click();
		
		// controllo che abbia creato il file
		assertTrue(new File(PanelInserimentoTest.thisTestRubName + "_no_items").exists());
		
		// vado in aggiungi contatto
		pannelloRubInsVisible(false);
		pannelloInputVisible(true);
		
		// torno indietro
		getMainWindow().getButton("Save & Back").click();
		
		// me ne sono andato?
		pannelloInputVisible(false);
		pannelloIndexVisible(true);

	}

	/**
	 * Testing if the user inserts contacts (check the add and the reset operations)
	 * @throws FileNotFoundException 
	 */
	public void testAddContacts() throws Exception{
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in creazione rubrica
		getMainWindow().getButton("Crea rubrica").click();
		
		// setto inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName);
		getMainWindow().getButton("Invio").click();
		
		String[] contatto = new String[] {"nome", "cognome", "numero", "indirizzo"};
		// inserisco un contatto
		getMainWindow().getTextBox("inNome").setText(contatto[0]);
		getMainWindow().getTextBox("inCognome").setText(contatto[1]);
		getMainWindow().getTextBox("inNumero").setText(contatto[2]);
		getMainWindow().getTextBox("inIndirizzo").setText(contatto[3]);
		
		getMainWindow().getButton("Add").click();
		
		// inserisco un altro contatto
		getMainWindow().getTextBox("inNome").setText(contatto[0]);
		getMainWindow().getTextBox("inCognome").setText(contatto[1]);
		getMainWindow().getTextBox("inNumero").setText(contatto[2]);
		getMainWindow().getTextBox("inIndirizzo").setText(contatto[3]);
		
		getMainWindow().getButton("Add").click();
		
		// controllo che siano stati inseriti i contatti correttamente
		BufferedReader fileReader = new BufferedReader(new FileReader(new File(PanelInserimentoTest.thisTestRubName)));
		String linea;
		int index=0;
		while((linea = fileReader.readLine()) != null) 
			assertEquals(contatto[index++ % 4], linea);
		
		// inserisco di nuovo il contatto per testare il reset dei campi
		getMainWindow().getTextBox("inNome").setText(contatto[0]);
		getMainWindow().getTextBox("inCognome").setText(contatto[1]);
		getMainWindow().getTextBox("inNumero").setText(contatto[2]);
		getMainWindow().getTextBox("inIndirizzo").setText(contatto[3]);
		
		getMainWindow().getButton("Reset").click();
		
		// controllo che i campi siano stati resettati
		assertEquals("", getMainWindow().getTextBox("inNome").getText());
		assertEquals("", getMainWindow().getTextBox("inCognome").getText());
		assertEquals("", getMainWindow().getTextBox("inNumero").getText());
		assertEquals("", getMainWindow().getTextBox("inIndirizzo").getText());

		// torno indietro
		getMainWindow().getButton("Save & Back").click();
	}
	
	/**
	 * Testing if the user submit a duplicate name into Creazione Rubrica
	 */
	public void testDuplicateNameCreazioneRubrica(){
		// parto da index
		pannelloIndexVisible(true);
				
		// vado in creazione rubrica
		getMainWindow().getButton("Crea rubrica").click();
		
		// controllo che gia` esista il file
		assertTrue(new File(PanelInserimentoTest.thisTestRubName).exists());
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName);
		getMainWindow().getButton("Invio").click();
		
		// non mi sono mosso da creazione rubrica
		pannelloRubInsVisible(true);
		pannelloInputVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("File giˆ esistente. Riprova                         ", getMainWindow().getTextBox("testoIns").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
	}
	
	/**
	 * Testing if user insert a void Rub name in Visualizza contatti
	 */
	public void testVoidNameVisualizzaContatti() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in visualizza contatti
		getMainWindow().getButton("Visualizza contatti").click();
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText("");
		getMainWindow().getButton("Invio ").click();
		
		// non mi sono mosso da creazione rubrica
		pannelloRubStpVisible(true);
		pannelloOutputVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("Nome File non corretto.Riprova!                       ", getMainWindow().getTextBox("testoIns").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
	
	}
	
	/**
	 * Testing if user insert an invalid Rub name in Visualizza contatti
	 */
	public void testInvalidNameVisualizzaContatti() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in visualizza contatti
		getMainWindow().getButton("Visualizza contatti").click();
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText("INVALID_INVALID_INVALID");
		getMainWindow().getButton("Invio ").click();
		
		// non mi sono mosso da creazione rubrica
		pannelloRubStpVisible(true);
		pannelloOutputVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("File non esistente. Riprova!                        ", getMainWindow().getTextBox("testoIns").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
	
	}
	
	/**
	 * Testing if the user lists the contacts
	 */
	public void testListContacts() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in visualizza contatti
		getMainWindow().getButton("Visualizza contatti").click();
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName);
		getMainWindow().getButton("Invio ").click();
		
		// vado in output
		pannelloRubStpVisible(false);
		pannelloOutputVisible(true);
		
		// mi faccio un giretto tra i contatti :)
		assertFalse(getMainWindow().getButton("<<").isEnabled());
		assertTrue(getMainWindow().getButton(">>").isEnabled());
		
		getMainWindow().getButton(">>").click();
		
		assertTrue(getMainWindow().getButton("<<").isEnabled());
		assertFalse(getMainWindow().getButton(">>").isEnabled());
		
		getMainWindow().getButton("<<").click();
		
		assertFalse(getMainWindow().getButton("<<").isEnabled());
		assertTrue(getMainWindow().getButton(">>").isEnabled());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
	
	}
	
	/**
	 * Testing if user insert a void Rub Name into Cerca contatti
	 */
	public void testVoidNameCerca() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in cerca contatti
		getMainWindow().getButton("Cerca Contatti").click();
		
		// setto un campo nullo a inNameRub
		getMainWindow().getTextBox("inNameRub").setText("");
		getMainWindow().getButton("Cerca").click();
		
		// non mi sono mosso da cerca contatti
		pannelloCercaVisible(true);
		pannelloRisultatiVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("Nome File non corretto.Riprova!                       ", getMainWindow().getTextBox("testoStp").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
	}
	
	/**
	 * Testing if user insert an invalid Rub Name into Cerca contatti
	 */
	public void testInvalidNameCerca() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in cerca contatti
		getMainWindow().getButton("Cerca Contatti").click();
		
		// setto un campo invalido a inNameRub
		getMainWindow().getTextBox("inNameRub").setText("INVALID_INVALID_INVALID");
		getMainWindow().getButton("Cerca").click();
		
		// non mi sono mosso da cerca contatti
		pannelloCercaVisible(true);
		pannelloRisultatiVisible(false);
		
		// controllo che l'errore venga stampato
		assertEquals("File non esistente. Riprova!                        ", getMainWindow().getTextBox("testoStp").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
	}
	
	/**
	 * Testing if user insert an invalid contact name into Cerca contatti
	 */
	public void testInvalidContactNameCerca() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in cerca contatti
		getMainWindow().getButton("Cerca Contatti").click();
		
		// setto un campo invalido a inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName);
		getMainWindow().getTextBox("inCerca").setText("INVALID_INVALID_INVALID");
		getMainWindow().getButton("Cerca").click();
		
		// vado in risultati
		pannelloCercaVisible(false);
		pannelloRisultatiVisible(true);
		
		// controllo che l'errore venga stampato
		assertEquals("Nessun contatto trovato!!", getMainWindow().getTextBox("risultati").getText());
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
	}
	
	/**
	 * Testing if user insert an valid contact name into Cerca contatti
	 */
	public void testValidContactNameCerca() {
		// parto da index
		pannelloIndexVisible(true);
		
		// vado in cerca contatti
		getMainWindow().getButton("Cerca Contatti").click();
		
		// setto un campo invalido a inNameRub
		getMainWindow().getTextBox("inNameRub").setText(PanelInserimentoTest.thisTestRubName);
		getMainWindow().getTextBox("inCerca").setText("nome");
		getMainWindow().getButton("Cerca").click();
		
		// vado in risultati
		pannelloCercaVisible(false);
		pannelloRisultatiVisible(true);
		
		// controllo che l'errore non venga stampato
		assertFalse(getMainWindow().getTextBox("risultati").getText().equals("Nessun contatto trovato!!"));
		
		// vado via
		getMainWindow().getButton("Indietro").click();
		
	}
	
	/**
	 * Testing exit button
	 */
	public void testExit() {
		getMainWindow().getButton("esci").click();
		assertEquals(null, getMainWindow());
	}
	
	/**
	 * Method used to check if the Index panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloIndexVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("index").isVisible());
		
		assertEquals(att, window.getButton("Crea rubrica").isVisible());
		assertEquals(att, window.getButton("Visualizza contatti").isVisible());
		assertEquals(att, window.getButton("Cerca Contatti").isVisible());
		assertEquals(att, window.getButton("esci").isVisible());
		
		assertEquals(att, window.getTextBox("                              ").isVisible());

	}
	
	/**
	 * Method used to check if the Cerca panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloCercaVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());
		assertEquals(att, window.getTextBox("infoCerca").isVisible());
		assertEquals(att, window.getTextBox("inCerca").isVisible());
		
		assertEquals(att, window.getButton("Cerca").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());
	
	}
   
	/**
	 * Method used to check if the Risultati panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloRisultatiVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("risultati").isVisible());
		
		assertEquals(att, window.getButton("Indietro").isVisible());
	
	}

	/**
	 * Method used to check if the Input panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
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
	
	/**
	 * Method used to check if the Output panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloOutputVisible(boolean att) {
		Window window = getMainWindow();
		
		assertEquals(att, window.getTextBox("Nome:").isVisible());
		assertEquals(att, window.getTextBox("Cognome:").isVisible());
		assertEquals(att, window.getTextBox("Numero:").isVisible());
		assertEquals(att, window.getTextBox("Indirizzo:").isVisible());

		assertEquals(att, window.getButton(">>").isVisible());
		//assertEquals(att, window.getButton("Indietro").isVisible());
		assertEquals(att, window.getButton("<<").isVisible());
		
	}
	
	/**
	 * Method used to check if the RubIns panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloRubInsVisible(boolean att) {
		Window window = getMainWindow();
		
		assertFalse(window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("testoIns").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());

		assertEquals(att, window.getButton("Invio").isVisible());
		assertFalse(window.getButton("Invio ").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());

	}
	
	/**
	 * Method used to check if the RubStp panel is shown/hidden
	 * 
	 * @param att : true is for visible, false is for not visible
	 */
	private void pannelloRubStpVisible(boolean att) {
		Window window = getMainWindow();

		assertFalse(window.getTextBox("testoIns").isVisible());
		assertEquals(att, window.getTextBox("testoStp").isVisible());
		assertEquals(att, window.getTextBox("inNameRub").isVisible());
		
		assertEquals(att, window.getButton("Invio ").isVisible());
		assertFalse(window.getButton("Invio").isVisible());
		assertEquals(att, window.getButton("Indietro").isVisible());

	}
	


}
