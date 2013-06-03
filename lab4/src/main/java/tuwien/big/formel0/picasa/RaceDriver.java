package tuwien.big.formel0.picasa;

import java.io.Serializable;
import javax.faces.bean.NoneScoped;
import javax.persistence.Entity;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;


/**
 * Represents a race driver from Picasa
 * @author pl
 *
 */

@Entity
@ManagedBean(name = "entity")
@Table(name="Avatar")
@NoneScoped

public class RaceDriver implements Serializable {

    private String name;
    private String url;
    private String wikiUrl;
    @Id
    private int raceDriverId;

    public RaceDriver() {
    }
    
    public void setRaceDriverId(int raceDriverId){
        this.raceDriverId = raceDriverId;
    }
    
    public int getRaceDriverId(){
        return raceDriverId;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }   

}