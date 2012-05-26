/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapi;

/**
 * Class representing a field on the board
 */
public class Field {
    
    /**
     * Player occupying this field, i.e. 1, 2, 3, or 4, or -1 if this
     * field is not occupied.
     */
    private int occupyingPlayer;
    
    public Field(){
        occupyingPlayer = -1;
    }
    
    public Field(int occupyingPlayer){
        this.occupyingPlayer = occupyingPlayer;
    }
    
    public int getOccupyingPlayer(){
        return occupyingPlayer;
    }
    
    public void setOccupyingPlayer(int player){
        occupyingPlayer = player;
    }
}
