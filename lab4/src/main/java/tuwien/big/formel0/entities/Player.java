package tuwien.big.formel0.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.persistence.*;
import tuwien.big.formel0.controller.RaceDriverControl;
import tuwien.big.formel0.picasa.RaceDriver;

@ManagedBean(name = "player")
@NoneScoped
public class Player {
    
    private String firstname = null;
    private String lastname = null;
    private String name = null;
    private String password = null;
    private String birthday = null;
    private String sex = null;
    private RaceDriver racedriver = null;

    /**
     * Creates a new instance of Player
     */
    public Player() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the racedriver
     */
    public RaceDriver getRaceDriver() {
        return racedriver;
    }

    /**
     * @param racedriver the racedriver to set
     */
    public void setRaceDriver(RaceDriver racedriver) {
        this.racedriver = racedriver;
    }
    
    public void setRaceDriverName(String name) {
        setRaceDriver(RaceDriverControl.getRaceDriver(name));
    }
    
    public String getRaceDriverName() {
        if (this.racedriver != null) {
            return this.racedriver.getName();
        }
        return "kein Rennfahrer!";
    }
    
    public String getRaceDriverWiki() {
        return this.racedriver.getWikiUrl();
    }
    
    public void setRaceDriverWiki(String wiki) {
        this.racedriver.setWikiUrl(wiki);
    }
    
    public void setRaceDriverUrl(String url) {
        this.racedriver.setUrl(url);
    }
    
    public String getRaceDriverUrl() {
        return this.racedriver.getUrl();
    }
}
