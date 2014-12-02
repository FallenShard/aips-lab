/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vezba3;

import CH.ifa.draw.standard.DecoratorFigure;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.framework.FigureChangeEvent;

import java.awt.*;

/**
 *
 * @author Bratislav Predic
 */
public class AtmosphereDecorator extends DecoratorFigure{

    public static final int XMARGIN = 2;
    public static final int YMARGIN = 2;
    
    private Color colour;
    
    public AtmosphereDecorator(Color newColor){
        colour = newColor;
    }

    public AtmosphereDecorator(Figure figure, Color newColour) {
        super(figure);
        colour = newColour;
    }

    @Override
    public Rectangle displayBox() {
        Rectangle r = fComponent.displayBox();
        r.grow(XMARGIN, YMARGIN);
        return r;
    }

    @Override
    public void draw(Graphics g) {
        Rectangle r = displayBox();
        super.draw(g);
        g.setColor(colour);
        g.drawOval(r.x, r.y, r.width, r.height);
    }

    @Override
    public void figureInvalidated(FigureChangeEvent e) {
        Rectangle rect = e.getInvalidatedRectangle();
        rect.grow(XMARGIN, YMARGIN);
        super.figureInvalidated(new FigureChangeEvent(e.getFigure(), rect));
    }
    
    

}
