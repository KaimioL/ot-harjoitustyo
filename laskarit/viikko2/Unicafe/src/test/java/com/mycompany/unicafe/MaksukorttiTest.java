package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenToimii(){
        kortti.lataaRahaa(5);
        
        assertEquals("saldo: 0.15", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimii(){
        kortti.otaRahaa(5);
        
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void kortinArvoEiMeneOttaessaNegatiiviseksi(){
        kortti.otaRahaa(15);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaBoolean(){
        assertTrue(kortti.otaRahaa(5));
        assertTrue(!kortti.otaRahaa(10));
    }
    
    @Test
    public void saldoToimii(){
        assertEquals(10, kortti.saldo());
    }
}
