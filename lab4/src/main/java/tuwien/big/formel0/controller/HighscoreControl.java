package tuwien.big.formel0.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.math.BigInteger;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.entities.RegisteredPlayerPool;
import tuwien.big.formel0.highscore.*;

/**
 *
 * @author alex
 */
@ManagedBean(name = "hs")
@SessionScoped
public class HighscoreControl {

    FacesContext context = FacesContext.getCurrentInstance();
    RegisteredPlayerPool rpp;
    
    
    private PublishHighScoreService highscoreService;
    
    

    public HighscoreControl() {
        this.rpp = (RegisteredPlayerPool) context.getApplication().evaluateExpressionGet(context, "#{rpp}", RegisteredPlayerPool.class);
        highscoreService = new PublishHighScoreService();
    }

    public String postHighscore(formel0api.Game currentGame) {
        //rpp.getClass();
        
        //System.out.println("POSTING HIGH SCORE! " + this.getRpp().getRegplayers().size());
       // System.out.println("Player name test: " + this.getRpp().getRegplayers().get(0).getName());

        ObjectFactory factory = new ObjectFactory();

        HighScoreRequestType request = factory.createHighScoreRequestType();
        
        
        //Set name, birthday and gender
        String playerName = currentGame.getPlayer().getName();
        String birthdayString = "";
        String playerGender = "";
        
        
        long duration = currentGame.getSpentTime()/1000;
        String winner = currentGame.getLeader().getName();
        
        try {
        //for loop to set the player's birthday and gender
        for (int i = 0; i < this.getRpp().getRegplayers().size(); i++) {
            Player playerEntity = getRpp().getRegplayers().get(i);
            if (this.getRpp().getRegplayers().get(i).getName().equals(currentGame.getPlayer().getName())) {
                birthdayString = playerEntity.getBirthday();
                if (playerEntity.getSex().equals("m")) {
                    playerGender = "MALE";
                } else {
                    playerGender = "FEMALE";
                }
            }
        }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
      
        

        try {

            request.setUserKey("34EphAp2C4ebaswu");
            
            System.out.println("BIRTHDAY TEST: " + birthdayString);
            String[] birthdaySplit = birthdayString.split("\\.");
            System.out.println("SPLIT TEST SIZE " + birthdaySplit.length);
            
            
            
            int birthYear = Integer.valueOf(birthdaySplit[2]);
            int birthMonth = Integer.valueOf(birthdaySplit[1]);
            int birthDay = Integer.valueOf(birthdaySplit[0]);
            
            
            XMLGregorianCalendar datetime = XMLGregorianCalendarImpl.createDateTime(2013, 6, 3, 0, 0, 0);
            XMLGregorianCalendar date = XMLGregorianCalendarImpl.createDate(2013, 6, 3, 0);
            XMLGregorianCalendar birthday = XMLGregorianCalendarImpl.createDate(birthYear, birthMonth, birthDay, 0);

            //Create TournamentType
            TournamentType tournament = factory.createTournamentType();
            
            //Create players in tournament
            TournamentType.Players players = factory.createTournamentTypePlayers();
            TournamentType.Players.Player person = factory.createTournamentTypePlayersPlayer();

            //Create rounds in tournament
            TournamentType.Rounds rounds = factory.createTournamentTypeRounds();
            TournamentType.Rounds.Round round = factory.createTournamentTypeRoundsRound();

            //Create GameType
            GameType game = factory.createGameType();
            
            //Create players in game
            GameType.Players gamePlayers = factory.createGameTypePlayers();
            GameType.Players.Player gamePlayer = factory.createGameTypePlayersPlayer();
            gamePlayer.setRef(playerName);
            gamePlayers.getPlayer().add(gamePlayer);
            
            //Set game attributes
            game.setDate(date);
            game.setStatus("finished");
            game.setDuration(BigInteger.valueOf(duration));
            game.setWinner(winner);
            game.setPlayers(gamePlayers);
                     
            
            //Set round attributes
            round.setNumber(0);
            round.getGame().add(game);
            
            //Add round to rounds
            rounds.getRound().add(round);

            //Set person attributes
            person.setUsername(playerName);
            person.setGender(playerGender);
            person.setDateOfBirth(birthday);

            //Add player (person) to players
            players.getPlayer().add(person);

            //Set tournament attributes
            tournament.setStartDate(date);
            tournament.setEndDate(date);
            tournament.setRegistrationDeadline(datetime);
            tournament.setPlayers(players);
            tournament.setRounds(rounds);

            //Set tournament in the HighScoreRequestType object
            request.setTournament(tournament);
            
            
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }


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
}
