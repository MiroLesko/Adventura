/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Trieda PrikazBatoh predstavuje implementáciu príkazu 'batoh' 
 * v hre. Táto trieda je súčasťou jednoduchej textovej hry.
 *
 * @author    Miroslav Leško
 * @version   1.00.000
 */
public class PrikazBatoh implements IPrikaz
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    private static final String NAZEV = "batoh";
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
     *  @param hPlan vytvára herný plán, v ktorom sa vráti obsah batohu
     */
    public PrikazBatoh(HerniPlan hPlan)
    {
        this.hPlan = hPlan ;
    }

     /**
     *  Vykonávanie príkazu 'batoh'. Za jeho pomoci sa obsah batohu.
     *  @param parametry názov batohu (batoh)
     *  @return v prípade existencie batohu jeho obsah 
     */
    public String proved(String... parametry){
        // overenie, či batoh existuje
        if(hPlan.getBatoh() == null){
        return "neexistujem";
        }
        
        // vrátenie vecí
        return hPlan.getBatoh().vratVeciVBatohu();
    
    }

     /**
     * Metóda vracia názov príkazu.
     * @return názov príkazu
     */
    public String getNazev() {
        return NAZEV;
    }


    //##########################################################################
    //== NESTED DATA TYPES =====================================================
}
