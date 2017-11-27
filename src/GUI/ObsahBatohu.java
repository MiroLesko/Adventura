/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logika.Batoh;
import logika.Hra;
import logika.IHra;
import logika.PrikazZahod;
import logika.Vec;
import main.Main;
import utils.Observer;


/** 
 * Trieda ObsahBatohu ako potomok triedy ListView vytvára
 * špecifickú instanciu, ktorá zobrazuje obrazový zoznam vecí umiestnených 
 * v batohu. Zároveň implementuje rozhranie Observer
 * pomocou ktorého reaguje na pridanie a odobranie veci z batoha.
 *
 * @author Miroslav Leško
 * @version 1.00.0000 — 2017-11-12
 */
public class ObsahBatohu extends ListView implements Observer {
    ObservableList<Button> viditelnyBatoh;
   // IHra hra;
    Main main;
    

    public ObsahBatohu(Main main) {
        this.main = main;
        main.getHra().getHerniPlan().getBatoh().registerObserver(this);
        addList();
        
       
    }
    
    
    /**
    * Metóda vytvára viditeľných batoh a nastavuje jeho 
    * preferovanú širku.
    *
    */ 
    private void addList(){
        viditelnyBatoh = FXCollections.observableArrayList();
    
        this.setItems(viditelnyBatoh);
        this.setPrefWidth(170);
    
        
    }
    
    
    /**
    * Metóda odregistruje, vytvorí novú hru a opätovne zaregistruje observerov.
    *
    * @param novaHra nová Hra
    */ 
    public void newGame(IHra novaHra){
        main.getHra().getHerniPlan().getBatoh().removeObserver(this);
        main.setHra(novaHra);
        main.getHra().getHerniPlan().getBatoh().registerObserver(this);
        update();
    }
    
    
    
    /**
    * Metóda vykonáva aktualizáciu viditeľného zoznamu priestorov po notifikácií
    * (upozornení na zmenu) pozorovaným subjektom.
    *
    */ 
    @Override
    public void update() {
      //premazanie zobrazeného viditeľného batohu
      viditelnyBatoh.clear();
      
      //prechádzanie batohu a vkladanie tlačidiel s podobou vecí z batohu
      for(Vec vecVBatohu : main.getHra().getHerniPlan().getBatoh().getSetVeci()){
        ImageView viditelnaVecVBatohu = new ImageView(new Image(Main.class.getResourceAsStream(vecVBatohu.getZdroj()),150,150,false,true));
        
        Button button = new Button();
        button.setGraphic(viditelnaVecVBatohu);
        viditelnyBatoh.add(button);
        
        //nastavenie reakcie na kliknutie - zahodenie veci a aktualizácia batoha
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               PrikazZahod prikazZahod = new PrikazZahod(main.getHra().getHerniPlan());
               main.getCentralText().appendText("\n" + prikazZahod.proved(vecVBatohu.getNazev())+ "\n");
               main.getVeciVPriestore().update();
            }
        }
        );
        
        
        
      }
      
    }
    
}
