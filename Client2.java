package palindrome;

import java.io.*;
import java.net.*;


public class Client2 {
	static Socket s; //Client socket for connecting to the server
	static String server = "137.21.142.161"; //Use localhost by default
	static int serverPort = 1221; //Use port 1221 by default
	static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    static BufferedReader br = null;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length < 1) {
			s = new Socket(server, serverPort);
		}
		else {
			s = new Socket(args[0], serverPort); 
		}
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		br = new BufferedReader(new InputStreamReader(System.in));
		//prompt for a string to check and assign to a variable
		System.out.println("Please enter a String to check: ");
		String outgoing = br.readLine(); 
		//exit upon "exit" being assigned
		while(!outgoing.equals("")) {
			//send a message to the server
			oos.writeUTF(outgoing);
			oos.flush();
			//read the server response message
			String message = ois.readUTF();
			System.out.println("The String is a palindrome is " + message);
			//prompt for another string or exit
			System.out.println("Please enter a String to check: ");
			outgoing = br.readLine();
		}
        //close resources
		System.out.println("Have a Nice Day!");
        ois.close();
        oos.close();
        s.close(); 
	}

}
