package tuwien.big.formel0.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.formel0.highscore.*;




/**
 *
 * @author alex
 */
public class HighscoreControl {
    private PublishHighScoreService highscoreService;
    
    public HighscoreControl() {
        highscoreService = new PublishHighScoreService();
    }
    
    public String postHighscore() {
        ObjectFactory factory = new ObjectFactory();
        
        HighScoreRequestType request = factory.createHighScoreRequestType();
        request.setUserKey("34EphAp2C4ebaswu");
        
        TournamentType tournament = factory.createTournamentType();
        request.setTournament(tournament);
        
        XMLGregorianCalendar datetime = XMLGregorianCalendarImpl.createDateTime(2013, 6, 3, 0, 0, 0);
        XMLGregorianCalendar date = XMLGregorianCalendarImpl.createDate(2013, 6, 3, 0);
        
        
        return null;
    }
}
