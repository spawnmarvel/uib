package control;
import javax.swing.*;
/**
 * this class controls the different feedback messages and gives the right feedback
 * @author tommy
 *
 */

public class FeedbackHandler {
	
	/**
     * The method gives a negative feedback when an operation is successful.
     * @param feedBackText
     */
	public static void failedFeedback(String errorMessage){
			String title = "En feil har oppstått";
			String message = errorMessage;
			JOptionPane.showMessageDialog(null, message, title,
					JOptionPane.OK_CANCEL_OPTION);
	}
	
    /**
     * The method gives a positive feedback when an operation is successful.
     * @param feedBackText
     */
    public static void successfulFeedBack(String feedBackText) {
		   String title = "Operasjon vellykket";
		   String message = feedBackText;
		   JOptionPane.showMessageDialog(null, message, title,
				   JOptionPane.OK_CANCEL_OPTION);
    }
	
    
}
