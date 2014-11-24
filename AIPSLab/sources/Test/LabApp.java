/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import CH.ifa.draw.application.DrawApplication;
import static CH.ifa.draw.application.DrawApplication.IMAGES;
import CH.ifa.draw.standard.CreationTool;
import CH.ifa.draw.standard.ToolButton;
import java.awt.Panel;

/**
 *
 * @author FallenShard
 */
public class LabApp extends DrawApplication
{
    LabApp(String title)
    {
        super(title);
    }
    
    @Override
    protected void createTools(Panel palette)
    {
        super.createTools(palette);
        
        CreationTool ellipseTool = new CreationTool(view(), new PlanetFigure());
        ToolButton planetButton = new ToolButton(this, IMAGES + "ELLIPSE", "Planet Tool", ellipseTool);
        palette.add(planetButton);
    }
}
