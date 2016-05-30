/**
 * Created by Anthony on 5/26/2016.
 */


import javax.swing.JComponent
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Shape

class MainDraw extends JComponent {
    Map _pMap

    MainDraw() {
        _pMap = new HashMap<String, Shape>()
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g
        g2.setColor(Color.BLACK)

        _pMap.each { key, val ->
            g2.draw(val)
        }
    }
}
