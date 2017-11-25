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
import logika.IHra;
import logika.Prostor;
import logika.PrikazJdi;
import main.Main;
import utils.Observer;

/** 
 * Trieda SousedniProstory ako potomok triedy ListView vytvára
 * špecifickú instanciu, ktorá zobrazuje zoznam susedných priestorov 
 * aktuálneho priestoru. Zároveň implementuje rozhranie Observer
 * pomocou ktorého reaguje na zmenu priestoru.
 *
 * @author Miroslav Leško
 * @version 1.00.0000 — 2017-11-12
 */
public class SousedniProstory extends ListView implements Observer {
    ObservableList<Button> viditelnyZoznamPriestorov;
   // IHra hra;
    Main main;

    public SousedniProstory(Main main) {
        this.main = main;
        main.getHra().getHerniPlan().registerObserver(this);
        addList();
        
    }
    
    /**
    * Metóda vytvára viditeľných zoznam priestorov a nastavuje jeho 
    * preferovanú širku.
    *
    */ 
    public void addList(){
        viditelnyZoznamPriestorov = FXCollections.observableArrayList();  
        this.setItems(viditelnyZoznamPriestorov);
        this.setPrefWidth(170);
        this.update();
    }
    
    
    /**
    * Metóda odregistruje, vytvorí novú hru a opätovne zaregistruje observerov.
    *
    * @param novaHra nová Hra
    */
    public void newGame(IHra novaHra){
        main.getHra().getHerniPlan().removeObserver(this);
        main.setHra(novaHra);
        main.getHra().getHerniPlan().registerObserver(this);
        update();
    }
    
    
    /**
    * Metóda vykonáva aktualizáciu viditeľného zoznamu priestorov po notifikácií
    * (upozornení na zmenu) pozorovaným subjektom. 
    *
    */ 
    @Override
    public void update() {
       
        viditelnyZoznamPriestorov.clear();
        
        for(Prostor susednyPriestor : main.getHra().getHerniPlan().getAktualniProstor().getVychody()){
            Button button = new Button();
            viditelnyZoznamPriestorov.add(button);
            button.setText(susednyPriestor.getNazev());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    PrikazJdi prikazJdi = new PrikazJdi(main.getHra().getHerniPlan());
                    main.getCentralText().appendText("\n" + prikazJdi.proved(susednyPriestor.getNazev())+ "\n");
                }
            });
           
       } 
    }
    
    
    
    
    
}
