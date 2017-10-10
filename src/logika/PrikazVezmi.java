/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Trieda PrikazVezmi predstavuje implementáciu príkazu 'vezmi' 
 * v hre. Táto trieda je súčasťou jednoduchej textovej hry.
 *
 * @author    Miroslav Leško
 * @version   1.00.000
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;
    
    //== Konstruktory a tovární metody =============================================

       /***************************************************************************
     *  Konštruktor triedy
     *  @param hPlan vytvára herný plán, v ktorom sa budú veci zbierať
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
        /**
     *  Vykonávanie príkazu 'vezmi'. Za jeho pomoci hráč vyzdvihne vec 
     *  z priestoru a vloží ju do batohu.
     *  @param parametry názov veci, ktorú chceme vziať
     *  @return správa, ktorá sa vypíše hráčovi
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        // incializácia vloženého slova
        String nazevVeci = parametry[0];
        
        // nájdenie preskúmavanej veci jej odobratím z priestoru
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        
        // ak sa vec v priestore nenachádza
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        // vec neprenositeľná je vložená naspäť do priestoru
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
       
       // prípad dosiahnutia maximálnej kapacity batohu, vec je vložená 
       // späť do priestoru
       if (herniPlan.getBatoh().jePlny()) {
               herniPlan.getAktualniProstor().vlozVec(vec);
               return "batoh je plný";
           }
       
       
       // vloženie veci do batohu v prípade jeho existencie    
       if(herniPlan.getBatoh() != null){
           herniPlan.getBatoh().pridaj(vec);
           
         
        }
        return "věc '" + nazevVeci + "' jsi uložil do batohu";
    }

     /**
     * Metóda vracia názov príkazu.
     * @return názov príkazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
