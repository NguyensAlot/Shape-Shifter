/**
 * Created by Anthony on 5/19/2016.
 */


import java.awt.geom.Ellipse2D

public class Player extends GameObject{
    def _move = 80

    def MoveLeft() {
        _pos.x -= _move
        _shape = new Ellipse2D.Double(_pos?.x, _pos?.y, _size, _size)
    }

    def MoveRight() {
        _pos.x += _move
        _shape = new Ellipse2D.Double(_pos?.x, _pos?.y, _size, _size)
    }
}
