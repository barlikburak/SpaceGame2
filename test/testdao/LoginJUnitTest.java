/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.LoginDAO;
import entity.Login;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author burak
 */
public class LoginJUnitTest extends AbstractJUnit {

    private Login login;
    private String[] email, password, user;
    private LoginDAO loginDAO;

    public LoginJUnitTest() {
        super(new LoginDAO());
    }

    @Before
    public void setUp() {
        login = new Login();
        login.setSifre(stringUret());
        email = new String[3];
        password = new String[3];
        user = new String[2];
        loginDAO = new LoginDAO();
        email[0] = "barlikburak@hotmail.com";
        email[1] = "hasanbasri@gmail.com";
        email[2] = "test@gmail.com";
        password[0] = "admin123";
        password[1] = "player123";
        password[2] = "yanlışŞifre";
        user[0] = "admin";
        user[1] = "oyuncu";
    }

    @Test
    @Override
    public void insertTest() {
        //login tablosuna veri eklendi.
        Assert.assertNotNull(getDao().insert(login));
    }

    @Test
    @Override
    public void updateAndSearchTest() {
        ArrayList<Login> tempList = (ArrayList<Login>) getDao().findAll();
        Assert.assertNotNull(tempList);//findAll metodunu kontrol ettik.
        Login temp = tempList.get(tempList.size() - 1);
        //id'ye göre arama işlemini kontrol ettik.
        Assert.assertEquals(temp.getLogin_id(), ((Login) getDao().findById(temp.getLogin_id())).getLogin_id());
        //update işlemini kontrol ettik.
        temp.setSifre("UPDATE" + stringUret());
        getDao().update(temp);
        Assert.assertEquals(temp.getSifre(), ((Login) getDao().findById(temp.getLogin_id())).getSifre());
    }

    @Test
    public void loginTest() {
        Assert.assertEquals(true, loginDAO.login(email[0], password[0], user[0]));
        Assert.assertEquals(false, loginDAO.login(email[1], password[1], user[1]));
        Assert.assertEquals(false, loginDAO.login(email[2], password[2], user[0]));
        Assert.assertEquals(false, loginDAO.login(email[2], password[2], user[1]));
    }

    @After
    public void tearDown() {
        login = null;
        setDao(null);
        setRandom(null);
        loginDAO = null;
        email = null;
        password = null;
        user = null;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
