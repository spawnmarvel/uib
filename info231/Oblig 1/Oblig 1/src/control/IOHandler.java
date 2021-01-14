package control;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import schedulePlanner.Employee;
import schedulePlanner.EmployeeArray;
import schedulePlanner.Shift;
import schedulePlanner.ShiftArray;

/**
 *The class controls writing and reading objects to and from text or object files.
 * @author David Ronen.
 *
 */

public class IOHandler {
	
	/**
	 * Constructor of IOHandler.
	 */

	public IOHandler(){
		
	}

    /**
     * Saves the  objects to disk.
     */
    public void saveToFile(Object o, String path){
    	try
    	{
        	FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
    		}
    	catch(IOException i1)
    	{
    		i1.printStackTrace();
    		}
    	}

    /**
     * The method sends an error message when an operation fails.
     * @param inMessage
     */
        public void showExceptionMessage(String inMessage){
			String title = "An error accured!";
			String message = inMessage;
			JOptionPane.showMessageDialog(null,message,title,
					JOptionPane.OK_CANCEL_OPTION);
			}

        /**
         * Reads an object from disk.
         */
        public Object readFromFile(Object o, String path){
        	Object obj = null;

         try {
             FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream in = new ObjectInputStream(fileIn);
            	 obj = (Object) in.readObject();
            	 in.close();
            	 fileIn.close();

         }
             catch (IOException exc) {
                 String errorMessage2 = "Error: File not found or the file is unreadable";
                 showExceptionMessage(errorMessage2);
                 exc.printStackTrace();


             } catch (ClassNotFoundException e) {
                 String errorMessage3 ="Error: The class is not visible on the logical classpath of the context class loader.";
                 showExceptionMessage(errorMessage3);
                 e.printStackTrace();

			}
             return obj;
        }

        /**
         * The method writes the list of shifts and list of employees to a text file.
         * @param l - the list i.g the (EmployeeArray)empArr or (ShiftArray)shiftArr object.
         * @param path - to the file.
         */
    	public void writeToFile(Object l, String path) {
            System.out.println("writing to " + path);
            BufferedWriter out = null;
            try {
                File file = new File(path);
                out = new BufferedWriter(
                      new FileWriter(file));
                if(l instanceof EmployeeArray){
                for (int i = 0; i < ((EmployeeArray) l).getStaffSize(); i++ ) {
                	Employee myEmp = ((EmployeeArray) l).getList().get(i);
                    out.write(myEmp.toString());
                    out.newLine();
                    }
                }
                else if(l instanceof ShiftArray){
                    for (int i = 0; i < ((ShiftArray) l).getAtWorkSize(); i++ ) {
                    	Shift myShift = ((ShiftArray) l).getList().get(i);
                        out.write(myShift.toString());
                        out.newLine();
                }
                }
                out.close();
                }
            catch(IOException e) {
            	String errorMessage4 ="Error: An error occured!";
                showExceptionMessage(errorMessage4);
            	System.out.println("IO error for " + path + ": " + e.getMessage());
            	}
            }


    	/**
    	 * The method reads the files.txt and returns theirs content to be presented on the main screen. 
    	 * @param path - to the file.
    	 * @return - text presentation of the file's content.
    	 */
    	public ArrayList<String> readDataFromFile(String path) {
    		ArrayList<String> arrayOfStrings = new ArrayList<String>();
    		try {
    			BufferedReader reader = new BufferedReader(new FileReader(path));
    			String line = "";
    			while ((line = reader.readLine()) != null) {
    				arrayOfStrings.add(line + "\n");
    				}
    			reader.close();
    			}
    		catch (Exception ex) { System.out.println("Exception: " + ex.getMessage()); }
    		return arrayOfStrings;
    		}
    	}



