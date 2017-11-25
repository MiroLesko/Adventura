package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha, Miroslav Leško
 * @version    ZS 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }
        
        // incializácia vloženého slova
        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        // overenie, či susedný priestor existuje, respektíve je susedný k aktuálnemu
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        
        // nachádzanie uzamknutých priestorov, hráč nie je vpustený do vnútra
        if(sousedniProstor.getZamknutost() == true) {
           if(plan.getAktualniProstor().getNazev().equals("schodisko_1NP")){
            return "Vchod do odletovej haly je zabezpečený bezpečnostnými dverami." + '\n' +
                    "Nájdi a použi niečo, čím by sa dali otvoriť.";
            
            }
        
           if(plan.getAktualniProstor().getNazev().equals("check_in")){
            return "Chlapíkovi na check-ine sa zdáte podozrivý."+ '\n' +
                    "Skúste ho niečim podplatiť.";
            
            }
        
        }
        
        
        // uzamknutie priestoru, ak sa doň hráč snaží prejsť s nepovolenou vecou 
        if(plan.getAktualniProstor().getNazev().equals("detektor_kovov") && sousedniProstor.getNazev().equals("čakacia_miestnosť") 
                                    && plan.getBatoh().getVec("páčidlo") != null){
        sousedniProstor.setZamknutost(true);
        return "Skontrolujte si batoh! S nebezpečnými predmetmi"+ '\n' +
                "cez detektor neprejdete.";
        
        }
        
      
         // zmena aktuálneho priestoru 
         plan.setAktualniProstor(sousedniProstor);
         return sousedniProstor.dlouhyPopis();
    
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    
}
