package ru.easmith;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

            List<ServerThread> threadList = new ArrayList<>();

            while (listeningSocket) {
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket, ++clientNumber, threadList);
                serverThread.start();
                threadList.add(serverThread);
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
