/**
 * Rubrica.java
 * 
 * @author Alenadri Enrico - III G
 * @date 6/02/2009
 * Fixed by:
 * "helloIAmPau - Pasquale Boemio <boemianrapsodi[at]gmail.com>"
 * "b3by" - Antonio Bevilacqua <b3by.in.th3.sky[at]gmail.com>"
 * ----------
 * This is the first called class. It simply shows the jpanel "FramePri".
 * 
 * We have fixed:
 * 1. indentation
 * 2. added some inline documentation
 * 3. replaced show() method with setVisible() method of frame object. show() seems to be deprecated
 * 
 * NB: Some class or variables name are edited in order to respect the standard "camel notation"
 * to make this source code more readable. 
 */
package is2.rubrica;
import javax.swing.JFrame;


public class Rubrica{
	/**
	 * The main method. I don't have to add something else. 
	 * 
	 * @param args : This is some command line arguments. Be careful, Rubrica.java doesn't use it!
	 */
	public static void main(String[] args){
		
		int xLoc=400,   // posizione x dei frame nello schermo
			yLoc=400;   // posizione y dei frame nello schermo
		
		FramePri frame = new FramePri(xLoc,yLoc); // creo il pannello che verra` mostrato
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operazione da eseguire alla sua chiusura
		
		/*-------------------------------------------------
		 * FIX-3
		 * ------------------------------------------------ 
		 * Il metodo show() risulta essere deprecato; per evitare problemi futuri
		 * utilizziamo il metodo setVisible()
		 */
		//frame.show(); 
		frame.setVisible(true); // mostrami il pannello :)
		//-------------------------------------------------
     
	}
 }
