/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;


import java.util.*;
import utils.Observer;
import utils.Subject;


/*******************************************************************************
 * Instancie triedy Batoh predstavujú batoh, do ktorého sú umiestňované
 * instancie triedy Vec. Tie možno do batohu vložiť, prípadne z batohu
 * odstrániť.
 *
 * @author  Miroslav Leško
 * @version 1.00.0000 — 2016-12-26
 */
public class Batoh implements Subject
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    private Set<Vec> batoh;
    private List<Observer> seznamObserveru = new ArrayList<Observer>();
    
    
    //== VARIABLE CLASS ATTRIBUTES =============================================




    //##########################################################################
    //== STATIC INITIALIZER (CLASS CONSTRUCTOR) ================================
    //== CLASS GETTERS AND SETTERS =============================================
    //== OTHER NON-PRIVATE CLASS METHODS =======================================
    //== PRIVATE AND AUXILIARY CLASS METHODS ===================================



    //##########################################################################
    //== CONSTANT INSTANCE ATTRIBUTES ==========================================
    //== VARIABLE INSTANCE ATTRIBUTES ==========================================



    //##########################################################################
    //== CONSTUCTORS AND FACTORY METHODS =======================================

    /***************************************************************************
    * Konštuktor vytvárajúci Hashset reprezentujúci batoh, do ktorého 
    * možno vkladať veci.
    */
    public Batoh()
    {
        batoh = new HashSet <> ();
        
    }



    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    /**
     * Pridanie veci do batohu, ak vec existuje. Vstupným parametrom
     * je objekt veci, v prípade úspešného pridania sa vracia hodnota true.
     *  @param vec Vec, ktorú chceme do batohu pridať
     */
    public boolean pridaj(Vec vec){
        if(vec == null){
        return false;
        }
        
        batoh.add(vec);
        notifyObservers();
        return true;
    }
    


     /**
     * Vrátenie hodnoty true, ak je dosiahnutá maximálna kapacita
     * batohu, ktorá je 4 veci.  
     *  @return true/false podľa naplnenosti batohu
     */
    public boolean jePlny(){
        if(batoh.size() < 4){
            return false;
        }
        return true;
    }
    
    
     /**
     * Vrátenie textového reťazce, ktorý predstavuje všetky veci 
     * v batohu. V prípade prázdného batohu sa vracia "batoh je prázdny".
     *  @return textový reťazec obsahujúci veci v batohu
     */
   public String vratVeciVBatohu() {
       // prípad, keď je batoh prázdny
       if(batoh.isEmpty()){
           return "batoh je prázdny";
        }
    
       // vytvorenie dočasnej premennej zoznam      
       String zoznam = "";
       
       // prechádzanie batohu a ukladanie názvov do zoznamu
       for(Vec v : batoh){
            zoznam = zoznam + v.getNazev() + " ";
    
        }
       return zoznam;
    }
    
    /**
     *  Odobranie veci z batoha. V prípade úspešného odobratia návrat
     *  hodnoty true. 
     *  @return logická hodnota reprezentujúca úspešnosť prevedenia odobratia 
     */
    public boolean zahodVec(Vec vec){
        if(vec != null){
            // prechádzanie obsahu batohu
            for (Vec v : batoh){
                // porovnanie názvu vloženej veci s vecami v batohu, v prípade zhody 
                // odstránenie veci z batohy
                if(v.getNazev().equals(vec.getNazev())){
                    batoh.remove(v);
                    notifyObservers();
                    return true;
                }
    
            }
    
        }
    
        return false; 
    }
    
    /**
     *  Vrátenie veci z batoha na základe jej názvu. V prípade, že sa vec
     *  v batohu nenachádza je vrátená hodnota null.
     *  @param nazev názov veci, ktorú potrebujeme vrátiť
     *  @return hľadaná vec
     */
    public Vec getVec(String nazev) {
        // vytvorenie dočasnej premennej, ak cykly nenájdu vec vracia sa null
        Vec hladana = null;
        
        // prechádzanie batohu
            for(Vec v : batoh){
        
            // zhoda názvov, dočasná premenná reprezentuje vec    
            if(v.getNazev().equals(nazev)){
                hladana = v;  
            }
        
        }
        
        return hladana;
       
        
    }

    public Set<Vec> getSetVeci() {
        return batoh;
    }

    
    
    
    
    
    
    
    //== OTHER NON-PRIVATE INSTANCE METHODS ====================================
    //== PRIVATE AND AUXILIARY INSTANCE METHODS ================================



    //##########################################################################
    //== NESTED DATA TYPES =====================================================


    @Override
    public void registerObserver(Observer observer) {
        seznamObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        seznamObserveru.remove(observer); 
    }

    @Override
    public void notifyObservers() {
        for(Observer observerPolozkaZoznamu : seznamObserveru){
        observerPolozkaZoznamu.update();
    }
    }

}