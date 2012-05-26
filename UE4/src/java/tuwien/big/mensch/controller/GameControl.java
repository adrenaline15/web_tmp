/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.mensch.controller;

import gameapi.Field;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import gameapi.Game;
import gameapi.Player;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import tuwien.big.highscore.*;

@ManagedBean(name = "gc")
@SessionScoped
public class GameControl {

    private String playername;
    private Player player;
    private Player computeropponent;
    private Game game;
    private int score = 0;
    private HighScoreRequestType hsrt;

    /**
     * Creates a new instance of MemoryControl
     */
    public GameControl() {
    }

    public GameControl(String playername) {
        this.playername = playername;
        init();
    }

    /**
     * Initializes a new game
     */
    public void init() {
        List<Player> playerlist = new ArrayList<Player>();



        player = new Player(this.playername);
        playerlist.add(player);

        computeropponent = new Player("Computer");
        playerlist.add(computeropponent);

        this.game = new Game(playerlist, true);
        score = 0;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public String getTime() {
        long milliseconds = game.getSpentTime();
        return String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                (TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))));
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return
     * <code>true</code> if this game is over,
     * <code>false</code> otherwise.
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
        return game.getRound();
    }

    /**
     * Return an unmodifiable list of the players participating in this game
     *
     * @return an unmodifiable list of the players participating in this game
     */
    public List<Player> getPlayers() {
        return game.getPlayers();
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
     * Specifies if a counter of a player occupies the field identified by
     * <code>index</code> and when yes which player's counter occupies it
     *
     * @param index Index of the field for which should be checked if and which
     * player's counter occupies it
     * @return number of the player whose counter occupies the field identified
     * by the given
     * <code>intex</code>, i.e., 1, 2, 3 or 4; or -1 if no counter occupies this
     * field
     */
    public int isFieldOccupied(int index) {
        if (this.game.getPositionplayer1() == index) {
            return 1;
        } else if (this.game.getPositionplayer2() == index) {
            return 2;
        } else if (this.game.getPositionplayer3() == index) {
            return 3;
        } else if (this.game.getPositionplayer4() == index) {
            return 4;
        }
        return -1;
    }

    /**
     * Rolls the dice for the player
     */
    public void rollDice() {
        if (isGameOver()) {
            callThis();
            //return;
        }

        this.score = game.rollthedice(player);
    }

    public void callThis() {

        System.out.println("--------------------CALL THIS--------------------------");

        PublishHighScoreService phss = new PublishHighScoreService();
        PublishHighScoreEndpoint phse = phss.getPublishHighScorePort();
        hsrt = new HighScoreRequestType();
        hsrt.setUserKey("G8N4P3E5D2S8Y4X2V9M5N");

        InfoType i = new InfoType();
        i.setName("Mensch aergere dich nicht");
        InfoType.Players ps = new InfoType.Players();
        ps.setNumber(2);
        InfoType.Players.Screenname s1 = new InfoType.Players.Screenname();
        s1.setPlayer(1);
        s1.setValue("TESTPLAYER");
        ps.getScreenname().add(s1);

        InfoType.Players.Screenname s2 = new InfoType.Players.Screenname();
        s2.setPlayer(2);
        s2.setValue("CPU");
        ps.getScreenname().add(s2);
        i.setPlayers(ps);



        InfoType.Winner win = new InfoType.Winner();
        win.setPlayer(1);
        i.setWinner(win);

        DescType dtype = new DescType();
        dtype.getContent().add("Description");
        i.setDescription(dtype);

        i.setRounds(BigInteger.valueOf(12));

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar xmlc;
        try {
            xmlc = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            i.setStarted(xmlc);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }


        tuwien.big.highscore.Game g = new tuwien.big.highscore.Game();
        g.setInfo(i);
        hsrt.setGame(g);
        try {
            String publish = phse.publishHighScore(hsrt);
            System.out.println(publish);
        } catch (Failure ex) {
            System.out.println("--------------------EXCEPTION HERE--------------------------");
            //Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);;
            System.out.println(ex.getFaultInfo().getDetail() + " | " + ex.getFaultInfo().getReason());
        }

    }

    /**
     * Returns the score thrown by the player
     *
     * @return the score thrown by the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns the score of the computer opponent
     *
     * @return the score of the computer-controlled opponent
     */
    public List<Integer> getOpponentScore() {
        return game.getOpponentScore();
    }

    /**
     * Returns the fields of the board
     *
     * @return fields of the board
     */
    public List<Field> getBoardFields() {
        return game.getBoardFields();
    }

    /**
     * Returns the computer opponent
     *
     * @return computer opponent
     */
    public Player getComputeropponent() {
        return computeropponent;
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
