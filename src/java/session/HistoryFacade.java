/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Book;
import entity.History;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "KTVR17WebLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }
    
    public List<History> findTakeBooks(){
        return em.createQuery("SELECT h FROM History h WHERE h.dateReturn=NULL")
                .getResultList();
    }

    public List<History> fineByBook(Book book) {
       return em.createQuery("SELECT h FROM History h WHERE h.book =:book").setParameter("book", book).getResultList();
    }
    
}
