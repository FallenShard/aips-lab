/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vezba3;

import CH.ifa.draw.figures.LineConnection;
import CH.ifa.draw.framework.Figure;

/**
 *
 * @author student
 */
public class SatelliteConnection extends LineConnection
{
    SatelliteConnection()
    {
        super();
        setStartDecoration(null);
    }
    
    public boolean canConnect(Figure start, Figure end)
    {
        if (start instanceof SatelliteFigure && end instanceof Planet)
            return true;
        
        if (start instanceof Planet && start instanceof Planet)
            return true;
        
        return false;
    }
}
