package tuwien.big.formel0.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import formel0api.Game;
import formel0api.Player;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.formel0.entities.RegisteredPlayerPool;
import tuwien.big.formel0.highscore.Failure;
import tuwien.big.formel0.highscore.HighScoreRequestType;
import tuwien.big.formel0.highscore.HighScoreService;
import tuwien.big.formel0.highscore.ObjectFactory;
import tuwien.big.formel0.highscore.PublishHighScoreService;
import tuwien.big.formel0.highscore.TournamentType;

@ManagedBean(name = "gc")
@SessionScoped
public class GameControl {

    Player player;
    Player computer;
    
    
    Game game;
    int playerscore = 0;
    int computerscore = 0;
    int round = 1;
    String playername;
       
    
    @ManagedProperty(value="#{highScoreService}")
    private HighScoreService highScoreService;
    
   

    public GameControl() {
        this("Susi");
    }

    /**
     * Initializes a new game.
     */
    public GameControl(String playername) {
        this.playername = playername;
        init();
    }

    public void init() {
        player = new Player(playername);
        computer = new Player("Deep Blue");
        this.game = new Game(player, computer);
        round = 1;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public String getTime() {
        long milliseconds = game.getSpentTime();
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                (TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))));
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return <code>true</code> if this game is over, <code>false</code>
     * otherwise.
     */
    public boolean isGameOver() {
        return game.isGameOver();
    }

    /**
     * Returns the rounds already played in this game
     *
     * @return the rounds already played in this game
     */
    public int getRound() {
        return round;
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public Player getLeader() {
        return game.getLeader();
    }

    /**
     * Rolls the dice for the player
     */
    public void doRound() {
        if (isGameOver()) {
            return;
        }
        this.playerscore = game.rollthedice(player);
        if (!isGameOver()) {
            this.computerscore = game.rollthedice(computer);
        } else {
            this.computerscore = 0;
        }
        ++round;
        
       System.out.println("isGameOver: "+isGameOver());

        if (isGameOver()) {
                   System.out.println("Game is over, setting highScoreService");

//            highScoreService=new HighScoreService();
       
            highScoreService.setGame(game);
            
            System.out.println("Calling Highscoreservice");
            String uuid=highScoreService.callHighscore();
            
            System.out.println("UUID:"+uuid);
            
            
        }
    }
    
    /**
     * Returns the score thrown by the player
     *
     * @return the score thrown by the player
     */
    public String getDiceResource() {
        return "img:wuerfel" + getPlayerScore() + ".png";
    }

    /**
     * Returns the score thrown by the player
     *
     * @return the score thrown by the player
     */
    public int getPlayerScore() {
        return this.playerscore;
    }

    /**
     * Returns the score of the computer
     *
     * @return the score of the computer-controlled opponent
     */
    public int getComputerScore() {
        return this.computerscore;
    }

    /**
     * Returns player 1 of the game
     *
     * @return player 1 of the game
     */
    public Player getPlayer1() {
        return this.player;
    }

    /**
     * Return player 2 of the game
     *
     * @return player 2 of the game
     */
    public Player getPlayer2() {
        return this.computer;
    }

    public void setHighScoreService(HighScoreService highScoreService) {
        this.highScoreService = highScoreService;
    }

    public HighScoreService getHighScoreService() {
        return highScoreService;
    }

    
}