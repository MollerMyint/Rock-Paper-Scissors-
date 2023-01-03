//package finalGameProject;
import java.io.*;
import java.net.*;

public class Client {
	private static String host = "10.110.174.208";
	//A port number serves as a communication endpoint between programs, 

	private static Integer port = 1337;

	private static Double versionNumber = 1.0;

	private static String messageWelcome = "--- Welcome to the Game "+ versionNumber + " --- \n";

	private static String messageRules = "\nRule set:\n - (R)ock beats (S)cissors\n - (S)cissors beats (P)aper\n - (P)aper beats (R)ock\n";
	public static void main(String args[]) throws Exception {

	String input = "";
	String response;

	System.out.println(Client.messageWelcome);

	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	Socket clientSocket = new Socket(Client.host, Client.port);
	DataOutputStream outToServer = new DataOutputStream(
	clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	do 
	{
		// Prompt user for select rock, paper or scissors ...
		System.out.println("Select (R)ock, (P)aper, (S)cissors");
		System.out.print("or type \"Rules\" in order to see the rules: ");	
		input = inFromUser.readLine();
		
		while(true)
		{
			if (input.equals("Rules")) 
			{
				System.out.println(Client.messageRules);
				break;
			}

			if (input.equals("R") || input.equals("P") || input.equals("S"))
			{
				break;
			}
			
			System.out.println("Input not valid \n");
			break;
		}	
	}
	
	while (!input.equals("R") && !input.equals("P") && !input.equals("S"));
		outToServer.writeBytes(input + "\n");

		System.out.println("\nYour input ("+ input + ") was successfully transmitted to the server. Its the next players turn");

		// Catch respones
		response = inFromServer.readLine();
		
		// Display respones
		System.out.println("Response from server: " + response);

		clientSocket.close();

	}
}