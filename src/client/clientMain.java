package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientMain implements Runnable{
    @Override
    public void run() {
        clientFace.main(null);


    }
    private static Socket fromserver = null;
    private static BufferedReader in;
    private static PrintWriter out;

    static boolean connect(){
        try {
            fromserver = new Socket("localhost",4444);
        } catch (IOException e) {
            System.out.println("no Client!");
            return false;
        }
        initIO();
        return true;
    }

    private static void initIO() {
        try {
            in  = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
        } catch (IOException e) {
            System.out.println("initIO in");
        }
        try {
            out = new PrintWriter(fromserver.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("initIO out");
        }
    }

    static void send(String text){
        System.out.println("clientSend 1");
        out.println(text);
        System.out.println("clientSend 2");

        try {
            accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void accept() throws IOException {
        System.out.println("clientAccept 1");
        String s;
        s = in.readLine();
        if (s != null) {
            System.out.println("s = " + s);
            clientFace face = new clientFace();
            face.lsetText(s);
        }
        System.out.println("clientAccept 2");
    }
}
