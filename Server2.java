package palindrome;

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
		String palindrome = ""; 
		
		while (!palindrome.isEmpty()){
			try {
				palindrome = ois.readUTF();
				}
				catch (EOFException e1) {
					System.exit(0);
				}	
		String result = toString(isPalindrome(palindrome)); 
		oos.writeUTF(result);
		oos.flush(); 
	
		}
		//close the ServerSocket object and resources
        s.close();
        ois.close();
        oos.close();
        ss.close(); 
	}

	private static String toString(boolean palindrome) {
		// TODO Auto-generated method stub
		if (palindrome == true) {
			return "true"; 
		}
			return "false"; 
	}

	private static boolean isPalindrome(String palindrome) {
		// TODO Auto-generated method stub
		String stringOne = filter(palindrome);
		String stringTwo = reverse(stringOne); 
		return stringTwo.equals(stringOne);
	}

	private static String reverse(String stringOne) {
		// TODO Auto-generated method stub
		StringBuilder reverse = new StringBuilder(stringOne); 
		reverse.reverse(); 
		return reverse.toString();
	}

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


