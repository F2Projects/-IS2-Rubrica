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
 * 
 * NB: Some class or variables name are edited in order to respect the standard "camel notation"
 * to make this source code more readable. 
 */
package is2.rubrica;

import java.awt.TextArea;
import java.awt.TextField;
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

class PanelInserimento extends JPanel implements ActionListener{

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
	
	private TextField inNome,
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
		this.indIns = new JButton("Crea rubrica");
		this.indStp = new JButton("Visualizza contatti");
		this.indCer = new JButton("Cerca Contatti");
		
		// Componenti inserimento nome Rubrica-inserimento scherda/stampa scheda
		this.testoIns = new JLabel("Inserisci il nome con cui salvare la rubrica        ");
		this.testoStp = new JLabel("Inserisci il nome della rubrica        ");
		this.inNameRub = new TextField(10);
		this.invio = new JButton("Invio");
		this.invio_ = new JButton("Invio ");
		
		// Componenti Cerca
		this.Cerca = new JButton("Cerca");
		this.infoCerca = new JLabel("Puoi cercare per Nome-Cognome-Numero-indirizzo");
		this.inCerca = new TextField(10);
		this.risultati = new TextArea(10,35);
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
		this.inNome = new TextField(17);
		this.inCognome = new TextField(17);
		this.inNumero = new TextField(17);
		this.inIndirizzo = new TextField(17);
		
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
	
   public void actionPerformed(ActionEvent evt)
		     {
			   String nome = inNome.getText();
			   String cognome = inCognome.getText();
			   String numero = inNumero.getText();
			   String indirizzo = inIndirizzo.getText();
			   String nameRub = inNameRub.getText();
              boolean test;

			   String command = evt.getActionCommand();
			   command = command.toLowerCase();
			 if(command.equals("<<"))
			 			    {
			 				  if(num>0)
			 				    {
			 					 num--;
			 					 avanti.setEnabled(true);
							    }

			 			      if(num<0)
			 			         indietro.setEnabled(false);

							   letturaFile(nameRub);
			 			       stampa();
				}
			 if(command.equals(">>"))
			    {
				  if((num+1)*4<lineeStm)
				    {
					 num++;
					 indietro.setEnabled(true);
				    }
			      if((num+1)*4==lineeStm)
			         avanti.setEnabled(false);

					 letturaFile(nameRub);
			         stampa();
				}
			 if(command.equals("indietro"))
			    {
                num=0;
                risCerca="";
                testoStp.setText("Inserisci il nome della rubrica        ");
                testoIns.setText("Inserisci il nome con cui salvare la rubrica        ");
                avanti.setEnabled(true);
                indietro.setEnabled(false);
				 pannelloIndex(true);
				 pannelloInput(false);
	             pannelloOutput(false);
				 pannelloRubIns(false);
				 pannelloRubStp(false);
				 pannelloCerca(false);
				 pannelloRisultati(false);

				}
	         if(command.equals("add"))
	            {
				try{
			         scrittura.println(nome);
			         scrittura.println(cognome);
			         scrittura.println(numero);
			         scrittura.println(indirizzo);
                 }catch(NullPointerException ex)
                  {
 	                System.out.println("File insesistente...                        ");
	             }
                    reset();

			    }

			  if(command.equals("invio"))
			  			    {
			  					test=true;

			                      if(nameRub.equals(""))
			                           test=true;
			  		             exFile = new File(nameRub);
			  		             if( ((exFile).exists()) || (test==false))
			  		               {
			  		                inNameRub.setText("");
			  		                if(test==false)
			  		                   testoIns.setText("Nome File non corretto.Riprova                        ");
			  		                else

			  		                   testoIns.setText("File già esistente. Riprova                         ");
			  					    }
			  					 else
			  					   {

			  					   pannelloRubIns(false);
			  	                   pannelloInput(true);
			  	                    try{
			  					             fileS = new FileOutputStream(nameRub);
			  					             scrittura = new PrintStream(fileS);
			  					        }catch(FileNotFoundException ex)
			  					        {
			  					    	   System.out.println("Errore in lettura File...");
			  	                        }

			  					   }


				}

				if(command.equals("invio "))
							    {
									test=true;

				                    if(nameRub.equals(""))
				                         test=false;
						             exFile = new File(nameRub);
						             if( ((exFile).exists()) && (test==true))
						               {
										letturaFile(nameRub);
										stampa();
				  					    pannelloRubStp(false);
					                    pannelloOutput(true);

						                }
									 else
									   {
										inNameRub.setText("");
						                if(test==false)
						                   testoStp.setText("Nome File non corretto.Riprova!                       ");
						                else

						                   testoStp.setText("File non esistente. Riprova!                        ");
									    }


				}

		      if(command.equals("reset"))
		        {
				 reset();
				}
		     if(command.equals("save & back"))
		        {
	             pannelloInput(false);
	             pannelloRubIns(false);
	             pannelloRubStp(false);
	             pannelloIndex(true);
	             pannelloOutput(false);
			    }
			 if(command.equals("cerca"))
			   {
				 test=true;
				 if(nameRub.equals(""))
                  test=false;
		         exFile = new File(nameRub);
                if( ((exFile).exists()) && (test==true))
				    {
				     stringa = inCerca.getText();
					 cont=0;
					 // ----------------------------------
					 // Variabile non usata
					 // con=0;
					 // ----------------------------------
					 linee=0;
					 cerca = new int[100];
				     cerca(stringa,nameRub);
				     i=0;
                    if(cerca[0]==0)
                      {
					    do{
					       stampaRisultati(cerca[i]-1,nameRub);
					       i++;
	                    }while(cerca[i]!=0);
					 }else{
					   risCerca="Nessun contatto trovato!!";
				     }
	                 risultati.setText(risCerca);
	                 pannelloCerca(false);
	                 pannelloRisultati(true);
				 }else{
		 			 inNameRub.setText("");
					 if(test==false)
					    testoStp.setText("Nome File non corretto.Riprova!                       ");
					 else
					    testoStp.setText("File non esistente. Riprova!                        ");
			     }


			   }
		     if(command.equals("Esci"))
		        {
	             System.exit(0);
			    }
			  if(command.equals("crea rubrica"))
			    {
	             pannelloRubIns(true);
	             pannelloIndex(false);
				}
			  if(command.equals("visualizza contatti"))
			    {
               pannelloRubStp(true);
               pannelloIndex(false);
				}
             if(command.equals("cerca contatti"))
              {
				 pannelloIndex(false);
				 pannelloCerca(true);
			   }
			 }
   void letturaFile(String nomeFile)
      {
		  int cont=0;
		  lineeStm=0;

     try{
        fileL = new FileReader(nomeFile);
        lettura = new BufferedReader(fileL);

        while( (linea = lettura.readLine()) != null )
	        {
	   		 lineeStm++;
	        }

        fileL = new FileReader(nomeFile);
        lettura = new BufferedReader(fileL);

           while( cont<num*4 )
	          {
		   	   cont++;
	   		   linea = lettura.readLine();
	        }

    }catch(FileNotFoundException ex)
	 {
	  System.out.println("Errore in lettura file, Nessun file trovato!");
	 }
	 catch(IOException ex)
	 {
	  System.out.println("Errore in lettura file..");
	 }

	   }

    void cerca(String stringRicerca,String nomeFile)
    {
     i=0;
	  try{
	      fileL = new FileReader(nomeFile);
		  lettura= new BufferedReader(fileL);
		  while( (linea = lettura.readLine()) != null )
		    {
		     linee++;
		    }
		  fileL = new FileReader(nomeFile);
		  lettura = new BufferedReader(fileL);
		  num=1;
		  do{
		     while( cont<num*4 )
		       {
		     	cont++;
			    linea = lettura.readLine();
			    if(linea != null)
			      {
			       if(linea.equals(stringRicerca))
				     {
				      cerca[i]=num;
				      i++;
			         }
				  }
	           }
	           num++;
		 }while(cont<linee);
	 }catch(FileNotFoundException ex)
    {
     System.out.println("Errore in lettura file..");
    }catch(IOException ex)
    {
     System.out.println("Errore in lettura file..");
    }
	}


   void stampaRisultati(int nume,String nomeFile)
   {
       cont=0;
       // ----------------------------------
		 // Variabile non usata
		 // con=0;
		 // ----------------------------------
       linee=0;
	    try{
	          fileL = new FileReader(nomeFile);
	          lettura = new BufferedReader(fileL);

	           while( (linea = lettura.readLine()) != null )
	 	         {
	 	   	  	  linee++;
	 	          }
	           fileL = new FileReader(nomeFile);
	           lettura = new BufferedReader(fileL);
	           while(cont<nume*4){
	 	   		   linea = lettura.readLine();
	 		   	   cont++;
	 	          }

	 			linea = lettura.readLine();
	 			  risCerca+="Nome: "+linea;

	 			linea = lettura.readLine();
	 			  risCerca+="\nCognome: "+linea;

	 			linea = lettura.readLine();
	 			  risCerca+="\nNumero Casa: "+linea;

	 			linea = lettura.readLine();
	 			  risCerca+="\nIndirizzo: "+linea+"\n\n";

	      }catch(FileNotFoundException ex)
	 	 {
	 	  System.out.println("Errore in lettura file..");
	 	 }
	 	 catch(IOException ex)
	 	 {
	 	  System.out.println("Errore in lettura file..");
	      }

	}

	void stampa()
	  {
	try{
         linea = lettura.readLine();
       stmNome.setText("                        Nome :  "+linea+"                                            ");

	  	  linea = lettura.readLine();
	    stmCognome.setText("                        Cognome :  "+linea+"                                  ");

		  linea = lettura.readLine();
       stmNumero.setText("                         Numero :  "+linea+"                                  ");

		  linea = lettura.readLine();
       stmIndirizzo.setText("                        Indirizzo :  "+linea+"                                  ");
   }catch(IOException ex)
   {
	  System.out.println("Errore in stampa Dati File.....");
	}catch(NullPointerException ex)
	{
     System.out.println("Nessun file ricevuto...");
	}

	  }

   void reset()
     {
		inNome.setText("");
		inCognome.setText("");
		inNumero.setText("");
		inIndirizzo.setText("");
	  }

	 void pannelloCerca(boolean att)
	   {
		testoStp.setVisible(att);
		inNameRub.setVisible(att);
		infoCerca.setVisible(att);
		inCerca.setVisible(att);
		Cerca.setVisible(att);
		back.setVisible(att);

	   }
   void pannelloRisultati(boolean att)
     {
	   risultati.setVisible(att);
	   back.setVisible(att);
	  }
	 void pannelloInput(boolean att)
	  {
	  nome.setVisible(att);
	  inNome.setVisible(att);
	  Cognome.setVisible(att);
	  inCognome.setVisible(att);
	  Numero.setVisible(att);
	  inNumero.setVisible(att);
	  Indirizzo.setVisible(att);
	  inIndirizzo.setVisible(att);

	  ok.setVisible(att);
	  reset.setVisible(att);
	  savExit.setVisible(att);

	  }
	 void pannelloOutput(boolean att)
	  {
      stmNome.setVisible(att);
	   stmCognome.setVisible(att);
      stmNumero.setVisible(att);
	   stmIndirizzo.setVisible(att);
	   avanti.setVisible(att);
	   back.setVisible(att);
      indietro.setVisible(att);
	  }
	  void pannelloRubIns(boolean att)
	   {
		testoStp.setVisible(false);
	    testoIns.setVisible(att);
	    inNameRub.setVisible(att);
	    invio.setVisible(att);
	    invio_.setVisible(false);
	   }

	  void pannelloRubStp(boolean att)
	   {
		testoIns.setVisible(false);
	    testoStp.setVisible(att);
	    inNameRub.setVisible(att);
	    invio.setVisible(false);
	    invio_.setVisible(att);
	    back.setVisible(att);
	   }
	  void pannelloIndex(boolean att)
	   {

        index.setVisible(att);
        indIns.setVisible(att);
        indStp.setVisible(att);
        indCer.setVisible(att);
        exit.setVisible(att);
        spazio.setVisible(att);
      }

}