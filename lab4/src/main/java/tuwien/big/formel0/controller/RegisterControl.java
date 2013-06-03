package tuwien.big.formel0.controller;

import tuwien.big.formel0.utilities.Utility;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.entities.RegisteredPlayerPool;
import tuwien.big.formel0.picasa.RaceDriver;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@ManagedBean(name = "rc")
@RequestScoped
public class RegisterControl {

    @ManagedProperty(value = "#{player}")
    private Player newplayer;
    @ManagedProperty(value = "false")
    private boolean displayterms;
    @ManagedProperty(value = "#{rpp}")
    private RegisteredPlayerPool rpp;
    @ManagedProperty(value = "#{false}")
    private boolean registrationsuccessful;
    private RaceDriver[] driverlist;

    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("java:comp/env/jdbc/lab4");
     EntityManager em = emf.createEntityManager();
    
    
    /**
     * Creates a new instance of RegisterControl
     */
    public RegisterControl() {
    }

    public String register() {
        boolean success = getRpp().addPlayer(newplayer);

        if (success == true) {
            registrationsuccessful = true;
        }
        return "register";
    }

    //Checks if the display checkbox changed
    public void displayChanged(ValueChangeEvent e) {
        Boolean show = (Boolean) e.getNewValue();
        if (show != null) {
            displayterms = show;
        }

        FacesContext.getCurrentInstance().renderResponse();
    }

    //Validation of the birthday
    public void validateBirthday(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String bd = (String) value;

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);

        try {
            format.parse(bd);
        } catch (ParseException e) {
            String i18ntext = Utility.getResourceText(ctx, "msg", "birthdateformat");

            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_WARN, i18ntext, null);

            throw new ValidatorException(msg);
        }
    }

    /**
     * @return the newplayer
     */
    public Player getNewplayer() {
        return newplayer;
    }
    
    
    
    /**
     * @param newplayer the newplayer to set
     */
   //em.getTransaction().begin();
    public void setNewplayer(Player newplayer) {
        this.newplayer = newplayer;
        em.persist(newplayer);
    }

    /**
     * @return the displayterms
     */
    public boolean isDisplayterms() {
        return displayterms;
    }

    /**
     * @param displayterms the displayterms to set
     */
    public void setDisplayterms(boolean displayterms) {
        this.displayterms = displayterms;
    }

    /**
     * @return the rpp
     */
    public RegisteredPlayerPool getRpp() {
        return rpp;
    }

    /**
     * @param rpp the rpp to set
     */
    public void setRpp(RegisteredPlayerPool rpp) {
        this.rpp = rpp;
    }

    /**
     * @return the registrationsuccessful
     */
    public boolean isRegistrationsuccessful() {
        return registrationsuccessful;
    }

    /**
     * @param registrationsuccessful the registrationsuccessful to set
     */
    public void setRegistrationsuccessful(boolean registrationsuccessful) {
        this.registrationsuccessful = registrationsuccessful;
    }

    public boolean isEmptyRdc() {
        return RaceDriverControl.isEmpty();
    }

    public RaceDriver[] getRaceDriverList() {
        RaceDriverControl.updateRaceDriverList();
        int i = 0;
        driverlist = new RaceDriver[RaceDriverControl.getRaceDriverList().size()];
        for (RaceDriver driver : RaceDriverControl.getRaceDriverList()) {
            driverlist[i] = driver;
            i++;
        }
        return driverlist;

    }
    
    //em.getTransaction().commit();

    //em.close();
}
