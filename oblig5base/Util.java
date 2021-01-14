/**
 * Utilities for the game
 * 
 * @Author Andreas Bjerknes
 * @version 23.04.2010
 */
public class Util
{

    /**
     * Method that checks the length of a string
     * @param input The input of a string
     * @return input or "Unspecified"
     */
    public static String checkString(String input)
    {
        input = input.trim();
        if(input.length() > 0){
            return input;
        }
        else{
            return "Unspecified";
        }
    }
    /**
     * Method that checks that the input is a positive integer.
     * @param input The input of an int
     * @return input or 0
     */
    public static int checkNotNegativeInt(int input){
        if(input >= 0){
            return input;
        }
        else{
            return 0;
        }
    }
}
