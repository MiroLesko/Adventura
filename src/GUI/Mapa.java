/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Trieda ObsahBatohu ako potomok triedy AnchorPane vytvára
 * špecifickú instanciu, ktorá zobrazuje mapu priestorov a aktuálne umiestnenie.
 * Zároveň implementuje rozhranie Observer,
 * pomocou ktorého reaguje na pridanie a odobranie veci z batoha.
 * @author Miro
 * @version 1.00.0000 — 2017-11-12
 */
public class Mapa extends AnchorPane implements Observer {
    private IHra hra;
    private Circle tecka;
    private ImageView krizImageView;

    
    public Mapa(IHra hra){
    this.hra = hra;
    hra.getHerniPlan().registerObserver(this);
    krizImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/kriz.gif"),20,20,false,true));    
    init();
    
    }
    
    
    /**
    * Metóda inicializuje obrazovú formu mapy zo zdrojového súboru 
    * a pridáva ju spolu s ukazovateľom aktuálneho priestoru
    * do Mapy.
    */
    private void init(){
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"),500,350,false,true));
        this.getChildren().addAll(obrazekImageView, krizImageView);
        update();
    }
    
    
    
    /**
    * Metóda odregistruje, vytvorí novú hru a opätovne zaregistruje observerov.
    *
    * @param novaHra nová Hra
    */ 
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    /**
    * Metóda vykonáva aktualizáciu ukazovateľa aktuálneho priestoru v po notifikácií
    * (upozornení na zmenu) pozorovaným subjektom.
    *
    */ 
    @Override
    public void update() {
        this.setTopAnchor(krizImageView, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(krizImageView, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
