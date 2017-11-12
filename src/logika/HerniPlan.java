package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Miroslav Leško
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{

    private static final String CILOVY_PROSTOR = "let_smer_Miami";
    private static final String KONEC_HRY_1 = "záhadná_miestnosť";
    private static final String KONEC_HRY_2 = "parkovisko";
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    
    
    private Prostor aktualniProstor;

    private Batoh batoh;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     *  
     */
    public HerniPlan() {
        zalozProstoryHry();

        this.batoh = new Batoh();
    }
    
    /**
     *  Vytvára jednotlivé priestory a prepojuje ich pomocou východov.
     *  Špecifikované sú priestory, ktoré sú uzamknuté.
     *  Ako počiatočný priestor nastaví zadržiavaciu celu.
     *  Metódou sa vytvárajú konkrétne veci a priradzujú sa im priestory, 
     *  v ktorých sa náchadzajú.
     *  
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor zadrziavaciaCela = new Prostor("zadržiavacia_cela","zadržiavacia cela pre dočasné umiestnenie osôb" + '\n' +
                                                                      "podorzrivých z trestnej činnosti, prípadne inej"+ '\n' +
                                                                            "činnosti, ktorá narúša, prípadne môže narušit chod letiska",135,165);
        Prostor chodba = new Prostor("chodba", "chodba s úplne obyčajným vzhľadom",163,170);
        Prostor sklad = new Prostor("sklad","sklad, ktorý slúži pre uskladnenie vecí" + '\n' + "pre údržbu letiska",195,177);
        Prostor kopirovaciaMiestnost = new Prostor("kopírovacia_miestnosť","kopírovacia_miestnosť s kancelárskymi potrebami" + '\n' + "a kopírkou",85,215);
        Prostor zahadnaMiestnost = new Prostor(KONEC_HRY_1,"záhadná miestnosť. WHOOPS! Tak toto sa nemalo stať!" + '\n' +  
                                                                                    "Je to WC na ktorom sedí policajt! Tvoj pokus o útek sa nepodaril.",45,220);
        Prostor schodisko = new Prostor("schodisko_0","schodisko prízemie, môžeš postupovať do miestností na tomto podlaží",170,205);
        Prostor schodisko1 = new Prostor("schodisko_1PP","prvé podzemné podlažie, môžeš postupovať do miestností na tomto podlaží",165,308);
        Prostor schodisko2 = new Prostor("schodisko_1NP","prvé nadzemné podlažie, môžeš postupovať do miestností na tomto podlaží." + '\n' +
                                                                "Východ zo schodiska je opatrený bezpečnostnými dverami." +'\n' +
                                                                "Nájdi niečo, čím by sa dali otvoriť.",200,100);
        Prostor skladBatozin = new Prostor("sklad_batožín","sklad batožín, kde sa triedi batožina pre rozličné lety" + '\n' + 
                                                            "a zhromažďujú stratené kufre.",230,270);
        Prostor odletovaHala = new Prostor("odletová_hala","odletová hala, hlavný priestor letiska s občianskou vybavenosťou",230,50);
        Prostor parkovisko = new Prostor(KONEC_HRY_2,"parkovisko. WHOOPS! Tak toto sa nemalo stať!" + '\n' + 
                                                                      "Parkovisko je plné policajtov, ktorí ťa hľadajú."+ '\n' + 
                                                                       "Tvoj pokus o útek sa nepodaril.",50,50);
        Prostor checkIn = new Prostor("check_in","check-in, miesto kontroly náležitostí pre povolenie k letu."+ '\n' + 
                                                       "Máš naozaj všetko, čo potrebuješ?",330,60);
                                                        
        Prostor potraviny = new Prostor("potraviny","letištné potraviny, malý výber a nehorázne ceny...",110,95); 
        Prostor detektorKovov = new Prostor("detektor_kovov","detektor kovov, zariadenie na kontrolu nebezpečných predmetov"+ '\n' +
                                                                "Kovové predmety sú problém!",383,57);
        Prostor cakaciaMiestnost = new Prostor("čakacia_miestnosť","čakacia miestnosť, ktorá poskytuje cestujúcim komfort"+ '\n' +
                                                                    "pri čakaní na svoj let",400,135);
        Prostor gate1 = new Prostor("gate_1","gate 1, odletová brána pre vnútroštátne linky, ale ty letíš do USA",337,170);
        Prostor gate2 = new Prostor("gate_2","gate 2, odletová brána pre súkromné lety" + '\n' + "Uplatený muchacho ťa víta širokým úsmevom",370,170);
        Prostor gate3 = new Prostor("gate_3","gate 3, odletová brána pre medzinárodné lety, ale tu by si drogy sotva prepašoval",403,170);
        Prostor letSmerMiami = new Prostor(CILOVY_PROSTOR,"let_smer_Miami. Dobrá práca! Prekonal si nástrahy letiska"+ '\n' +
                                                                "a letíš za svojou budúcnosťou. Či to bol správny krok ukáže čas",357,210);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        zadrziavaciaCela.setVychod(chodba);
        chodba.setVychod(zadrziavaciaCela);
        chodba.setVychod(sklad);
        chodba.setVychod(schodisko);
        chodba.setVychod(kopirovaciaMiestnost);
        sklad.setVychod(chodba);
        kopirovaciaMiestnost.setVychod(chodba);
        kopirovaciaMiestnost.setVychod(zahadnaMiestnost);
        schodisko.setVychod(chodba);
        schodisko.setVychod(schodisko1);
        schodisko.setVychod(schodisko2);
        schodisko1.setVychod(schodisko);
        schodisko1.setVychod(skladBatozin);
        skladBatozin.setVychod(schodisko1);
        schodisko2.setVychod(schodisko);
        schodisko2.setVychod(odletovaHala);
        odletovaHala.setVychod(schodisko2);
        odletovaHala.setVychod(parkovisko);
        odletovaHala.setVychod(checkIn);
        odletovaHala.setVychod(potraviny);
        potraviny.setVychod(odletovaHala);
        checkIn.setVychod(odletovaHala);
        checkIn.setVychod(detektorKovov);
        detektorKovov.setVychod(checkIn);
        detektorKovov.setVychod(cakaciaMiestnost);
        cakaciaMiestnost.setVychod(detektorKovov);
        cakaciaMiestnost.setVychod(gate1);
        cakaciaMiestnost.setVychod(gate2);
        cakaciaMiestnost.setVychod(gate3);
        gate1.setVychod(cakaciaMiestnost);
        gate2.setVychod(letSmerMiami);
        gate2.setVychod(cakaciaMiestnost);
        gate3.setVychod(cakaciaMiestnost);
        
        
        // vytvoříme několik věcí
        Vec letenka = new Vec("letenka", "Tvoja letenka do Miami", true, "/zdroje/letenka.png");
        Vec pas = new Vec("pas", "Tvoj pas, úradný doklad umožnujúci vycestovanie", true, "/zdroje/pas.png");
        Vec pacidlo = new Vec("páčidlo", "Kovový nástroj vhodný na násilné vniknutie" + '\n' + "do uzatvorených priestorov", true, "/zdroje/pacidlo.png");
        Vec lano = new Vec("lano", "Horolezecké lano, primárne určené na zaisťovanie batožín", true, "/zdroje/lano.jpg");
        Vec hydraulickeKlieste = new Vec("hydraulické_kliešte", "Masívne kliešte na strihanie hrubých plechov"+ '\n' +
                                                                    "a materiálov", false, "/zdroje/klieste.jpg");
        Vec hasiaciPristroj = new Vec("hasiaci_prístroj", "Práškový hasiaci prístroj slúžiaci na hasenie požiarov", true, "/zdroje/hasiaci.jpg");
        Vec mobil = new Vec("mobil", "Plne funkčný zabudnutý mobil", true, "/zdroje/mobil.png");
        Vec jackDaniels = new Vec("jack_daniels", "Flaša kvalitnej, dobre známej whiskey", true, "/zdroje/jack.jpg");
        Vec kubanskeDutniky = new Vec("dutníky", "Balenie pravých kubánských dutníkov", true, "/zdroje/dutniky.jpg");
        Vec rozkyt = new Vec("r&t", "Sada pre labužníkov - rožky a treska", true, "/zdroje/treska.png");
        
        
        // umístíme věci do prostorů
        zadrziavaciaCela.vlozVec(letenka);
        kopirovaciaMiestnost.vlozVec(pas);
        sklad.vlozVec(pacidlo);
        sklad.vlozVec(lano);
        sklad.vlozVec(hydraulickeKlieste);
        skladBatozin.vlozVec(mobil);
        schodisko.vlozVec(hasiaciPristroj);
        potraviny.vlozVec(jackDaniels);
        potraviny.vlozVec(kubanskeDutniky);
        potraviny.vlozVec(rozkyt);
       
        
        // určenie zamknutých priestorov
        odletovaHala.setZamknutost(true);
        detektorKovov.setZamknutost(true);
       
        
        // hra začíná v zadržiavacej cele
        aktualniProstor = zadrziavaciaCela; 
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }

    /**
    *   Metóda vracia hodnotu true, ak sa hráč dostane do cieľového priestoru.
    *        
    */
    public boolean hracVyhral() {
        if (aktualniProstor.getNazev().equals(CILOVY_PROSTOR)) {
            return true;
        }
        
        return false;
    }
    
    /**
    *   Metóda vracia hodnotu true, ak sa hráč dostane do priestoru, v ktorom hra končí
    *   a hráč prehráva.
    */
    public boolean hracPrehral(){
        if (aktualniProstor.getNazev().equals(KONEC_HRY_1) || aktualniProstor.getNazev().equals(KONEC_HRY_2) ){
            return true;
    
        }   
        return false;
    }
    
    /**
    *   Metóda vracia odkaz na batoh, v ktorom sú uložené zozbierané veci.
    *   @return vytvorený batoh s vecami
    */
    public Batoh getBatoh(){
        return this.batoh;
    
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
         listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
         for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
}
