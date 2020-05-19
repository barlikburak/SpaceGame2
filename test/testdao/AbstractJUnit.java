/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import dao.AbstractDAO;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author burak
 */
public abstract class AbstractJUnit {

    private Random random;
    private AbstractDAO dao;

    public AbstractJUnit(AbstractDAO dao) {
        this.dao = dao;
        random = new Random();
    }

    /**
     *
     * @return 8 haneli string döndürüyor.
     */
    public String stringUret() {
        char[] array = new char[8];
        int index = 0;
        while (index != 5) {
            array[index] = (char) (random.nextInt(25) + 97);
            index++;
        }
        while (index != 8) {
            array[index] = (char) (random.nextInt(9) + 48);
            index++;
        }
        return new String(array);
    }

    @Test
    public abstract void insertTest();

    /**
     * Arama ve güncellemeyi tek metotta kontrol etmemizin güncelleme işlemi bir
     * değer döndermediği için arama ile kontrol etmek içindir.
     */
    @Test
    public abstract void updateAndSearchTest();

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public AbstractDAO getDao() {
        return dao;
    }

    public void setDao(AbstractDAO dao) {
        this.dao = dao;
    }

}
