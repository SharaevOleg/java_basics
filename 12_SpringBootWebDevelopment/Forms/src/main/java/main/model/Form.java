package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fIO;
    private String pasport;
    private String adress;
    private int summ;
    private int time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //---------------------------------------------------


    public String getfIO() {
        return fIO;
    }

    public void setfIO(String fIO) {
        this.fIO = fIO;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

