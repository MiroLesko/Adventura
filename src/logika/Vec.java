/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instancie triedy Vec predstavujú veci, ktoré sú rozmiestnené v priestoroch
 * a ktoré je možné zbierať (umiestniť do batohu), použiť, preskúmať či zahodiť.
 *
 * @author    Miroslav Leško
 * @version   1.00.000
 */

public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private String zdroj;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konštruktor vytvárajúci veci na základe parametrov.
     *  @param nazev Názov vytváranej veci
     *  @param popis Popis veci, špecifikácia
     *  @param prenositelna Informácia o jej prenositelonosti (true/false)
     *  @param zdroj Zdroj grafického súboru predstavujúceho vec
     */
    public Vec(String nazev, String popis, boolean prenositelna, String zdroj)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.zdroj = zdroj;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     * Metóda vracia názov veci.
     * @return názov veci
     */
    public String getNazev() {
        return nazev;
    }
    
     /**
     * Metóda vracia popis veci.
     * @return popis veci (textový reťazec)
     */
    public String getPopis() {
        return popis;
    }
    
     /**
     * Metóda vracia informaciu o prenositeľnosti veci.
     * @return prenositelna hodnota true alebo false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    
    /**
    * Metóda vracia informáciu o obrazovom zdroji veci.
    * @return stringový odkaz zdroja
    */
    public String getZdroj() {
        return zdroj;
    }
    
    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================
      

}
