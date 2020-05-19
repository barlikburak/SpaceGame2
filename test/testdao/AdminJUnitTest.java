/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.AdminDAO;
import entity.Admin;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class AdminJUnitTest extends KisiJUnitTest {

    private Admin admin;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        setDao(new AdminDAO());
        admin = new Admin();
        admin.setTelefon("+905350001122");
        admin.setKisi(getKisi());
    }

    @Test
    @Override
    public void insertTest() {
        //login, kisi ve admin tablosuna veri eklendi.
        Assert.assertNotNull(getDao().insert(admin));
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        ArrayList<Admin> tempList = (ArrayList<Admin>) getDao().findAll();
        Assert.assertNotNull(tempList);//findAll metodunu kontrol ettik.
        Admin temp = tempList.get(tempList.size() - 1);
        Assert.assertNotNull(temp.getKisi());//her yönetici 1 kişi olacağı için onunda null olup olmadığını kontrol ediyoruz.
        Assert.assertNotNull(temp.getKisi().getLogin());//her kişinin 1 login'i olacağı için onunda null olup olmadığı durumunu kontrol ediyoruz.
        //id'ye göre arama işlemini kontrol ettik.
        Assert.assertEquals(temp.getAdmin_id(), ((Admin) getDao().findById(temp.getAdmin_id())).getAdmin_id());
        //update işlemini kontrol ettik.
        temp.getKisi().getLogin().setSifre("UPDATE" + stringUret());
        temp.getKisi().setAd("UPDATEisim");
        temp.getKisi().setSoyad("UPDATEsoyisim");
        temp.getKisi().setE_posta("update"+stringUret()+"@gmail.com");//unique olduğu için string üretiyoruz.
        temp.setTelefon("0000000000000");//update edildiği belli olması için hepsini 0 girdik.
        getDao().update(temp);
        Admin tempCopy = (Admin) getDao().findById(temp.getAdmin_id());
        //gönderilen değerler ile gelen değerleri karşılaştırdık.
        Assert.assertEquals(temp.getKisi().getLogin().getSifre(), tempCopy.getKisi().getLogin().getSifre());
        Assert.assertEquals(temp.getKisi().getAd(), tempCopy.getKisi().getAd());
        Assert.assertEquals(temp.getKisi().getSoyad(), tempCopy.getKisi().getSoyad());
        Assert.assertEquals(temp.getKisi().getE_posta(), tempCopy.getKisi().getE_posta());
        Assert.assertEquals(temp.getTelefon(), tempCopy.getTelefon());
    }
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
        admin = null;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
