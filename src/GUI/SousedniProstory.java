/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author Miro
 */
public class SousedniProstory extends ListView implements Observer {
    ObservableList<String> viditelnyZoznamPriestorov;
    IHra hra;

    public SousedniProstory(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        addList();
    }
    
    public void addList(){
        viditelnyZoznamPriestorov = FXCollections.observableArrayList();
        viditelnyZoznamPriestorov.add("chodba");
        this.setItems(viditelnyZoznamPriestorov);
        this.setPrefWidth(170);
    
    
    }
    
   
    @Override
    public void update() {
       
        viditelnyZoznamPriestorov.clear();
        
        for(Prostor susednyPriestor : hra.getHerniPlan().getAktualniProstor().getVychody()){
           viditelnyZoznamPriestorov.add(susednyPriestor.getNazev());
           
       } 
    }
    
}
