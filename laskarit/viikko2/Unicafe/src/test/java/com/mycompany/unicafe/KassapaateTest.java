/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author latep_000
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    public KassapaateTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(500);
        paate = new Kassapaate();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kassapaateLuontiToimii() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenOstoToimiiJosMaksuRiittaa(){
        assertEquals(10, paate.syoEdullisesti(250));
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenOstoToimiiJosMaksuEiRiita(){
        assertEquals(2, paate.syoEdullisesti(2));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenOstoToimiiJosMaksuRiittaa(){
        assertEquals(10, paate.syoMaukkaasti(410));
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenOstoToimiiJosMaksuEiRiita(){
        assertEquals(2, paate.syoMaukkaasti(2));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenOstoKortiltaToimiiJosKortinSaldoRiittaa(){
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenOstoKortiltaToimiiJosKortinSaldoEiRiita(){
        assertTrue(!paate.syoEdullisesti(new Maksukortti(1)));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenOstoKortiltaToimiiJosKortinSaldoRiittaa(){
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenOstoKortiltaToimiiJosKortinSaldoEiRiita(){
        assertTrue(!paate.syoMaukkaasti(new Maksukortti(1)));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLataaminenToimiiPositiivisella(){
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(100010, paate.kassassaRahaa());
        assertEquals(510, kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa(){
        paate.lataaRahaaKortille(kortti, -5);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(500, kortti.saldo());
    }
}
