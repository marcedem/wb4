package tuwien.big.formel0.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.math.BigInteger;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.formel0.entities.Player;
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
        
        XMLGregorianCalendar datetime = XMLGregorianCalendarImpl.createDateTime(2013, 6, 3, 0, 0, 0);
        XMLGregorianCalendar date = XMLGregorianCalendarImpl.createDate(2013, 6, 3, 0);
        XMLGregorianCalendar birthday = XMLGregorianCalendarImpl.createDate(1990, 1, 1, 0);
        
        TournamentType tournament = factory.createTournamentType();
        
        TournamentType.Players players = factory.createTournamentTypePlayers();
        TournamentType.Players.Player person = null;
        
        TournamentType.Rounds rounds = factory.createTournamentTypeRounds();
        TournamentType.Rounds.Round round = null;
        
        GameType game = factory.createGameType();
        
        game.setDate(date);
        game.setStatus("finished");
        game.setDuration(BigInteger.valueOf(23));
        game.setWinner("test");
        
        GameType.Players gamePlayers = null;
        gamePlayers.getPlayer().add(factory.createGameTypePlayersPlayer());
        game.setPlayers(gamePlayers);
        
        round.setNumber(0);
        round.getGame().add(game);
        
        person.setUsername("test");
        person.setGender("MALE");
        person.setDateOfBirth(birthday);
        
        players.getPlayer().add(person);
        
        
        tournament.setStartDate(date);
        tournament.setEndDate(date);
        tournament.setRegistrationDeadline(datetime);
        tournament.setPlayers(players);
        tournament.setRounds(rounds);
      
        request.setTournament(tournament);
                
        String response = "";

        System.out.println("HIGHSCORE POST SUCCESS NUMBER ONE!");
        
        try {
            response = highscoreService.getPublishHighScorePort().publishHighScore(request);
            System.out.println("HIGHSCORE POST SUCCESS!");
        } catch (Failure e) {
            System.out.println(e);
            e.printStackTrace();
            System.err.println(e.getFaultInfo().getDetail());
        }
        return response;

    }
}
