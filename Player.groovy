/**
 * Created by Anthony on 5/19/2016.
 */


import java.awt.Color
import java.awt.Shape
import java.awt.geom.Ellipse2D

public class Player extends GameObject{
    String _name
    Shape _shape
    Color _color
    def _pos = []
    def _size = 50, _move = 80

    Player() {

    }
}
