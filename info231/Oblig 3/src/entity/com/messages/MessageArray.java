package entity.com.messages;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JOptionPane;


/**
 * The class holds - and provides procedures to manipulate - the list of Messages.
 *   
 * @author Aslak Roedder, Hedvig Andersland
 * 
 *
 */

public class MessageArray  {
	
	private  ArrayList<Mess> mess;

    /**
     * Constructor for objects of class MessageArray
     */
    public MessageArray ()
    {
       mess = new ArrayList<Mess>();
    }
    
    /**
     * The method returns the size of the list of Messages.
     * @return
     */

    public int getMessSize(){
    	return mess.size();
    }
   /**
    * The method adds an Message to the list by receiving an Message object as an argument..
    * @param aMessage
    * @return - true if the Message was successfully added, false otherwise.
    */
    public boolean addMessage(Mess aMessage){
        for(Mess entry : mess){
            if (entry.getMelding().equalsIgnoreCase(aMessage.getMelding())){
                int n = JOptionPane.showConfirmDialog(null,
                        "Foelgende melding: " + aMessage.getMelding() + " finnes i systemet" + "\n" +
                        "Fortsette?", "Fortsette?", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION) {
                        	mess.add(aMessage);
                        	return true;
                        }

                        else if(n == JOptionPane.NO_OPTION) {
                        	return false;
                        	}
                        }
            }
        mess.add(aMessage);
        return true;
        }
    
    /**
     * The method adds an Message to the list by receiving the message, importance and date of deletion as arguments..
     * @param message - the message.
     * @param importance - of the message.
     * @return - true if the Message was successfully added, false otherwise.
     */
    public boolean addMessage(Date delDate, Date date, int importance, String message, int empId, boolean done){
        for (Mess aMessage : mess){
            if (aMessage.getMelding()== message){
                return false;
            }
        }
        Mess myMessage = new Mess(delDate, date, importance, message, empId, done);
        mess.add(myMessage);
        return true;
    }
    
   /**
    * The method finds an Message in the list by matching identical messages
    * @param message
    * @return - the Message object if a match was made.
    * 
    * TODO: Searches the message. What if the same message exists multiple places? Should we search for other variables?
    */
    public Mess findMessage(String melding){
        String searchString = melding;
        for (int i = 0; i < mess.size(); i++){
            Mess aMessage = mess.get(i);
            if (aMessage.getMelding() == searchString){
                return aMessage;
            }
        }
        return null;
    }
    
   /**
    * The method verifies that a given message  actually exists in the list of Messages. 
    * @param message - the message to be verified.
    * @return - true if such a number exists in the list, false otherwise.
    */
    public boolean isMess(String message){
    	for (Mess entry : mess){
    		if(entry.getMelding()== message){
    			return true;
    			}
    		}
    	failedFeedBack("meldingen finnes ikke i systemet");
    	return false;
    	}
    

    /**
     * The method deletes a message entered by the user.
     * @param message - the message to be deleted. 
     * @return - true if the delete operation was successful, false otherwise.
     */

    public boolean deleteMessage(Date delDate, Date date, int importance, String melding, int empId, boolean done){
    	if(isMess(melding)){
        Iterator<Mess> it = mess.iterator();
        while (it.hasNext()){
            Mess aMessage = (Mess)it.next();
            if(aMessage.getMelding() == melding){
            	Mess myMessage = aMessage;
                mess.remove(myMessage);
                return true;
            }
            }
        }
        failedFeedBack("Sletting mislyktes!");
        return false;
    }

    /**
     * This method gives an warning that an operation failed.
     * @param feedBackText - the message given.
     */
    public void failedFeedBack(String feedBackText){
    	String title = "Feil oppstod!";
		String message = feedBackText;
		JOptionPane.showMessageDialog(null,message,title,
				JOptionPane.OK_CANCEL_OPTION);
    }
    
    /**
     * The method returns the list of Messages.
     * @return - the list of Messages.
     */
    public ArrayList<Mess>getList(){
    	return mess;
    }
    /**
     *  initiates the list of Messages
	 * assigns the arrayList of Messages "mess" to the actual list from database.
     * @param innList
     */
    public void setList(ArrayList<Mess> innList){
    	mess = innList;
    }
    
    
    /**
     * the method prints the toString representation of the object.
     */
    public void print() {
        System.out.println(toString());
    }
    
    /**
     * The method returns a string representation of the object.
     */
    public String toString(){
        String returnString = "Message: \n";
        for(Mess entry : mess){
            returnString += "Delete date: " + entry.getDeleteDate() + ". importance: " + entry.getImportance() + ". Message: " + entry.getMelding() + ". \n";
        }
        return returnString;
    }

}

