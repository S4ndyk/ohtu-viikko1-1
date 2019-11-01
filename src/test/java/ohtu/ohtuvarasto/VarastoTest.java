package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoSaldolla = new Varasto(10, 5);

    }

    @Test
    public void merkkijonoMuotoOnOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusPalauttaaNolla() {
        Varasto virheellinen = new Varasto(-1);
        assertEquals(0, virheellinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuuta() {
        double ennen = varasto.getSaldo();
        varasto.lisaaVarastoon(-5);
        assertEquals(ennen, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiMuuta() {
        double ennen = varasto.getSaldo();
        varasto.otaVarastosta(-5);
        assertEquals(ennen, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliKaikenOttaminenNollaa() {
        varasto.otaVarastosta(10000);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test 
    public void yliRajojenLisaaminenMaksimoi() {
        varasto.lisaaVarastoon(10000);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoisellaOikeaSaldo() {
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkuSaldoisellaOikeaTilavuus() {
        assertEquals(10, varastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoNollaa() {
        Varasto v = new Varasto(10, -5);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusNollaa() {
        Varasto v = new Varasto(-5, 5);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoSuurempiKuinTilavuusMaksimoi() {
        Varasto v = new Varasto(10, 500);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    
}