/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import dao.AbstractDAO;

/**
 *
 * @author burak
 */
public class DAOContext {

    private AbstractDAO dao;

    public DAOContext() {
    }

    public DAOContext(AbstractDAO dao) {
        this.dao = dao;
    }

    public void setDao(AbstractDAO dao) {
        this.dao = dao;
    }

    public Object insert(Object object) {
        return this.dao.insert(object);
    }

    public void update(Object object) {
        this.dao.update(object);
    }

    public Object findById(Object object) {
        return this.dao.findById(object);
    }

    public Object findByObject(Object object) {
        return this.dao.findByObject(object);
    }

    public Object findAll() {
        return this.dao.findAll();
    }
}
