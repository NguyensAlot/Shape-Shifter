/**
 * Created by Anthony on 5/30/2016.
 * Description: The super class GameObject has basic components for graphic rendering and game logic
 */


import java.awt.Color
import java.awt.Rectangle
import java.awt.Shape

class GameObject {
    // some fields for inheriting
    protected String _name
    protected Shape _shape
    protected Color _color
    protected def _pos = [x: 0, y: 0]
    protected def _size = 50

    /**
     * Default constructor, set some properties
     *
     * */
    GameObject() {
        _color = Color.BLACK
        _shape = new Rectangle(_pos.x, _pos.y, _size, _size)
    }

    /**
     *
     *
     * */
    def MoveObstacle() {
        _pos.y++
        _shape = new Rectangle((int)_pos?.x, (int)_pos?.y, _size, _size)
    }
}
