/**
 * Created by Anthony on 5/26/2016.
 * Description: This class is the main graphics renderer which will draw all the shapes needed for the game
 */


import javax.swing.JComponent
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class MainDraw extends JComponent {
    Map _pMap

    MainDraw() {
        _pMap = new HashMap<String, GameObject>()
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g
        g2.setColor(Color.BLACK)

        _pMap.each { key, val ->
            g2.setColor(val._color)
            g2.draw(val._shape)
        }
    }
}
