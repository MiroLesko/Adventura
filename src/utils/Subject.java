/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Rozhranie Subject obsahuje metódy, ktoré registrujú, odstraňujú
 * a upozorňujú pozorovateľov objektov impementujúcich toto rozhranie.
 * @author Miroslav Leško
 * @version 1.00.0000 — 2017-11-12
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
