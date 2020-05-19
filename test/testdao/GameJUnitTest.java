/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.GameDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class GameJUnitTest extends AbstractJUnit {

    private String help;

    public GameJUnitTest() {
        super(new GameDAO());
    }

    @Before
    public void setUp() {
        help = "HELPUPDATE "+stringUret();
    }

    @Test
    @Override
    public void insertTest() {
        //game tablosuna insert işlemi gerçekleştirmiyoruz.
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        //Game tablosu update edildi ve bu update edilen yazıyı veritabanından çektik.
        //ikisi de aynı diyemi kontrol ettik.
        //Böylelikle aynı anda hem update hem de arama işlemini kontrol etmiş olduk.
        getDao().update(help);
        Assert.assertEquals(help, getDao().findAll());
    }

    @After
    public void tearDown() {
        setDao(null);
        setRandom(null);
        help = null;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
