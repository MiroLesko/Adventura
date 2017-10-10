/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Trieda PrikazZahod predstavuje implementáciu príkazu 'zahod' 
 * v hre. Táto trieda je súčasťou jednoduchej textovej hry. 
 *
 * @author  Miroslav Leško
 * @version 1.00.0000 — 2016-12-27
 */
public class PrikazPouzi implements IPrikaz
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    private static final String NAZOV = "pouzi";
    private HerniPlan hPlan;
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
     *  Konštruktor triedy
     *  @param hPlan vytvára herný plán, v ktorom sa budú veci používať
     */
    public PrikazPouzi(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }



    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    //== OTHER NON-PRIVATE INSTANCE METHODS ====================================
    //== PRIVATE AND AUXILIARY INSTANCE METHODS ================================
         /**
     * Metóda vracia názov príkazu.
     * @return názov príkazu
     */
    public String getNazev(){
        return NAZOV;
    }
    
    
     /**
     *  Vykonávanie príkazu 'pouzi'. Za jeho pomoci sa zadaná vec
     *  použije. Použitím veci sa otvárajú priestory.
     *  Ak vec priestor nevie otvoriť ostane v batohu, hra pokračuje.
     *  @param parametry názov veci, ktorú chceme použiť
     *  @return správa, ktorá sa vypíše hráčovi
     */
    public String proved(String... parametry){
   
        ///Vylúčenie možnosti, že príkaz nenasleduje konkrétna vec   
        if(parametry.length < 1){
            return "neviem, čo mám použiť";
        }
    
        // incializácia vloženého slova
        String nazovVeci = parametry[0];
    
        // nájdenie veci v batohu
        Vec vec = hPlan.getBatoh().getVec(nazovVeci);
    
        // vylúčenie prípadu, že vec nie je v batohu 
        if(vec == null){
            return "Vec '"+ nazovVeci +"' sa nenachádza v batohu.";
        }
    
    
        // otvorenie východu zo schodiska do odletovej haly, vec ostáva po použití v batohu
        if(nazovVeci.equals("páčidlo") && hPlan.getAktualniProstor().getNazev().equals("schodisko_1NP")){
            hPlan.getAktualniProstor().vratSousedniProstor("odletová_hala").setZamknutost(false);
            return "Podarilo sa ti otvoriť bezpečnostné dvere."+ '\n' +
            "Cesta je voľná :)";
        }
    
        // podplatenie pracovníka check-inu, otvorenie prechodu na detektor, vec po použití odstráná
        // z batohu
        if(nazovVeci.equals("r&t") && hPlan.getAktualniProstor().getNazev().equals("check_in")){
            hPlan.getAktualniProstor().vratSousedniProstor("detektor_kovov").setZamknutost(false);
            hPlan.getBatoh().zahodVec(hPlan.getBatoh().getVec("r&t"));
            return "Chlapík je gurmán! Rožky a treska sú istota."+ '\n' +
               "Môžeš pokračovať v úteku.";
            }   
    
    
    
        // vo všetkých ostatných prípadoch je použitie vecí zbytočné
        return "Toto tu používaš zbytočne...";
    }

    //##########################################################################
    //== NESTED DATA TYPES =====================================================
}
