/**
 * FramePri.java
 * 
 * @author Alenadri Enrico - III G
 * @date 6/02/2009
 * Fixed by:
 * "helloIAmPau - Pasquale Boemio <boemianrapsodi[at]gmail.com>"
 * "b3by" - Antonio Bevilacqua <b3by.in.th3.sky[at]gmail.com>"
 * ----------
 * This is the frame object showed by Rubrica.java.
 * We suppose the purpose of the author is to have an object representative of the view with 
 * which the user interacts. 
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

import java.awt.Container;
import javax.swing.JFrame;

class FramePri extends JFrame {
	
    // L'oggetto JFrame e` un oggetto "Serializable". Poiche` abbiamo seri problemi,
	// preferiamo eliminare anche tutti i "warning" presenti nel codice, e quindi 
	// assegnamo a questo un id autogenerato!
	private static final long serialVersionUID = 5630246559558602437L;
	// Ecco quanto dovra` essere grande il mio pannello!
	public static final int DEFAULT_WIDTH = 300 ;
    public static final int DEFAULT_HEIGHT = 245 ;
	
    /**
     * This method builds the panel and puts it at location (xLoc,yLoc) on the screen. 
     * 
     * @param xLoc : This is the location of the panel on the axis x
     * @param yLoc : This is the location of the panel on the axis y
     */
	public FramePri(int xLoc,int yLoc){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT); // setta la dimensione
		setTitle("Rubrica 2.0  by Aleandri Enrico"); // setta il titolo
		setResizable(false); // non permettere all'utente di fare come cacchio gli pare
		setLocation(xLoc,yLoc); // mettilo proprio li, aspe` un po` piu` a destra, ok!
		PanelInserimento panelIns = new PanelInserimento(); // crea il contenuto del pannello 
		Container contentPane = getContentPane(); // prendi il contenitore
		contentPane.add(panelIns); // in tavola!
		
	}
}
