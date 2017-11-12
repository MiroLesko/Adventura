/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import logika.Batoh;
import logika.Hra;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;
import utils.Subject;

/**
 *
 * @author Miro
 */
public class ObsahBatohu extends ListView implements Observer {
    ObservableList<ImageView> viditelnyBatoh;
    IHra hra;
    
    

    public ObsahBatohu(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().getBatoh().registerObserver(this);
        addList();
        
       
    }
    
    private void addList(){
    viditelnyBatoh = FXCollections.observableArrayList();
    
    this.setItems(viditelnyBatoh);
    this.setPrefWidth(170);

    
    }
    
    
    
    
    @Override
    public void update() {
      
      viditelnyBatoh.clear();
      for(Vec vecVBatohu : hra.getHerniPlan().getBatoh().getSetVeci()){
        viditelnyBatoh.add(new ImageView(new Image(Main.class.getResourceAsStream(vecVBatohu.getZdroj()),150,150,false,true)));   
      }
      
    }
    
}
