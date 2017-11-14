package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serverMain {//implements Runnable{

    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket fromclient;

//    @Override
//    public void run() {
//       createServ();
//       initIO();
//       while (true) {
//           try {
//               accept();
//           } catch (IOException e) {
//               System.out.println("server exec");
//           }
//       }
//    }

    public static void main(String[] args) {
        Console console = System.console();
        if(console!=null) {
            String login = console.readLine("Введите логин:");
            System.out.println(login);
        }
        createServ();
        initIO();
        while (true) {
            try {
                accept();
            } catch (IOException e) {
                System.out.println("server exec");
            }
        }
    }

    private static void createServ(){
        ServerSocket servers = null;
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }
        try {
            System.out.print("Waiting for a client...");
            fromclient= servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
    }

    private static void initIO()  {
        try {
            in = new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
        } catch (IOException e) {
            System.out.println("error IO in");
        }
        try {
            out = new PrintWriter(fromclient.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("error IO out");
        }
    }

    private static void accept() throws IOException {
        System.out.println("servAccept 1");
        String s = in.readLine();
        System.out.println(s);
        System.out.println("servAccept 2");
        send(s);

    }
    private static void send(String text){
        System.out.println("ServSend 1");
        out.println(text);
        System.out.println("ServSend 2");
    }

}
