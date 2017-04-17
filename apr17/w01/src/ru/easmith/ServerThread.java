package ru.easmith;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread extends Thread {

    private Socket socket = null;
    private int clientNumber;

    public ServerThread(Socket socket, int clientNumber) {
        super("ServerThread#" + clientNumber);
        this.socket = socket;

        this.clientNumber = clientNumber;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Client #" + clientNumber + ".");

            while (true) {
                String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
                out.println("Answer on: " +input);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Client #" + clientNumber + " IOException");
            }
            System.out.println("Connection # " + clientNumber + " closed");
        }
    }


}
