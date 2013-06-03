package tuwien.big.formel0.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.formel0.highscore.*;

/**
 *
 * @author alex
 */
@ManagedBean(name = "hs")
@ApplicationScoped
public class HighscoreControl {

    private PublishHighScoreService highscoreService;

    public HighscoreControl() {
        highscoreService = new PublishHighScoreService();
    }

    public String postHighscore() {
        System.out.println("POSTING HIGH SCORE!");
        
        ObjectFactory factory = new ObjectFactory();

        HighScoreRequestType request = factory.createHighScoreRequestType();
        request.setUserKey("34EphAp2C4ebaswu");

        TournamentType tournament = factory.createTournamentType();
        request.setTournament(tournament);

        XMLGregorianCalendar datetime = XMLGregorianCalendarImpl.createDateTime(2013, 6, 3, 0, 0, 0);
        XMLGregorianCalendar date = XMLGregorianCalendarImpl.createDate(2013, 6, 3, 0);

        tournament.setStartDate(date);
        tournament.setEndDate(date);
        tournament.setRegistrationDeadline(datetime);
        
        
        
        String response = "";

        try {
            response = highscoreService.getPublishHighScorePort().publishHighScore(request);
        } catch (Failure e) {
            System.out.println(e);
            e.printStackTrace();
            System.err.println(e.getFaultInfo().getDetail());
        }
        return response;

    }
}
