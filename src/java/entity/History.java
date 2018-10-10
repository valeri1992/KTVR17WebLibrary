/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Melnikov
 */
@Entity
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.MERGE})
    private Book book;
    @OneToOne()
    private Reader reader;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBegin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReturn;

    public History() {
    }

    public History(Book book, Reader reader, Date dateBegin, Date dateReturn) {
        this.book = book;
        this.reader = reader;
        this.dateBegin = dateBegin;
        this.dateReturn = dateReturn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.book);
        hash = 97 * hash + Objects.hashCode(this.reader);
        hash = 97 * hash + Objects.hashCode(this.dateBegin);
        hash = 97 * hash + Objects.hashCode(this.dateReturn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.dateBegin, other.dateBegin)) {
            return false;
        }
        if (!Objects.equals(this.dateReturn, other.dateReturn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdm = new SimpleDateFormat("dd.mm.yyyy");
        if(dateReturn != null){
            return "History{" + "id=" + id + ", book=" + book.getNameBook() + ", reader=" + reader.getName()+" "+reader.getSurname() + ", dateBegin=" + sdm.format(dateBegin) + ", dateReturn=" + sdm.format(dateReturn) + '}';
        }else{
            return "History{" + "id=" + id + ", book=" + book.getNameBook() + ", reader=" + reader.getName()+" "+reader.getSurname() + ", dateBegin=" + sdm.format(dateBegin) + '}';
        }
    }
    
    

    
    
}
