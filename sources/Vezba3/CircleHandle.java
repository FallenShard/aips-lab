/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vezba3;

import java.awt.*;
import CH.ifa.draw.standard.LocatorHandle;
import CH.ifa.draw.framework.*;

/**
 *
 * @author vladan
 */
public class CircleHandle extends LocatorHandle {

    public CircleHandle (Figure owner, Locator l) {
        super(owner, l);
    }

    @Override
    public void invokeStep(int x, int y, int anchorX, int anchorY, DrawingView view) {
        //super.invokeStep(x, y, anchorX, anchorY, view);
     
        Rectangle rect = owner().displayBox();
        Point center = new Point(rect.x + (rect.width / 2), rect.y + (rect.height / 2));
        int radius = Math.max(Math.abs(x-center.x), Math.abs(y-center.y));

        if (radius > 60)
            radius = 60;

        rect.x = center.x - radius;
        rect.y = center.y - radius;
        rect.width = 2 * radius;
        rect.height = 2 * radius;
        
        owner().displayBox(rect);
       
    }

}
