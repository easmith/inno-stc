package ru.easmith;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Queue;

public class ServerThread extends Thread {

    private Socket socket = null;
    private List<ServerThread> threads;

    public int getClientNumber() {
        return clientNumber;
    }

    private int clientNumber;

    public String getClientName() {
        return clientName;
    }

    private String clientName;
    private Queue messages;
    private BufferedReader in;

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    private PrintWriter out;

    public ServerThread(Socket socket, int clientNumber, List<ServerThread> threads) {
        super("ServerThread#" + clientNumber);
        this.socket = socket;
        this.threads = threads;

        try {
            this.in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);


        } catch (IOException e) {
            e.printStackTrace();
        }

        this.clientNumber = clientNumber;
    }

    public void run() {
        try {

            out.println("Клиент #" + clientNumber + ". Напиши свое имя");
            this.clientName = in.readLine();
            out.println("Имя '" + this.clientName + "' принято. Приятного общения!");

            while (true) {
                String input = in.readLine();
                if (input == null || input.equals("exit")) {
                    break;
                }

                System.out.println(this.clientName + ": " + input);

                for (ServerThread thread : threads) {
                    if (thread.getClientNumber() != this.clientNumber) {
                        thread.getOut().println(this.getClientName() + " > " + input);
                        thread.getOut().flush();
                    }
                }
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
