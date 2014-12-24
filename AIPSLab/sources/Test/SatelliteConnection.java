/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import CH.ifa.draw.figures.LineConnection;
import CH.ifa.draw.framework.Figure;

/**
 *
 * @author FallenShard
 */
public class SatelliteConnection extends LineConnection
{
    SatelliteConnection()
    {
        super();
        setStartDecoration(null);
    }
    
    @Override
    public boolean canConnect(Figure start, Figure end)
    {
        if (start instanceof SatelliteFigure && end instanceof PlanetFigure)
            return true;
        
        return start instanceof PlanetFigure && end instanceof PlanetFigure;
    }
}
