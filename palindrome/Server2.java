package palindrome;
/*
 * Brandon Hettler and Kyle O'Brien
 * 4/20/2019
 * CIS 419
 * The server class, utilizing 
 * the filter, reverse, toString, and
 * isPalindrome methods, takes a string
 * from the client class, and determine 
 * whether it is a palindrome. The server
 * class then returns the result back to the client. 
 * The server repeats this until the enter key is 
 * pressed. 
 */
import java.io.*;
import java.net.*;

public class Server2 {
	static ServerSocket ss = null; //Server socket to offer service
	static int serverPort = 1221; //Use port 1221 by default
	static Socket s; //Socket to accept connection 
	static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    

	public static void main(String[] args) throws IOException {
		//Establish requested service
		ss = new ServerSocket(serverPort);
		s = ss.accept();
		ois = new ObjectInputStream(s.getInputStream());
		oos = new ObjectOutputStream(s.getOutputStream());
		//declare and initialize the string 
		String palindrome = "a"; 
		
		//check whether the palindrome string is empty
		//and exit if it is empty 
		while (!palindrome.isEmpty()){
			try {
				//initialize the string to the input
				palindrome = ois.readUTF();
				}
				catch (EOFException e1) {
					System.exit(0);
				}		
			//declare the string result
			//and initialize it to the result
			//of the toString of the isPalindrome
			//with the lowercase of the palindrome string
		String result = toString(isPalindrome(palindrome.toLowerCase())); 
		//send the result back to the client
		oos.writeUTF(result);
		oos.flush(); 
	
		}
		//close the ServerSocket object and resources
        s.close();
        ois.close();
        oos.close();
        ss.close(); 
	}

	//takes the result of palindrome and 
	//returns the result as a string
	private static String toString(boolean palindrome) {
		// TODO Auto-generated method stub
		if (palindrome == true) {
			return "true"; 
		}
			return "false"; 
	}
    //from the parameter string, 
	//a filtered version of the parameter string
	//and a reverse of the filtered string 
	//are created. Whether they are
	//equal is returned. 
	private static boolean isPalindrome(String palindrome) {
		// TODO Auto-generated method stub
		String stringOne = filter(palindrome);
		String stringTwo = reverse(stringOne); 
		return stringTwo.equals(stringOne);
	}

	//takesa string and reverses it 
	private static String reverse(String stringOne) {
		// TODO Auto-generated method stub
		StringBuilder reverse = new StringBuilder(stringOne); 
		reverse.reverse(); 
		return reverse.toString();
	}

	//takes a string and filters out anything not a 
	//digit or a letter 
	private static String filter(String palindrome) {
		// TODO Auto-generated method stub 
		StringBuilder build = new StringBuilder(); 
		for (int i = 0; i < palindrome.length(); i++) {
			if (Character.isLetterOrDigit(palindrome.charAt(i)))
				build.append(palindrome.charAt(i));
		}
		return build.toString(); 
		}

	}
