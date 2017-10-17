/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author Miro
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
        BorderPane borderPane = new BorderPane();  

        //ctl+space - doplnanie

        TextArea centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        
        borderPane.setCenter(centralText);
        
        Label zadejPrikazLabel = new Label("Zadaj príkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
       
        TextField zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String vstupniPrikaz = zadejPrikazTextArea.getText();
               String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
               
               centralText.appendText("\n" + vstupniPrikaz + "\n");
               centralText.appendText("\n" + odpovedHry + "\n");
               
               zadejPrikazTextArea.setText("");
               
               if (hra.konecHry()){
                   zadejPrikazTextArea.setEditable(false);
                   centralText.appendText(hra.vratEpilog());
            
            }
        }};
        
             
        
        
        FlowPane dolnaLista = new FlowPane();
        dolnaLista.setAlignment(Pos.CENTER);
        dolnaLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        borderPane.setBottom(dolnaLista);
        
        
        Scene scene = new Scene(borderPane, 500, 350);

        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else {
            if (args[0].equals("-txt")){
            IHra hra = new Hra();
            TextoveRozhrani textHra = new TextoveRozhrani(hra);
            textHra.hraj();
            }
            else{
            System.out.println("Neplatné!!");
            System.exit(1);
            }
        }
    }

}
