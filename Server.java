//package finalGameProject;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

private static Integer port = 1337;

private static Double versionNumber = 1.0;

private static String welcomeMessage = "--- Welcome to the Game " + versionNumber + " --- \n";

private static int getPort() 
{
	Integer input;
	
	Scanner sc = new Scanner(System.in);
	
	do 
	{
		System.out.print("insert \"0\" in order to continue with the default setting (" + Server.port + "): ");
		input = sc.nextInt();
	} 
	
	while (input != 0 );
	
	sc.close();
	
	return input == 0 ? Server.port : input;
}

public static void main(String args[]) throws Exception {
	String resClient_1 = "";
	String resClient_2 = "";
	String inputClient_1;
	String inputClient_2;
	
	System.out.println(Server.welcomeMessage);
	
	Server.port = Server.getPort();
	
	// Create new server socket & dump out a status msg
	ServerSocket welcomeSocket = new ServerSocket(Server.port);
	System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort() + " ...");
	
	while (!welcomeSocket.isClosed()) 
	{
		// iPlayer one
		Socket client_1 = welcomeSocket.accept();
		if (client_1.isConnected()) 
		{
			System.out.println("\nPlayer one (" + (client_1.getLocalAddress().toString()).substring(1) + ":"+ client_1.getLocalPort() + ") has joined ... waiting for player two ...");
		}
		DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
		BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));
		
		// Player two
		Socket client_2 = welcomeSocket.accept();
		if (client_2.isConnected()) 
		{
			System.out.println("Player two (" + (client_2.getLocalAddress().toString()).substring(1) + ":"+ client_1.getLocalPort() + ") has joined ... lets begin ... best out of 1 ");
		}

		DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
		BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));
		
		// Get client inputs
		inputClient_1 = inClient_1.readLine();
		inputClient_2 = inClient_2.readLine();
		
		if (inputClient_1.equals(inputClient_2)) 
		{
			resClient_1 = "Draw";
			resClient_2 = "Draw";
			System.out.println("It's a draw. ");
		}
		
		else if (inputClient_1.equals("R") && inputClient_2.equals("S")) 
		{
			resClient_1 = "You win";
			resClient_2 = "You lose";
			System.out.println("Player one wins.");
		}
		
		else if (inputClient_1.equals("S") && inputClient_2.equals("R")) 
		{
			resClient_1 = "You lose";
			resClient_2 = "You win";
			System.out.println("Player two wins. ");
		}
		
		else if (inputClient_1.equals("R") && inputClient_2.equals("P")) 
		{
			resClient_1 = "You lose";
			resClient_2 = "You win";
			System.out.println("Player two wins. ");
		}
		
		else if (inputClient_1.equals("P") && inputClient_2.equals("R")) 
		{
			resClient_1 = "You win";
			resClient_2 = "You lose";
			System.out.println("Player one wins.");
		}
		
		else if (inputClient_1.equals("S") && inputClient_2.equals("P")) 
		{
			resClient_1 = "You win";
			resClient_2 = "You lose";
			System.out.println("Player one wins.");
		}
		
		else if (inputClient_1.equals("P") && inputClient_2.equals("S")) 
		{
			resClient_1 = "You lose";
			resClient_2 = "You win";
			System.out.println("Player two wins.");
		}
		
		outClient_1.writeBytes(resClient_1.toUpperCase());
		outClient_2.writeBytes(resClient_2.toUpperCase());
		client_1.close();
		client_2.close();
		
		
		System.out.println("\nWaiting for new players ...\n");
		}
	}
}