package oblig1.myStack;
import java.util.Scanner;
/**
 * This class converts a regular arithmetic expression in to reverse polish notation,
 * on the postfix form, by using the scanner class to read input.
 * @author jk070
 * Date 28/09/2011 time 21:40
 */

public class Converter {

	private MyStack<String> ms;//the stack
	private MyStack<String> ar;
	private String inFix;//the regular expression
	private String postFix = "";//the converted postFix
	private String temp;//the temporary string
	/**
	 * The constructor for the converter class
	 */
	public Converter(){
		temp = "";
		ms = new MyStack<String>();
		ar = new MyStack<String>();
	}
	public String getPostFix() {
		return postFix;
	}
	/**
	 * Puts the postfix expression on a stack
	 * method not in use.
	 * @param postfix
	 * @return stack toString
	 */
	public String ResultFromExpPost(String postfix) { 
		char s;
		MyStack<Character> val = new MyStack<Character>();
		for(int i = 0; i < postfix.length(); i++) {
			s = postfix.charAt(i);
			val.push(s);
		}
		return val.toString();
	}
	/**
	 * The convert method: which converts from infix to postfix
	 * expression
	 * Input the string to convert
	 * @param exp
	 */
	//this method runs in linear time O(N) due to the input strings 
	public String ConvertExp(String exp) {
		inFix = exp;
		for (int i=0;i<inFix.length();i++){
			temp = inFix.substring(i,i+1);       
			if(temp.matches("[0-9]")) { //the input can only be a digit, append postfix expression directly
				postFix += temp;
				//ar.push(temp);
			}
			else if (isThisOperator(temp)){
				if (ms.isEmpty()){//If ms is empty push the char.
					ms.push(temp);
					//ar.push(temp);

				}
				else{
					String top = ms.top();
					while (getPrecedOrder(top,temp).equals(top)&& !(ms.isEmpty())){
						postFix += ms.pop();
						if (!(ms.isEmpty()))
							top = ms.top();
					}
					ms.push(temp);
					//ar.push(temp);
				}
			}
		}
		// append all the operators from the stack to the postfix exp.
		while(!(ms.isEmpty()))
			postFix += ms.pop();
		//System.out.println(ar.toString());
		return postFix;
	}

	/**
	 * This method find out if a character in a staring is an operator or not
	 * Returns true if isOp(is Operator) is an operator, 
	 * false otherwise
	 * @return true if is operator, false otherwise
	 */
	//runs in O(1)
	public boolean isThisOperator(String isOp){

		String ope = "*/%+-";
		if (ope.indexOf(isOp) != -1)
			return true;
		else
			return false;
	}

	
	/**
	 * Returns and calculates the operator with higher precedence between operatorOne and operatorTw0,
	 * if they have the same precedence, the first operator in the argument list operatorOne is returned.
	 * @param operatorOne
	 * @param operatorTwo
	 * @return one of them
	 */
	//runs in O(1)
	public String getPrecedOrder(String operatorOne, String operatorTwo){

		String highPre = "*/%";
		String lovPre = "+-";
		if ((highPre.indexOf(operatorOne) != -1) && (lovPre.indexOf(operatorTwo) != -1))
			return operatorOne;
		else if ((highPre.indexOf(operatorTwo) != -1) && (lovPre.indexOf(operatorOne) != -1))
			return operatorTwo;
		else if((highPre.indexOf(operatorOne) != -1) && (highPre.indexOf(operatorTwo) != -1))
			return operatorOne;
		else
			return operatorOne;
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("Give a regular expression(ex: 2+2 or ((2+2))*5):");
		Scanner scanner = new Scanner(System.in);
		String expression = scanner.nextLine();
		System.out.println("The expression:" +expression);
		Converter con = new Converter();		
		System.out.println("The Converted: postfix : " +con.ConvertExp(expression));



	}

}