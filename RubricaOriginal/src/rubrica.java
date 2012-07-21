/**
 * Autore :Alenadri Enrico
 * Classe : III G
 * Data :  6/02/2009
 * Nome File:  rubrica.java
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.io.*;


 public class rubrica{

 	public static void main(String[] args)
     {
 	 int xLoc=400,   //posizione x dei frame nello schermo
 	     yLoc=400;   //posizione y dei frame nello schermo


     framePri frame = new framePri(xLoc,yLoc);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.show();
     }
 }


 class panelInserimento extends JPanel implements ActionListener
 {
   private boolean exist=true;
   private int x=0,
               num=0,
		       linee=0,
		       lineeStm=0,
		       i,
		       cont,
		       con;
   private String nameRub,
                  linea,
                  stringa,
                  risCerca="";
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
                  Congome,
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
   public panelInserimento()
    {

     exit = new JButton("esci");
     spazio= new JLabel("                              ");

     //Componenti Index
	 index = new JLabel("       Cosa vuoi fare?                                    ");
	 indIns = new JButton("Crea rubrica");
	 indStp = new JButton("Visualizza contatti");
	 indCer = new JButton("Cerca Contatti");




     //Componenti inserimento nome Rubrica-inserimento scherda/stampa scheda
	 testoIns = new JLabel("Inserisci il nome con cui salvare la rubrica        ");
	 testoStp = new JLabel("Inserisci il nome della rubrica        ");
	 inNameRub = new TextField(10);
	 invio = new JButton("Invio");
	 invio_ = new JButton("Invio ");

	 //Componenti Cerca
	 Cerca = new JButton("Cerca");
	 infoCerca = new JLabel("Puoi cercare per Nome-Cognome-Numero-indirizzo");
	 inCerca = new TextField(10);
	 risultati = new TextArea(10,35);
	 risultati.setEditable(false);
	 risultati.setCaretPosition(0);



	 //Componenti inserimento scheda
	  nome = new JLabel("                Nome");
	  Congome = new JLabel("                Cognome");
	  Numero = new JLabel("                Numero Casa");
	  Indirizzo = new JLabel("                Indirizzo");
	  ok = new JButton("Add");
	  reset = new JButton("Reset");
	  savExit = new JButton("Save & Back");
	  inNome = new TextField(17);
	  inCognome = new TextField(17);
	  inNumero = new TextField(17);
	  inIndirizzo = new TextField(17);

     //Componenti stampa Scheda
     stmNome = new JLabel("Nome:");
     stmCognome = new JLabel("Cognome:");
     stmNumero = new JLabel("Numero:");
     stmIndirizzo = new JLabel("Indirizzo");
     avanti = new JButton(">>");
     back = new JButton("Indietro");
     indietro = new JButton("<<");



     nameRub = inNameRub.getText();

      //Aggiungo componenti index
      add(index);
      add(indIns);
      add(indStp);
      add(indCer);
      add(exit);

      indIns.addActionListener(this);
      indStp.addActionListener(this);
      indCer.addActionListener(this);
      exit.addActionListener(this);

      //Aggiungo componenti Inserimento rubrica
      add(testoStp);
 	  add(testoIns);
	  add(inNameRub);
	  add(invio);
	  add(invio_);

	  invio.addActionListener(this);
	  invio_.addActionListener(this);


     //Aggiungo componenti Cerca
      add(risultati);
      add(infoCerca);
      add(inCerca);
      add(Cerca);

      Cerca.addActionListener(this);

    ///Aggiungo componenti Input contatti
	  add(nome);
	  add(inNome);
	  add(Congome);
	  add(inCognome);
	  add(Numero);
	  add(inNumero);
	  add(Indirizzo);
	  add(inIndirizzo);
	  add(ok);
	  add(reset);
	  add(savExit);

	  ok.addActionListener(this);
	  reset.addActionListener(this);
	  savExit.addActionListener(this);


      //Aggiungo componenti Visualizzazione contatti
      add(stmNome);
      add(stmCognome);
      add(stmNumero);
      add(stmIndirizzo);
      add(indietro);
      add(back);
      add(avanti);
      indietro.setEnabled(false);

      avanti.addActionListener(this);
      back.addActionListener(this);
      indietro.addActionListener(this);



      //Setto i componenti per visualizzare la scherata di index
	  pannelloInput(false);
	  pannelloOutput(false);
	  pannelloRubIns(false);
	  pannelloCerca(false);
	  pannelloRisultati(false);



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
					 con=0;
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
        con=0;
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
	  Congome.setVisible(att);
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




class framePri extends JFrame
 {
    public framePri(int xLoc,int yLoc)
    {

 	setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
 	setTitle("Rubrica 2.0  by Aleandri Enrico");
 	setResizable(false);
 	setLocation(xLoc,yLoc);
 	panelInserimento panelIns = new panelInserimento();
 	Container contentPane = getContentPane();
 	contentPane.add(panelIns);
    }
     public static final int DEFAULT_WIDTH =300 ;
     public static final int DEFAULT_HEIGHT =245 ;
}
