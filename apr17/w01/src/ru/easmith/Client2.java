package ru.easmith;

import java.io.*;
import java.net.Socket;

/**
 * Created by eku on 17.04.17.
 */
public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            Thread thread = new Thread(new ClientThread(printWriter));
            thread.start();

            String message = null;
            while((message = bufferedReader.readLine()) != null) {
                System.out.println(message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ClientThread implements Runnable {

        private PrintWriter outputStream;

        public ClientThread(PrintWriter outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            String str = null;
            while(true) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(System.in));
                try {
                    str = br.readLine();
                    outputStream.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
