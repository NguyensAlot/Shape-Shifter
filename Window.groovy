/**
 * Created by Anthony on 5/16/2016.
 * Description: This class will handle rendering of the window and game object interactions
 */


import javax.swing.JFrame
import javax.swing.JOptionPane
import java.awt.Color
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D

class Window implements KeyListener {
    def _width = 800, _height = 600, ticks = 0, score = 0
    def _playing
    GameObject _p1, _p2
    JFrame _gameFrame
    MainDraw _gameDraw
    List _obstacles

    Window() {
        // flag if game lost
        _playing = true

        // some player objects (circles)
        _p1 = new Player(_name: "player1", _color: Color.RED, _pos: [x: _width/8*0+10, y: _height-100], _move: _width/10)
        _p2 = new Player(_name: "player2", _color: Color.BLUE, _pos: [x: _width/8*4+10, y: _height-100], _move: _width/10)
        _p1._shape = new Ellipse2D.Double(_p1?._pos?.x, _p1?._pos?.y, _p1._size, _p1._size)
        _p2._shape = new Ellipse2D.Double(_p2?._pos?.x, _p2?._pos?.y, _p2._size, _p2._size)

        // game obstacles (squares)
        _obstacles = new ArrayList<GameObject>()
        _obstacles.add(new GameObject(_name: "block1", _pos: [x: _width/10*0+10, y: 0]))
        _obstacles.add(new GameObject(_name: "block2", _pos: [x: _width/10*4+10, y: 100]))

        // graphics handler
        _gameDraw = new MainDraw()
        _gameDraw._pMap.put(_p1._name, _p1)
        _gameDraw._pMap.put(_p2._name, _p2)
        _gameDraw._pMap.put("middleLine", new GameObject(_shape: new Line2D.Double(_width/2-5, 0, _width/2-5, _height)))
        _obstacles.each { b ->
            _gameDraw._pMap.put(b._name, b)
        }

        // frame
        _gameFrame = new JFrame("Shape Shifter")
        _gameFrame.setSize(_width, _height)
        _gameFrame.setResizable(false)
        _gameFrame.setMinimumSize(new Dimension(800, 600));
        _gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        _gameFrame.getContentPane().add(_gameDraw)
        _gameFrame.addKeyListener(this)
        _gameFrame.setVisible(true)
    }

    /**
     * Main game loop, updates unit interactions
     *
     * */
    def Run() {
        while (_playing) {
            _obstacles.each { b ->
                if (Collision(_p1, b) || Collision(_p2, b)) {
                    _playing = false
                    _gameFrame.removeKeyListener(this)
                    JOptionPane.showMessageDialog(null, "GAME OVER!\nYou dodged $score block(s) ")
                }
            }
            // increment ticks
            ticks++;
            // create a 2nd array list for removing
            ArrayList toRemove = new ArrayList<GameObject>()

            // every 50000 ticks, create a new block
            if (ticks % 30000 == 0 && _obstacles.size() < 20) {
                CreateObstacle()
            }

            // every 250 ticks, move the block down by 1 pixel
            if (ticks % 200 == 0) {
                // use of closure with each loop
                _obstacles.each { b ->
                    b.MoveObstacle()
                    // put updated block back in map
                    _gameDraw._pMap.put(b._name, b)
                    // if block is outside of window, add to list to remove and increment score
                    if (b._pos.y > _height) {
                        toRemove.add(b)
                        score++
                    }
                }
            }
            // remove blocks
            if (toRemove.size() > 0) {
                _obstacles.removeAll(toRemove)
            }
            // repaint
            _gameDraw.repaint()
        }
    }

    void keyTyped(KeyEvent e) {
    }
    void keyReleased(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed. Will move the circle shapes when A-D or Left-Right is pressed
     *
     */
    @Override
    void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (_p1._pos.x - (int)_p1._move > 0) {
                    _p1.MoveLeft()
                    _gameDraw._pMap.put(_p1._name, _p1)
                }
                break
            case KeyEvent.VK_D:
                if (_p1._pos.x + (int)_p1._move < _width/2) {
                    _p1.MoveRight()
                    _gameDraw._pMap.put(_p1._name, _p1)
                }
                break
            case KeyEvent.VK_LEFT:
                if (_p2._pos.x - (int)_p2._move > _width/2) {
                    _p2.MoveLeft()
                    _gameDraw._pMap.put(_p2._name, _p2)
                }
                break
            case KeyEvent.VK_RIGHT:
                if (_p2._pos.x + (int)_p2._move < _width) {
                    _p2.MoveRight()
                    _gameDraw._pMap.put(_p2._name, _p2)
                }
                break
        }
        _gameDraw.repaint()
    }

    /**
     * Method will create 2 new GameObjects and add to list of obstacles for painting
     *
     * */
    def CreateObstacle() {
        Random r = new Random()
        def col = r.nextInt(5)
        def col2 = r.nextInt(5) + 5
        _obstacles.add(new GameObject(_name: "block"+ticks, _pos: [x: _width/10*col+10, y: 0]))
        _obstacles.add(new GameObject(_name: "block"+ticks+1, _pos: [x: _width/10*col2+10, y: 0]))
    }

    /**
     * Simple collision detection algorithm between a player and obstacle.
     *
     * */
    boolean Collision(player, obstacle) {
        def oY = obstacle?._pos?.y + obstacle?._size
        if (player?._pos?.x == obstacle?._pos?.x &&
                (player._pos.y - oY < 0 && player._pos.y - oY > -100)) {
            return true
        }
        return false
    }
}