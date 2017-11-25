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
import logika.IHra;
import logika.PrikazVezmi;
import logika.Vec;
import main.Main;
import utils.Observer;


/**
 *
 * @author Miro
 */
public class VeciVPriestore extends ListView implements Observer {
    ObservableList<Button> viditelnyZoznamVeci;
    Main main;
            
            
    public VeciVPriestore(Main main){
    this.main = main;
    main.getHra().getHerniPlan().registerObserver(this);
    main.getHra().getHerniPlan().getAktualniProstor().registerObserver(this);
    addList();
    }
    
    public void addList(){
    viditelnyZoznamVeci = FXCollections.observableArrayList();
    this.setItems(viditelnyZoznamVeci);
    this.setPrefWidth(320);
    this.update();
    
    }
    
    /**
    * Metóda odregistruje, vytvorí novú hru a opätovne zaregistruje observerov.
    *
    * @param novaHra nová Hra
    */ 
    public void newGame(IHra novaHra){
        main.getHra().getHerniPlan().removeObserver(this);
        main.getHra().getHerniPlan().getAktualniProstor().removeObserver(this);
        main.setHra(novaHra);
        main.getHra().getHerniPlan().registerObserver(this);
        main.getHra().getHerniPlan().getAktualniProstor().registerObserver(this);
        update();
    }
    
    
      
    @Override
    public void update() {
      
        viditelnyZoznamVeci.clear();
       
       for(Vec vecVPriestore : main.getHra().getHerniPlan().getAktualniProstor().getVeci().values()){
           ImageView viditelnaVecVPriestore = new ImageView(new Image(Main.class.getResourceAsStream(vecVPriestore.getZdroj()),100,100,false,true));
           
           Button button = new Button();
           button.setGraphic(viditelnaVecVPriestore);
           
           viditelnyZoznamVeci.add(button);
           
           button.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   PrikazVezmi prikazVezmi = new PrikazVezmi(main.getHra().getHerniPlan());
                   main.getCentralText().appendText("\n" + prikazVezmi.proved(vecVPriestore.getNazev())+ "\n");
                   update();
               }
           });
           
           
                   
           
       }
    }
    
}
