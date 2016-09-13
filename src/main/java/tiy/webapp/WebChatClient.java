package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jfabiano on 9/12/2016.
 */
public class WebChatClient {

    final String HOST_ADDRESS = "localhost";
    final int PORT_NUMBER = 8005;
    String message = "";
    PrintWriter outToServer;
    BufferedReader inFromServer;
    Socket clientConnection;

    public static void main(String[] args) {
        new WebChatClient();

    }

    public void establishConnection() {
        try {
            clientConnection = new Socket(HOST_ADDRESS, PORT_NUMBER);

            System.out.println("Connected!");

            // once we connect to the server, we also have an input and output stream
            outToServer = new PrintWriter(clientConnection.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            System.out.println("Input and Output Streams initialized");

            // we could replace this with a name we get from the user
            outToServer.println("name=" + "client-baseline");
            // make sure we capture the response coming back from the server
            String serverResponse = inFromServer.readLine();
            System.out.println("Server says: " + serverResponse);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

        }
    }

    public String sendMessage(String message)//send single message method needed for the unit test, or
    {
        this.message = message;
        //return null;
        try {
        Socket clientConnection = new Socket(HOST_ADDRESS, PORT_NUMBER);

        System.out.println("Connected!");

        // once we connect to the server, we also have an input and output stream
        PrintWriter outToServer = new PrintWriter(clientConnection.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
        System.out.println("Input and Output Streams initialized");

        // we could replace this with a name we get from the user
        outToServer.println("name=" + "client-baseline");
        // make sure we capture the response coming back from the server
        String serverResponse = inFromServer.readLine();
        System.out.println("Server says: " + serverResponse);

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your message to send: " );
            String userMessage = inputScanner.nextLine();

            if (userMessage.equalsIgnoreCase("exit")) {
                break;
            }

            outToServer.println(userMessage);
            serverResponse = inFromServer.readLine();
            System.out.println("Server says: " + serverResponse);

        }


        // these should be in a finally block, but keeping here for simplicity
        inFromServer.close();
        outToServer.close();
        clientConnection.close();

    } catch (Exception exception) {
        exception.printStackTrace();
    } finally
        {

        }
        return this.message;


    }
    public String sendSingleMessage(String message) {
        this.message = message;
        //Boolean sent = false;
        String input = "";
            try {

                outToServer.println(this.message);

                    //return inFromServer.readLine();
                    input = inFromServer.readLine();
                    //sent = true;


            }
            catch(IOException ex)
            {

            }
            // these should be in a finally block, but keeping here for simplicity
        if (input != "")
        {
            return input;
        }
        else {
            return "IO Exception issue occurred.";
        }

    }
    public void closeConnection()
    {
        try {
            inFromServer.close();
            outToServer.close();
            clientConnection.close();
        }
        catch(IOException ex)
        {

        }
    }

}
