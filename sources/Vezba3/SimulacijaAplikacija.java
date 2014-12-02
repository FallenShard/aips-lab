/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vezba3;

import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.standard.*;
import CH.ifa.draw.figures.*;
import CH.ifa.draw.framework.Tool;
import java.awt.Panel;
/**
 *
 * @author vladan
 */
public class SimulacijaAplikacija extends DrawApplication{

    @Override
    protected void createTools(Panel palette) {
        super.createTools(palette);
        //CreationTool ellipseTool = new CreationTool(view(), new EllipseFigure());
        CreationTool ellipseTool = new CreationTool(view(), new Planet());
        ToolButton ellipseButton = new ToolButton(this, IMAGES+"ELLIPSE", "Ellipse Tool", ellipseTool);
        palette.add(ellipseButton);
        CreationTool satelliteTool = new GravityTool(view(), new SatelliteFigure(), new SatelliteConnection());
        ToolButton satelliteButton = new ToolButton(this, IMAGES+"SATELLITE", "Satellite Tool", satelliteTool);
        palette.add(satelliteButton); 
        ActionTool atmosphereTool = new AtmosphereTool(view());
        ToolButton atmosphereButton = new ToolButton(this, IMAGES+"ATMOSPHERE", "Atmosphere Tool", atmosphereTool);
        palette.add(atmosphereButton); 
        
        ConnectionTool connTool = new ConnectionTool(view(), new SatelliteConnection());
        ToolButton connectButton = new ToolButton(this, IMAGES+"LINE", "Line Tool", connTool);
        palette.add(connectButton);
        
        Tool tool = new ConnectedTextTool(view(), new TextFigure());
        ToolButton connTextButton =createToolButton(IMAGES + "ATEXT", "Connected Text Tool", tool);
        palette.add(connTextButton);
    }
}
