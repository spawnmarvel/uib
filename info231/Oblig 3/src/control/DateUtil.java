package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
/**
 * The class ensures that dates are valid and correctly inserted by the users.
 * @author dro068 (David Ronen).
 *
 */
public class DateUtil {
	/**
	 * The method ensures valid dates and correct format:
	 * Valid date is tested by checking that the given date, of the new or rescheduled shift, does not precede 
	 * the day of creating/rescheduling. 
	 * Valid format is tested by invoking validateDate() of this class.
	 * @param inNewDate - the date entered by the user.
	 * @return - true if the date is valid. false otherwise.
	 */
	public boolean isLegalTime(String inNewDate){
		DateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		try {
			if(validateDate(inNewDate, true, "dd-MM-yy")){
				Date  startDate = sdf.parse(inNewDate);
				Date  endDate = sdf.parse(getDate());
				GregorianCalendar startGC = new GregorianCalendar();
				startGC.setTime(startDate);
				GregorianCalendar endGC = new GregorianCalendar();
				endGC.setTime(endDate);
				if(startGC.after(endGC)|| startGC.equals(endGC)){
					return true;
				}
				else{
					failedFeedBack("Vaktdatoen kan ikke være før dagens dato!");
					return false;
				}
			}
			else{
				failedFeedBack("Ugyldig dato");

			}
		}
		catch (Exception NumberFormatException) {
			failedFeedBack("Ugyldig dato");
		}
		return false;
	}
	/**
	 * The method returns the date at time of invoking.
	 * @return - the current date at invoking time.
	 */

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);

	}

	/**
	 * The method sends a message if an operation failed.
	 * @param inMessage
	 */
	public void failedFeedBack(String inMessage){
		FeedbackHandler.failedFeedback(inMessage);
	}
	/**
	 * The method test whether date is given in a valid format.
	 * @param dateStr- the date entered by the user.
	 * @param allowPast- also dates earlier than the date at invoking.
	 * @param formatStr- the right format, in this case: dd-MM-yy.
	 * @return - true if valid, false otherwise.
	 */

	public static boolean validateDate(String dateStr, boolean allowPast, String formatStr){
		if (formatStr == null) return false;
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		Date testDate = null;
		try{
			testDate = df.parse(dateStr);
		}
		catch (ParseException e){
			return false;
		}
		if (!allowPast){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			if (cal.getTime().after(testDate)) return false;
		}
		if (!df.format(testDate).equals(dateStr)) return false;
		return true;
	}
}
