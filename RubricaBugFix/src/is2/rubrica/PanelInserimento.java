/**
 * PanelInserimento.java
 * 
 * @author Alenadri Enrico - III G
 * @date 6/02/2009
 * Fixed by:
 * "helloIAmPau - Pasquale Boemio <pa.boemio[at]studenti.unina.it>"
 * "b3by" - Antonio Bevilacqua <anto.bevilacqua[at]studenti.unina.it>"
 * ----------
 * 
 * We have fixed:
 * 1. indentation
 * 2. added some inline documentation
 * 3. added the serialVersionUID (not necessary)
 * 4. converted TextField objects in JTextField (frow AWT to Swing)
 * 
 * NB: Some class or variables name are edited in order to respect the standard "camel notation"
 * to make this source code more readable. 
 */
package is2.rubrica;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInserimento extends JPanel implements ActionListener{

    // L'oggetto JFrame e` un oggetto "Serializable". Poiche` abbiamo seri problemi,
	// preferiamo eliminare anche tutti i "warning" presenti nel codice, e quindi 
	// assegnamo a questo un id autogenerato!
	private static final long serialVersionUID = 685601408825075349L;
	
	// ----------------------------------
	// Variabile non usata
	// private boolean exist=true;
	// ----------------------------------
	private int num=0,
			linee=0,
			lineeStm=0,
			i,
			cont;
	// ----------------------------------
	// Variabili non usate
	// 		con,
	//      x=0;
	// ----------------------------------
	
	private String linea,
			stringa,
			risCerca="";
	// ----------------------------------
	// Variabile non usata
	// 	nameRub;
	// ----------------------------------
	
	private JTextField inNome,
			inCognome,
			inNumero,
			inIndirizzo,
			inNameRub,
			inCerca;
	
	private FileOutputStream fileS;
	private FileReader fileL;
	private File exFile;
	private PrintStream scrittura;
	private BufferedReader lettura;
	private TextArea risultati;
	private int[] cerca = new int[100];
	
	private JLabel testoIns,
			testoStp,
			nome,
			// ----------------------------------
			// Addirittura errori di battitura...
			// Congome,
			Cognome,
			// ----------------------------------
			Numero,
			Indirizzo,
			index,
			spazio,
			stmNome,
			stmCognome,
			stmNumero,
			stmIndirizzo,
			infoCerca;
	
	private JButton ok,
			reset,
			savExit,
			invio,
			invio_,
			indIns,
			indStp,
			indCer,
			avanti,
			back,
			indietro,
			exit,
			Cerca;
	
	public PanelInserimento(){
		
		this.exit = new JButton("esci");
		this.spazio= new JLabel("                              ");
		
		// Componenti Index
		this.index = new JLabel("       Cosa vuoi fare?                                    ");
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ad alcune JLabel
		this.index.setName("index");
		// ----------------------------------
		this.indIns = new JButton("Crea rubrica");
		this.indStp = new JButton("Visualizza contatti");
		this.indCer = new JButton("Cerca Contatti");
		
		// Componenti inserimento nome Rubrica-inserimento scherda/stampa scheda
		this.testoIns = new JLabel("Inserisci il nome con cui salvare la rubrica        ");
		this.testoStp = new JLabel("Inserisci il nome della rubrica        ");
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ad alcune JLabel
		this.testoStp.setName("testoStp");
		this.testoIns.setName("testoIns");
		// ----------------------------------
		this.inNameRub = new JTextField(10);
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ai textfield
		this.inNameRub.setName("inNameRub");
		// ----------------------------------
		this.invio = new JButton("Invio");
		this.invio_ = new JButton("Invio ");
		
		// Componenti Cerca
		this.Cerca = new JButton("Cerca");
		this.infoCerca = new JLabel("Puoi cercare per Nome-Cognome-Numero-indirizzo");
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ai textfield
		this.infoCerca.setName("infoCerca");
		// ----------------------------------
		this.inCerca = new JTextField(10);
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ai textfield
		this.inCerca.setName("inCerca");
		// ----------------------------------
		this.risultati = new TextArea(10,35);
		// ----------------------------------
		// Per poter fare testing, sono stati aggiunti i nomi ai textfield
		this.risultati.setName("risultati");
		// ----------------------------------
		this.risultati.setEditable(false);
		this.risultati.setCaretPosition(0);
		
		// Componenti inserimento scheda
		this.nome = new JLabel("                Nome");
		this.Cognome = new JLabel("                Cognome");
		this.Numero = new JLabel("                Numero Casa");
		this.Indirizzo = new JLabel("                Indirizzo");
		this.ok = new JButton("Add");
		this.reset = new JButton("Reset");
		this.savExit = new JButton("Save & Back");
		this.inNome = new JTextField(17);
		this.inCognome = new JTextField(17);
		this.inNumero = new JTextField(17);
		this.inIndirizzo = new JTextField(17);
		// Per poter fare testing, sono stati aggiunti i nomi ai textfield
		this.inNome.setName("inNome");
		this.inCognome.setName("inCognome");
		this.inNumero.setName("inNumero");
		this.inIndirizzo.setName("inIndirizzo");
		// ----------------------------------
		
		// Componenti stampa Scheda
		this.stmNome = new JLabel("Nome:");
		this.stmCognome = new JLabel("Cognome:");
		this.stmNumero = new JLabel("Numero:");
		this.stmIndirizzo = new JLabel("Indirizzo");
		this.avanti = new JButton(">>");
		this.back = new JButton("Indietro");
		this.indietro = new JButton("<<");
		
		// ----------------------------------
		// Variabile non usata
		// 	this.nameRub = inNameRub.getText();
		// ----------------------------------
		
		// Aggiungo componenti index
		this.add(this.index);
		this.add(this.indIns);
		this.add(this.indStp);
		this.add(this.indCer);
		this.add(this.exit);
		
		this.indIns.addActionListener(this);
		this.indStp.addActionListener(this);
		this.indCer.addActionListener(this);
		this.exit.addActionListener(this);
		
		// Aggiungo componenti Inserimento rubrica
		this.add(this.testoStp);
		this.add(this.testoIns);
		this.add(this.inNameRub);
		this.add(this.invio);
		this.add(this.invio_);
		
		this.invio.addActionListener(this);
		this.invio_.addActionListener(this);
		
		// Aggiungo componenti Cerca
		this.add(this.risultati);
		this.add(this.infoCerca);
		this.add(this.inCerca);
		this.add(this.Cerca);
		
		this.Cerca.addActionListener(this);
		
		// Aggiungo componenti Input contatti
		this.add(this.nome);
		this.add(this.inNome);
		this.add(this.Cognome);
		this.add(this.inCognome);
		this.add(this.Numero);
		this.add(this.inNumero);
		this.add(this.Indirizzo);
		this.add(this.inIndirizzo);
		this.add(this.ok);
		this.add(this.reset);
		this.add(this.savExit);
		
		this.ok.addActionListener(this);
		this.reset.addActionListener(this);
		this.savExit.addActionListener(this);
		
		// Aggiungo componenti Visualizzazione contatti
		this.add(this.stmNome);
		this.add(this.stmCognome);
		this.add(this.stmNumero);
		this.add(this.stmIndirizzo);
		this.add(this.indietro);
		this.add(this.back);
		this.add(this.avanti);
		this.indietro.setEnabled(false);
		
		this.avanti.addActionListener(this);
		this.back.addActionListener(this);
		this.indietro.addActionListener(this);
		
		// Setto i componenti per visualizzare la scherata di index
		this.pannelloInput(false);
		this.pannelloOutput(false);
		this.pannelloRubIns(false);
		this.pannelloCerca(false);
		this.pannelloRisultati(false);
		
	}
	
	public void actionPerformed(ActionEvent evt){
		
		String nome = inNome.getText();
		String cognome = inCognome.getText();
		String numero = inNumero.getText();
		String indirizzo = inIndirizzo.getText();
		String nameRub = inNameRub.getText();
		boolean test;
		
		String command = evt.getActionCommand();
		command = command.toLowerCase();
		
		if(command.equals("<<")){
			if(this.num>0){
				this.num--;
				this.avanti.setEnabled(true);
			}
			if(this.num<0)
				this.indietro.setEnabled(false);
			
			this.letturaFile(nameRub);
			this.stampa();
		}
		if(command.equals(">>")){
			if((this.num+1)*4<this.lineeStm){
				this.num++;
				this.indietro.setEnabled(true);
			}
			if((this.num+1)*4==this.lineeStm)
				this.avanti.setEnabled(false);
			
			this.letturaFile(nameRub);
			this.stampa();
		}
		if(command.equals("indietro")){
			this.num=0;
			this.risCerca="";
			this.testoStp.setText("Inserisci il nome della rubrica        ");
			this.testoIns.setText("Inserisci il nome con cui salvare la rubrica        ");
			this.avanti.setEnabled(true);
			this.indietro.setEnabled(false);
			this.pannelloIndex(true);
			this.pannelloInput(false);
			this.pannelloOutput(false);
			this.pannelloRubIns(false);
			this.pannelloRubStp(false);
			this.pannelloCerca(false);
			this.pannelloRisultati(false);
		}
		if(command.equals("add")){
			try{
				this.scrittura.println(nome);
				this.scrittura.println(cognome);
				this.scrittura.println(numero);
				this.scrittura.println(indirizzo);
			} 
			catch(NullPointerException ex){
				System.out.println("File insesistente...                        ");
			}
			
			this.reset();
		}
		if(command.equals("invio")){
			test=true;
			
			if(nameRub.equals(""))
				test=true;
			
			this.exFile = new File(nameRub);
			if( ((this.exFile).exists()) || (test==false)){
				this.inNameRub.setText("");
				if(test==false)
					this.testoIns.setText("Nome File non corretto.Riprova                        ");
				else
					// ----------------------------------
					// Carattere UTF8 non codificato 
					//this.testoIns.setText("File già esistente. Riprova                         ");
					this.testoIns.setText("File giˆ esistente. Riprova                         ");
					// ----------------------------------
				}
			else{
				pannelloRubIns(false);
				pannelloInput(true);
				try{
					this.fileS = new FileOutputStream(nameRub);
					this.scrittura = new PrintStream(this.fileS);
				}
				catch(FileNotFoundException ex){
					System.out.println("Errore in lettura File...");
				}
			}
		}
		if(command.equals("invio ")){
			test=true;
			
			if(nameRub.equals(""))
				test=false;
			
			this.exFile = new File(nameRub);
			if( ((this.exFile).exists()) && (test==true)){
				this.letturaFile(nameRub);
				this.stampa();
				this.pannelloRubStp(false);
				this.pannelloOutput(true);
			}
			else{
				this.inNameRub.setText("");
				if(test==false)
					this.testoStp.setText("Nome File non corretto.Riprova!                       ");
				else
					testoStp.setText("File non esistente. Riprova!                        ");
			}
		}
		if(command.equals("reset")){
			this.reset();
		}
		if(command.equals("save & back")){
			this.pannelloInput(false);
			this.pannelloRubIns(false);
			this.pannelloRubStp(false);
			this.pannelloIndex(true);
			this.pannelloOutput(false);
		}
		if(command.equals("cerca")){
			test=true;
			
			if(nameRub.equals(""))
				test=false;
			
			this.exFile = new File(nameRub);
			if( ((this.exFile).exists()) && (test==true)){
				this.stringa = this.inCerca.getText();
				this.cont=0;
				// ----------------------------------
				// Variabile non usata
				// con=0;
				// ----------------------------------
				this.linee=0;
				this.cerca = new int[100];
				this.cerca(this.stringa,nameRub);
				this.i=0;
				if(this.cerca[0]==0) {
					do {
						this.stampaRisultati(this.cerca[this.i]-1,nameRub);
						this.i++;
					} while(this.cerca[this.i]!=0);
				}else{
					this.risCerca="Nessun contatto trovato!!";
				}
				this.risultati.setText(this.risCerca);
				this.pannelloCerca(false);
				this.pannelloRisultati(true);
			}else{
				this.inNameRub.setText("");
				if(test==false)
					this.testoStp.setText("Nome File non corretto.Riprova!                       ");
				else
					this.testoStp.setText("File non esistente. Riprova!                        ");
			}
		}
		if(command.equals("Esci")){
			System.exit(0);
		}
		if(command.equals("crea rubrica")){
			this.pannelloRubIns(true);
			this.pannelloIndex(false);
		}
		if(command.equals("visualizza contatti")){
			this.pannelloRubStp(true);
			this.pannelloIndex(false);
		}
		if(command.equals("cerca contatti")) {
			this.pannelloIndex(false);
			this.pannelloCerca(true);
		}
	}
   
	void letturaFile(String nomeFile) {
		  int cont=0;
		  this.lineeStm=0;
		  
		  try {
			  this.fileL = new FileReader(nomeFile);
			  this.lettura = new BufferedReader(this.fileL);
			  
			  while( (this.linea = this.lettura.readLine()) != null )
				  this.lineeStm++;
			  
			  this.fileL = new FileReader(nomeFile);
			  this.lettura = new BufferedReader(fileL);
			  
			  while( cont < this.num*4 ) {
				  cont++;
				  this.linea = this.lettura.readLine();
			  }
		  }
		  catch(FileNotFoundException ex) {
			  System.out.println("Errore in lettura file, Nessun file trovato!");
		  }
		  catch(IOException ex) {
			  System.out.println("Errore in lettura file..");
		  }
	}
	
	void cerca(String stringRicerca,String nomeFile) {
		this.i=0;
		
		try{
			this.fileL = new FileReader(nomeFile);
			this.lettura= new BufferedReader(this.fileL);
			
			while( (this.linea = this.lettura.readLine()) != null ) {
				this.linee++;
			}
			
			this.fileL = new FileReader(nomeFile);
			this.lettura = new BufferedReader(this.fileL);
			this.num=1;
			do {
				while( this.cont < this.num*4 ) {
					this.cont++;
					this.linea = this.lettura.readLine();
					if( this.linea != null) {
						if(this.linea.equals(stringRicerca)) {
							this.cerca[this.i] = this.num;
							this.i++;
						}
					}
				}
				this.num++;
			}
			while(this.cont < this.linee);
		}
		catch(FileNotFoundException ex) {
			System.out.println("Errore in lettura file..");
		}
		catch(IOException ex) {
			System.out.println("Errore in lettura file..");
		}
	}
	
	void stampaRisultati(int nume,String nomeFile) {
		this.cont=0;
		// ----------------------------------
		// Variabile non usata
		// con=0;
		// ----------------------------------
		this.linee=0;
		try{
			this.fileL = new FileReader(nomeFile);
			this.lettura = new BufferedReader(this.fileL);
			
			while( (this.linea = this.lettura.readLine()) != null ) {
				this.linee++;
			}
			
			this.fileL = new FileReader(nomeFile);
			this.lettura = new BufferedReader(this.fileL);
			
			while(this.cont<nume*4) {
				this.linea = this.lettura.readLine();
				this.cont++;
			}
			
			this.linea = this.lettura.readLine();
			this.risCerca += "Nome: " + this.linea;
			
			this.linea = this.lettura.readLine();
			this.risCerca += "\nCognome: " + this.linea;
			
			this.linea = this.lettura.readLine();
			this.risCerca += "\nNumero Casa: " + this.linea;
			
			this.linea = this.lettura.readLine();
			this.risCerca += "\nIndirizzo: " + this.linea + "\n\n";
		}
		catch(FileNotFoundException ex) {
			System.out.println("Errore in lettura file..");
		}
		catch(IOException ex) { 
			System.out.println("Errore in lettura file..");
		}
	}

	void stampa() {
		
		try {
			this.linea = this.lettura.readLine();
			this.stmNome.setText("                        Nome :  " + this.linea + "                                            ");
			this.linea = this.lettura.readLine();
			this.stmCognome.setText("                        Cognome :  " + this.linea + "                                  ");
			this.linea = this.lettura.readLine();
			this.stmNumero.setText("                         Numero :  " + this.linea + "                                  ");
			this.linea = this.lettura.readLine();
			this.stmIndirizzo.setText("                        Indirizzo :  " + this.linea + "                                  ");
		}
		catch(IOException ex) {
			System.out.println("Errore in stampa Dati File.....");
		} 
		catch(NullPointerException ex) {
			System.out.println("Nessun file ricevuto...");
		}
	}
	
	void reset() {
		this.inNome.setText("");
		this.inCognome.setText("");
		this.inNumero.setText("");
		this.inIndirizzo.setText("");
	}
	
	void pannelloCerca(boolean att) {
		this.testoStp.setVisible(att);
		this.inNameRub.setVisible(att);
		this.infoCerca.setVisible(att);
		this.inCerca.setVisible(att);
		Cerca.setVisible(att);
		back.setVisible(att);
	   }
   
	void pannelloRisultati(boolean att) {
		this.risultati.setVisible(att);
		this.back.setVisible(att);
	}
	
	void pannelloInput(boolean att) {
		this.nome.setVisible(att);
		this.inNome.setVisible(att);
		this.Cognome.setVisible(att);
		this.inCognome.setVisible(att);
		this.Numero.setVisible(att);
		this.inNumero.setVisible(att);
		this.Indirizzo.setVisible(att);
		this.inIndirizzo.setVisible(att);
		
		this.ok.setVisible(att);
		this.reset.setVisible(att);
		this.savExit.setVisible(att);
	}
	
	void pannelloOutput(boolean att) {
		this.stmNome.setVisible(att);
		this.stmCognome.setVisible(att);
		this.stmNumero.setVisible(att);
		this.stmIndirizzo.setVisible(att);
		this.avanti.setVisible(att);
		this.back.setVisible(att);
		this.indietro.setVisible(att);
	}
	
	void pannelloRubIns(boolean att) {
		this.testoStp.setVisible(false);
		this.testoIns.setVisible(att);
		this.inNameRub.setVisible(att);
		this.invio.setVisible(att);
		this.invio_.setVisible(false);
	}
	
	void pannelloRubStp(boolean att) {
		this.testoIns.setVisible(false);
		this.testoStp.setVisible(att);
		this.inNameRub.setVisible(att);
		this.invio.setVisible(false);
		this.invio_.setVisible(att);
		this.back.setVisible(att);
	}
	
	void pannelloIndex(boolean att) {
		this.index.setVisible(att);
		this.indIns.setVisible(att);
		this.indStp.setVisible(att);
		this.indCer.setVisible(att);
		this.exit.setVisible(att);
		this.spazio.setVisible(att);
	}
}