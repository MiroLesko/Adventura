/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Trieda PrikazZahod predstavuje implementáciu príkazu 'zahod' 
 * v hre. Táto trieda je súčasťou jednoduchej textovej hry. 
 *
 * @author  Miroslav Leško
 * @version 1.00.0000 — 2016-12-26
 */
public class PrikazZahod implements IPrikaz
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    public static final String NAZOV = "zahod";
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
     *  @param hPlan vytvára herný plán, v ktorom sa budú veci zahadzovať
     */
    public PrikazZahod(HerniPlan hPlan)
    {
    this.hPlan = hPlan;
    }



    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    //== OTHER NON-PRIVATE INSTANCE METHODS ====================================
    //== PRIVATE AND AUXILIARY INSTANCE METHODS ================================
     /**
     *  Vykonávanie príkazu 'zahod'. Za jeho pomoci zadaná vec zahodí.
     *  @param parametry názov veci, ktorú chceme zahodiť
     *  @return správa, ktorá sa vypíše hráčovi
     */
    public String proved(String... parametry){
        // prípad, keď chýba druhé vložené slovo (konkrétna vec)
        if (parametry.length < 1) {
            return "neviem, čo mám zahodiť";
        }
        // incializácia vloženého slova
        String nazevVeci = parametry[0];
        
        // získanie instancie veci, ktorej názov sme zadali
        Vec vec = hPlan.getBatoh().getVec(nazevVeci);
         
        // vylúčenie prípadu, že vec nie je v batohu
        if (vec == null) {
            return "vec '" + nazevVeci + "' nemáš v batohu";
        }
        
        
        // odstránenie veci z batohu a jej vloženie do aktuálneho priestoru
        hPlan.getBatoh().zahodVec(vec);
        hPlan.getAktualniProstor().vlozVec(vec);
        
        
        return "zahodil si vec " + nazevVeci + ",už nie je v batohu";
    }
 
     /**
     * Metóda vracia názov príkazu.
     * @return názov príkazu
     */
    public String getNazev(){
        return NAZOV;
    }


    //##########################################################################
    //== NESTED DATA TYPES =====================================================
}
