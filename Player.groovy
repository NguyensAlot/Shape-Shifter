/**
 * Created by Anthony on 5/19/2016.
 * Description: This class handles the Player game object
 */


import java.awt.geom.Ellipse2D

public class Player extends GameObject{
    def _move = 80

    /**
     * Recreate the player shape with its position shifted to the left
     *
     * */
    def MoveLeft() {
        _pos.x -= _move
        _shape = new Ellipse2D.Double(_pos?.x, _pos?.y, _size, _size)
    }

    /**
     * Recreate the player shape with its position shifted to the right
     *
     * */
    def MoveRight() {
        _pos.x += _move
        _shape = new Ellipse2D.Double(_pos?.x, _pos?.y, _size, _size)
    }
}
