package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class clientFace extends Application {
    @FXML
    Button btnConn;
    @FXML
    TextArea chatArea;
    @FXML
    TextField msgFld;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("clientSample.fxml"));
        } catch (IOException e) {
            System.out.println("error: FXMLLoader");
        }
        Scene scene = new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("client");
        primaryStage.show();
    }


    public void btnConn(ActionEvent actionEvent) {
        if (clientMain.connect()){
            btnConn.setText("Connected");
        }
        timer();
    }

    public void btnSend(ActionEvent actionEvent) {
        //chatArea.setText(chatArea.getText() + '\n' + msgFld.getText());
        clientMain.send(msgFld.getText());
        System.out.println("client send");
    }

    private static String text;
    static void lsetText(String t){
        text = t;
    }

    private void initChat(){
        if (text != null)
        chatArea.setText(chatArea.getText() + '\n' + text);
        text = null;
    }


    private void timer() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                initChat();
//                System.out.println("dsds");
            }
        });
        timer.start();
    }

}
