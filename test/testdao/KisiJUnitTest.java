/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.KisiDAO;
import entity.Kisi;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class KisiJUnitTest extends LoginJUnitTest {

    private Kisi kisi;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        setDao(new KisiDAO());
        kisi = new Kisi();
        kisi.setAd("isimTest");
        kisi.setSoyad("soyadTest");
        kisi.setE_posta(stringUret() + "@gmail.com");
        kisi.setLogin(getLogin());
    }

    @Test
    @Override
    public void insertTest() {
        //kisi tablosuna veri eklendi.
        Assert.assertNotNull(getDao().insert(kisi));
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        ArrayList<Kisi> tempList = (ArrayList<Kisi>) getDao().findAll();
        Assert.assertNotNull(tempList);//findAll metodunu kontrol ettik.
        Kisi temp = tempList.get(tempList.size() - 1);
        Assert.assertNotNull(temp.getLogin());//her kişinin 1 login'i olacağı için onunda null olup olmadığı durumunu kontrol ediyoruz.
        //id'ye göre arama işlemini kontrol ettik.
        Assert.assertEquals(temp.getKisi_id(), ((Kisi) getDao().findById(temp.getKisi_id())).getKisi_id());
        //update işlemini kontrol ettik.
        temp.getLogin().setSifre("UPDATE" + stringUret());
        temp.setAd("UPDATEisim");
        temp.setSoyad("UPDATEsoyisim");
        temp.setE_posta("update"+stringUret()+"@gmail.com");//unique olduğu için string üretiyoruz.
        getDao().update(temp);
        Kisi tempCopy = (Kisi) getDao().findById(temp.getKisi_id());
        //gönderilen değerler ile gelen değerleri karşılaştırdık.
        Assert.assertEquals(temp.getLogin().getSifre(), tempCopy.getLogin().getSifre());
        Assert.assertEquals(temp.getAd(), tempCopy.getAd());
        Assert.assertEquals(temp.getSoyad(), tempCopy.getSoyad());
        Assert.assertEquals(temp.getE_posta(), tempCopy.getE_posta());
    }

    
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
        kisi = null;
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
