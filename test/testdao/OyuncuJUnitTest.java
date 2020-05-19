/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.OyuncuDAO;
import entity.Oyuncu;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class OyuncuJUnitTest extends KisiJUnitTest {

    private Oyuncu oyuncu;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        setDao(new OyuncuDAO());
        oyuncu = new Oyuncu();
        oyuncu.setYas((short)37);
        oyuncu.setKisi(getKisi());
    }

    @Test
    @Override
    public void insertTest() {
        //login, kisi ve oyuncu tablosuna veri eklendi.
        Assert.assertNotNull(getDao().insert(oyuncu));
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        ArrayList<Oyuncu> tempList = (ArrayList<Oyuncu>) getDao().findAll();
        Assert.assertNotNull(tempList);//findAll metodunu kontrol ettik.
        Oyuncu temp = tempList.get(tempList.size() - 1);
        Assert.assertNotNull(temp.getShipList());//her oyuncunun en az 1 tane uzay gemisi olduğu için null olup olmadığını kontrol ediyoruz.
        Assert.assertNotNull(temp.getKisi());//her oyuncu 1 kişi olacağı için onunda null olup olmadığını kontrol ediyoruz.
        Assert.assertNotNull(temp.getKisi().getLogin());//her kişinin 1 login'i olacağı için onunda null olup olmadığı durumunu kontrol ediyoruz.
        //id'ye göre arama işlemini kontrol ettik.
        Assert.assertEquals(temp.getOyuncu_id(), ((Oyuncu) getDao().findById(temp.getOyuncu_id())).getOyuncu_id());
        //update işlemini kontrol ettik.
        temp.getKisi().getLogin().setSifre("UPDATE" + stringUret());
        temp.getKisi().setAd("UPDATEisim");
        temp.getKisi().setSoyad("UPDATEsoyisim");
        temp.getKisi().setE_posta("update"+stringUret()+"@gmail.com");//unique olduğu için string üretiyoruz.
        temp.setYas((short)2);//update edildiği belli olması için küçük değer girdik.
        getDao().update(temp);
        Oyuncu tempCopy = (Oyuncu) getDao().findById(temp.getOyuncu_id());
        //gönderilen değerler ile gelen değerleri karşılaştırdık.
        Assert.assertEquals(temp.getKisi().getLogin().getSifre(), tempCopy.getKisi().getLogin().getSifre());
        Assert.assertEquals(temp.getKisi().getAd(), tempCopy.getKisi().getAd());
        Assert.assertEquals(temp.getKisi().getSoyad(), tempCopy.getKisi().getSoyad());
        Assert.assertEquals(temp.getKisi().getE_posta(), tempCopy.getKisi().getE_posta());
        Assert.assertEquals(temp.getYas(), tempCopy.getYas());
    }
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
        oyuncu = null;
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
