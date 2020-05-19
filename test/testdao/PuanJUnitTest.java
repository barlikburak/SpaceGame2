/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.PuanDAO;
import entity.Oyuncu;
import entity.Puan;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class PuanJUnitTest extends OyuncuJUnitTest {

    private Puan puan;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        puan = new Puan();
        puan.setPuan(37);//25'in üzerinde puan eklediğimiz için oyuncu ile uzay gemisinin ilişkili tabloya da veri ekleniyor.
        puan.setOyuncu(((ArrayList<Oyuncu>)getDao().findAll()).get(0));
        setDao(new PuanDAO());
    }

    @Test
    @Override
    public void insertTest() {
        //puan tablosuna veri eklendi.
        Assert.assertNotNull(getDao().insert(puan));
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        //update işlemi yapılmadığı için sadece arama işlemini kontrol ettik.
        Assert.assertNotEquals(0, ((ArrayList<Puan>)(getDao().findAll())).size());
        Assert.assertNotEquals(0, ((ArrayList<Puan>)(getDao().findById(puan.getOyuncu().getOyuncu_id()))).size());
    }

    @After
    @Override
    public void tearDown() {
        super.tearDown();
        puan = null;
    }

    public Puan getPuan() {
        return puan;
    }

    public void setPuan(Puan puan) {
        this.puan = puan;
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
