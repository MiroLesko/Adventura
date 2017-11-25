/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;


/**
 *  Trieda, ktorej instancie sú potomkami triedy MenuBar.
 *  Predstavuje zobraziteľnú lištu s ponukou.
 * @author Miro
 * @version 1.00.0000 — 2017-11-12
 */
public class MenuLista extends MenuBar{
    private IHra hra;
    private Main main;
    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
        
    }
    
    
    /**
     * Metóda inicializuje jednotlivé časti lišty s menu.
     * Do lišty sú vkladané elementy Menu s vloženými
     * elementami MenuItem.
    */
    private void init(){
      //tvorba položiek Menu a MenuItem
      Menu novySoubor = new Menu("Adventura");
      Menu napoveda = new Menu("Help");
      MenuItem novaHra = new MenuItem("Nova hra");//, new ImageView(new Image(Main.class.getResourceAsStream("zdroj.png"))));
      novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
      MenuItem konecHry = new MenuItem("Konec hry");
      
      //vloženie položiek menu do Menu
      novySoubor.getItems().addAll(novaHra, konecHry);
      
      //vloženie elementov Menu do MenuLista
      this.getMenus().addAll(novySoubor, napoveda);
      
      //vytvorenie položiek menu a ich vloženie do Menu napoveda
      MenuItem oProgramu = new MenuItem("O programu");
      MenuItem napovedaItem = new MenuItem("Napoveda"); 
      napoveda.getItems().addAll(oProgramu, napovedaItem);
      
      
      /**
      * Metóda nastavujúca reakciu po kliknutí na položku menu konecHry.
      * Ukončí aktuálnu hru.
      * 
      */
      konecHry.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              System.exit(0);
          }
      });
    
      
      /**
      * Metóda nastavujúca reakciu po kliknutí na položku menu novaHra.
      * Vystavanie novejHry.
      * 
      */
      novaHra.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              hra = new Hra();
              main.getMapa().newGame(hra);
              main.getObsahBatohu().newGame(hra);
              main.getSousedniProstory().newGame(hra);
            //  main.getVeciVPriestore().newGame(hra);
              main.setHra(hra);
              main.getCentralText().setText(hra.vratUvitani());
          }
      });
      
      
      /**
       * Metóda nastavujúca reakciu po kliknutí na položku menu oProgramu.
       * Zobrazenie informácií o programe na novom javisku.
       */
      oProgramu.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);         
             oProgramuAlert.setTitle("O programu");
             oProgramuAlert.setHeaderText("Super adventura XYZ");
             oProgramuAlert.setContentText("OSGAJNGJASNGKAJSGIJKANSG");
             oProgramuAlert.initOwner(main.getStage());
          
             oProgramuAlert.showAndWait();
          
          }
     });
      
       
      /**
       * Metóda nastavujúca reakciu po kliknutí na položku menu napovedaItem..
       * Zobrazenie nápovedy k adventúre na novom javisku a scéne.
       */
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             Stage stage = new Stage();
             stage.setTitle("Napoveda");
             
             WebView webView = new WebView();
             webView.getEngine().load(Main.class.getResource("/zdroje/navod.html").toExternalForm());
             
             stage.setScene(new Scene(webView, 750,600));
             stage.show();
             
             
          }
      });
    
    }
}
