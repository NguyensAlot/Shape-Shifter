/**
 * Created by Anthony on 5/26/2016.
 * Description: This class is the main graphics renderer which will draw all the shapes needed for the game
 */


import javax.swing.JComponent
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class MainDraw extends JComponent {
    // use a map to easily find and adjust shapes
    Map _pMap

    /**
     * Default constructor, set some properties
     *
     * */
    MainDraw() {
        _pMap = new HashMap<String, GameObject>()
    }

    /**
     * Overridden method to handle graphic rendering
     *
     * */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g
        g2.setColor(Color.BLACK)

        // closure with loop to draw all shapes
        _pMap.each { key, val ->
            g2.setColor(val._color)
            g2.draw(val._shape)
        }
    }
}
