/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vezba3;

import CH.ifa.draw.figures.*;
import CH.ifa.draw.standard.*;
import java.awt.Color;
import java.util.Vector;

import java.awt.*;

/**
 *
 * @author vladan
 */
public class Planet extends EllipseFigure {

    @Override
    public Dimension size() {
        return super.size();
         /*
         Rectangle rect = displayBox();
        
        int size = Math.max(rect.width, rect.height);

        if (size < 120) {
            displayBox(rect);
        }
        return super.size();
        */
    }

    @Override
    public Vector handles() {
        //return super.handles();
        Vector handles = new Vector();
        handles.addElement(new CircleHandle(this, RelativeLocator.northWest()));
        handles.addElement(new CircleHandle(this, RelativeLocator.northEast()));
        handles.addElement(new CircleHandle(this, RelativeLocator.southWest()));
        handles.addElement(new CircleHandle(this, RelativeLocator.southEast()));
        return handles;
    }

    @Override
    public Color getFillColor() {
        //return super.getFillColor();

        Rectangle rect = displayBox();
        
        int size = Math.max(rect.width, rect.height);
        
        if (size < 40) {
            return Color.red;
        } else if (size < 80) {
            return Color.orange;
        } else {
            return Color.yellow;
        }
    }

    @Override
    public void basicDisplayBox(Point origin, Point corner)
    {
        int width = corner.x - origin.x;
        int height = corner.y - origin.y;
        int size;
        if (width > 0) 
            size = Math.min(120, Math.max(width, height));
        else
            size = Math.max(-120, Math.min(width, height));
        
        corner = new Point(origin.x + size, origin.y + size);
        
        int absWidth = Math.abs(width);
        if (absWidth > 100)
            setAttribute("FillColor", new Color(255, 255, 0));
        else if (absWidth > 80)
            setAttribute("FillColor", new Color(255, 127, 0));
        else
            setAttribute("FillColor", new Color(255,   0, 0));
        
        super.basicDisplayBox(origin, corner);
    }

    

}
