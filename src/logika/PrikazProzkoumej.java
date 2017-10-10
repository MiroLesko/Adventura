/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Trieda PrikazProzkoumej predstavuje implementáciu príkazu 'prozkoumej' 
 * v hre. Táto trieda je súčasťou jednoduchej textovej hry.
 *
 * @author    Miroslav Leško
 * @version   1.00.000
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konštruktor triedy
     *  @param hPlan vytvára herný plán, v ktorom sa budú veci skúmať
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Vykonávanie príkazu 'prozkoumej'. Za jeho pomoci sa zobrazí popis 
     *  preskúmavanej veci.
     *  @param parametry názov veci, ktorú chceme preskúmať
     *  @return správa, ktorá sa vypíše hráčovi
     */
    public String proved(String... parametry) {
        // prípad, keď chýba druhé vložené slovo (konkrétna vec)
        if (parametry.length < 1) {
            return "nevím, co mám prozkoumat";
        }
        
        // incializácia vloženého slova
        String nazevVeci = parametry[0];
        
        // nájdenie preskúmavanej veci jej odobratím z priestoru
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        
        // ak sa vec v priestore nenachádza
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        // vrátenie veci do priestoru
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
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
