/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import CH.ifa.draw.figures.EllipseFigure;
import CH.ifa.draw.figures.GroupFigure;
import CH.ifa.draw.figures.RectangleFigure;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author FallenShard
 */
public class SatelliteFigure extends GroupFigure
{

    public SatelliteFigure() {
        super();
        
        RectangleFigure r1 = new RectangleFigure(new Point(3,0), new Point(8,10));
        EllipseFigure e1 = new EllipseFigure(new Point(0,10), new Point(10,20));
        RectangleFigure r2 = new RectangleFigure(new Point(3,20), new Point(8,30));
        
        r1.setAttribute("FillColor", Color.BLUE);
        e1.setAttribute("FillColor", Color.YELLOW);
        r2.setAttribute("FillColor", Color.BLUE);
        
        super.add(r1);
        super.add(e1);
        super.add(r2);
    }

    @Override
    public void basicDisplayBox(Point origin, Point corner)
    {
        
        Rectangle r = displayBox();
        
        basicMoveBy((int)corner.getX()-r.width/2, (int)origin.getY()-r.height/2);
    }
    
    public boolean canConnect()
    {
        return true;
    }
}
