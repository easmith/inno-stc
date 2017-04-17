package ru.easmith;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eku on 17.04.17.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);

            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));


            bufferedWriter.write("ping1");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            String message = null;
            while((message = bufferedReader.readLine()) != null) {
                System.out.println(message);
                bufferedWriter.write("ping1");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
