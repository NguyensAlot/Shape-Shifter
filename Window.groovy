/**
 * Created by Anthony on 5/16/2016.
 */


import javax.swing.JFrame
import java.awt.Dimension
import java.awt.Shape
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.geom.Ellipse2D


class Window implements KeyListener {
    def _width = 800, _height = 600, ticks = 0, _move = 80
    def _loc = [p1_x: _width/8, p2_x: _width/8*4]
    def _playing
    Shape _p1, _p2
    JFrame _gameFrame
    MainDraw _gameDraw

    Window() {
        _playing = true

        _p1 = new Ellipse2D.Double(_loc.p1_x, _height-100, 50,50)
        _p2 = new Ellipse2D.Double(_loc.p2_x, _height-100, 50,50)

        _gameDraw = new MainDraw()
        _gameDraw._pMap = new HashMap<String, Shape>()
        _gameDraw._pMap.put("player1", _p1)
        _gameDraw._pMap.put("player2", _p2)

        _gameFrame = new JFrame("Shape Shifter")
        _gameFrame.setSize(_width, _height)
        _gameFrame.setResizable(false)
        _gameFrame.setMinimumSize(new Dimension(800, 600));
        _gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        _gameFrame.getContentPane().add(_gameDraw)
        _gameFrame.addKeyListener(this)
        _gameFrame.setVisible(true)
    }

//    @Override
//    public void actionPerformed(ActionEvent arg0) {
//        _gameDraw.repaint()
//        ticks++
//    }

    def Run() {
        while (_playing) {
            ticks++

        }
    }
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    @Override
    void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                _loc.p1_x -= _move
                _p1 = CreatePlayer(_loc.p1_x)
                _gameDraw._pMap.put("player1", _p1)
                break
            case KeyEvent.VK_D:
                _loc.p1_x += _move
                _p1 = CreatePlayer(_loc.p1_x)
                _gameDraw._pMap.put("player1", _p1)
                break
            case KeyEvent.VK_LEFT:
                _loc.p2_x -= _move
                _p2 = CreatePlayer(_loc.p2_x)
                _gameDraw._pMap.put("player2", _p2)
                break
            case KeyEvent.VK_RIGHT:
                _loc.p2_x += _move
                _p2 = CreatePlayer(_loc.p2_x)
                _gameDraw._pMap.put("player2", _p2)
                break
        }
        _gameDraw.repaint()
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    void keyReleased(KeyEvent e) {

    }

    Shape CreatePlayer(def x) {
        return new Ellipse2D.Double(x, _height-100, 50,50)
    }
}

