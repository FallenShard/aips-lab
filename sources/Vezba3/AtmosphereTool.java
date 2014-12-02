/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vezba3;

import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.standard.ActionTool;

import java.awt.*; 
/**
 *
 * @author Bratislav Predic
 */
public class AtmosphereTool extends ActionTool{

    public AtmosphereTool(DrawingView itsView) {
        super(itsView);
    }

    @Override
    public void action(Figure figure) {
        if (figure instanceof Planet){
            drawing().replace(figure, new AtmosphereDecorator(figure, Color.BLUE));
        } else if (figure instanceof AtmosphereDecorator) {
            drawing().replace(figure, ((AtmosphereDecorator)figure).peelDecoration());
        }
    }

}
