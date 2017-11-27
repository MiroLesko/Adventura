/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.ObsahBatohu;
import GUI.SousedniProstory;
import GUI.VeciVPriestore;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 * Trieda main slúži na spúšťanie Adventúry a vystavanie jej grafického
 * prostredia. Je potomkom triedy Application. 
 * 
 * @author  Miroslav Leško
 * @version 1.00.0000 — 2017-11-12
 */
public class Main extends Application {
    
    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextArea;
    private Mapa mapa;
    private MenuLista menuLista;
    private Stage stage;
    private ObsahBatohu obsahBatohu;
    private SousedniProstory sousedniProstory;
    private VeciVPriestore veciVPriestore;
    
    /**
    *  Metóda start vytvára grafické rozhranie, ktoré sa zobrazí
    *  užívateľovi a prostredníctvom ktorého bude hru ovládať.
    *  Grafické rozhranie je zobrazené na javisku. 
    *
    *  @param stage javisko, na ktorom sa zobrazí GUI 
    */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        obsahBatohu = new ObsahBatohu(this);
        sousedniProstory = new SousedniProstory(this);
        veciVPriestore = new VeciVPriestore(this);
        
        BorderPane borderPane = new BorderPane();
        
        //Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        //Label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //Dolni prikaz
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
            
            /**
            *   Metóda spracováva zadané príkazdy do TextField-u.
            * 
            *   
            */
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                
                    
                 }
                        
                        
                
            }
        });
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        
        // nastavenie zarovnania a vloženie elementov do dolnej lišty
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
       
        //vytvorenie vertikálneho boxu bočná lišta
        VBox bocnaLista = new VBox();
        
        //vytvorenie označenia pre zoznam susedných priestorov a nastavenie typu a veľkosti písma
        Label nazovSusednePriestory = new Label("Susedné priestory:");
        nazovSusednePriestory.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        Label nazovVeciVPriestore = new Label("Predmety v priestore:");
        nazovVeciVPriestore.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        VBox susednePriestoryVBox = new VBox();
        susednePriestoryVBox.getChildren().addAll(nazovSusednePriestory,sousedniProstory);
        
        VBox veciVPriestoreVBox = new VBox();
        veciVPriestoreVBox.getChildren().addAll(nazovVeciVPriestore,veciVPriestore);
        
        HBox priestoryAVeciHBox = new HBox();
        priestoryAVeciHBox.getChildren().addAll(susednePriestoryVBox, veciVPriestoreVBox);

        
        
        //nastavenie riadkovania a pridanie elementov do bočnej lišty
        bocnaLista.setSpacing(5);
        bocnaLista.getChildren().addAll(mapa, priestoryAVeciHBox);
        
        //pridanie elementov do jednotlivých častí border pane
        borderPane.setLeft(bocnaLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        borderPane.setRight(obsahBatohu);
        
        
        //vytvorenie scény, jeho priradenie do javiska a následné zobrazenie
        Scene scene = new Scene(borderPane, 1100, 650);

        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    /**
    *   Metóda vracia vytvorenú instanciu mapy
    *   @return mapa mapa, vytvorená pre GUI
    */
    public Mapa getMapa() {
        return mapa;
    }

    
    /**
    *   Metóda vracia vytvorenú instanciu viditeľného obsahu batoha
    *   @return obsahBatohu viditeľných obsah batoha
    */
    public ObsahBatohu getObsahBatohu() {
        return obsahBatohu;
    }

    
    /**
    *   Metóda vracia vytvorenú instanciu viditeľného listu susedných priestorov
    *   @return sousedniProstory viditeľných zoznam susedných priestorov
    */
    public SousedniProstory getSousedniProstory() {
        return sousedniProstory;
    }
   
    
    /**
    *   Metóda vracia vytvorenú instanciu textovej oblasti, v ktorej sa zobrazujú
    *   pokyny hry.
    *   @return centralText oblast pokynov hry
    */
    public TextArea getCentralText() {
        return centralText;
    }

    
    /**
    *   Metóda nastavuje hru.
    *   @param hra instancia rozhrania IHra, ktoré hra implementuje
    */
    public void setHra(IHra hra) {
        this.hra = hra;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    
    /**
    *   Metóda vráti javisko, na ktorom je hra vystavaná
    *   @return stage javisko s GUI hry
    */
    public Stage getStage() {
        return stage;
    }
    
    /**
    *   Metóda vráti rozhranie, ktoré implementuje hra.
    *   @return hra
    */
    
    public IHra getHra() {
        return hra;
    
    }

    /**
    *   Metóda vráti zoznam vecí, ktoré sa nachádzajú v priestore.
    *   @return veciVPriestore zoznam vecí v aktuálnom priestore
    */
    
    public VeciVPriestore getVeciVPriestore() {
        return veciVPriestore;
    }

    

}
