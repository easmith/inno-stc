package ru.easmith;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = null;

            boolean listeningSocket = true;
            try {
                serverSocket = new ServerSocket(5555);
            } catch (IOException e) {
                System.err.println("Не могу открыть порт: 5555");
            }

            int clientNumber = 0;

            while (listeningSocket) {
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket, clientNumber++);
                serverThread.start();
            }
            serverSocket.close();

//            ServerSocket serverSocket = new ServerSocket(5555);
//            Socket socket = serverSocket.accept();
//
//
//
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//
//            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//            String message = null;
//            while((message = bufferedReader.readLine()) != null) {
//                System.out.println("Get message: " + message);
//                dataOutputStream.writeBytes("Pong to " + message);
//            }
//            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
