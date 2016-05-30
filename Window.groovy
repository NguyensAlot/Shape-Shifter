/**
 * Created by Anthony on 5/16/2016.
 */


import javax.swing.JFrame
import javax.swing.JLabel
import java.awt.Color
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D


class Window implements KeyListener {
    def _width = 800, _height = 600, ticks = 0
    def _playing
    GameObject _p1, _p2
    JFrame _gameFrame
    JLabel _score
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
        _obstacles.add(new GameObject(_name: "block", _pos: [x: _width/8*0+10, y: 0], _index: _obstacles.size()))
        _obstacles.add(new GameObject(_name: "block2", _pos: [x: _width/8*4+10, y: 100], _index: _obstacles.size()))

        // graphics handler
        _gameDraw = new MainDraw()
        _gameDraw._pMap.put(_p1._name, _p1)
        _gameDraw._pMap.put(_p2._name, _p2)
        _gameDraw._pMap.put("middleLine", new GameObject(_shape: new Line2D.Double(_width/2-5, 0, _width/2-5, _height)))
        _obstacles.each { b ->
            _gameDraw._pMap.put(b._name, b)
        }

        // score
        _score = new JLabel(text: ticks.toString())

        // frame
        _gameFrame = new JFrame("Shape Shifter")
        _gameFrame.setSize(_width, _height)
        _gameFrame.setResizable(false)
        _gameFrame.setMinimumSize(new Dimension(800, 600));
        _gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        _gameFrame.add(_score)
        _gameFrame.getContentPane().add(_gameDraw)
        _gameFrame.addKeyListener(this)
        _gameFrame.setVisible(true)
    }

    def Run() {
        while (_playing) {
            ticks++;
            ArrayList toRemove = new ArrayList<GameObject>()
            if (ticks % 750000 == 0 && _obstacles.size() < 20) CreateObstacle()
            if (ticks % 5000 == 0) {
                _obstacles.each { b ->
                    b.MoveObstacle()
                    _gameDraw._pMap.put(b._name, b)
                    if (b._pos.y > _height) toRemove.add(b)
                }
            }
            if (toRemove.size() > 0) {
                _obstacles.removeAll(toRemove)
            }
            _gameDraw.repaint()
        }
    }

    void keyTyped(KeyEvent e) {
    }
    void keyReleased(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
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

    def CreateObstacle() {
        Random r = new Random()
        def col = r.nextInt(5)
        def col2 = r.nextInt(5) + 5
        _obstacles.add(new GameObject(_name: "block"+ticks, _pos: [x: _width/10*col+10, y: 0], _index: _obstacles.size()))
        _obstacles.add(new GameObject(_name: "block"+ticks+1, _pos: [x: _width/10*col2+10, y: 0], _index: _obstacles.size()))
    }

    boolean Collision(player, obstacle) {
        if (player._pos.y)
        return false;
    }
}

