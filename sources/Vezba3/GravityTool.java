/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vezba3;

import CH.ifa.draw.figures.LineConnection;
import CH.ifa.draw.standard.CreationTool;
import java.awt.*;
import java.awt.event.MouseEvent;

import CH.ifa.draw.framework.*;
import CH.ifa.draw.standard.ConnectionTool;
import CH.ifa.draw.util.Geom;
import java.util.Enumeration;

/**
 *
 * @author student
 */
public class GravityTool extends CreationTool
{
    private Figure  fCreatedFigure;
    private Point   fAnchorPoint;

    /**
     * the prototypical figure that is used to create new figures.
     */
    private Figure  fPrototype;
    private Figure  fConPrototype;
    
    private ConnectionTool fConTool;// = new ConnectionTool();
    
    private Connector   fStartConnector;
    private Connector   fEndConnector;
    private Connector   fConnectorTarget = null;

    private Figure fTarget = null;

    /**
     * the currently created figure
     */
    private ConnectionFigure  fConnection;

    private int  fSplitPoint;

    /**
     * the currently edited connection
     */
    private ConnectionFigure  fEditedConnection = null;

    
    
    GravityTool(DrawingView view, Figure figure, ConnectionFigure cFig)
    {
        super(view, figure);
        
        //fConTool = new ConnectionTool(view, cFig);
        fConPrototype = cFig;
    }
    
    GravityTool(DrawingView view)
    {
        super(view);
    }
    
    protected Figure findConnectionStart(int x, int y, Drawing drawing) {
        Figure target = findConnectableFigure(x, y, drawing);
        if ((target != null) && target.canConnect())
            return target;
        return null;
    }

    private Figure findConnectableFigure(int x, int y, Drawing drawing) {
        FigureEnumeration k = drawing.figuresReverse();
        while (k.hasMoreElements()) {
            Figure figure = k.nextFigure();
            if (!figure.includes(fConnection) && figure.canConnect()) {
                if (figure.containsPoint(x, y))
                    return figure;
            }
        }
        return null;
    }
    
    // FROM CONNECTION TOOL
//    public void mouseDown(MouseEvent e, int x, int y)
//    {
//        int ex = e.getX();
//        int ey = e.getY();
//        fTarget = findConnectionStart(ex, ey, drawing());
//        if (fTarget != null) {
//            fStartConnector = findConnector(ex, ey, fTarget);
//            if (fStartConnector != null) {
//                Point p = new Point(ex, ey);
//                fConnection = createConnection();
//                fConnection.startPoint(p.x, p.y);
//                fConnection.endPoint(p.x, p.y);
//                view().add(fConnection);
//            }
//        }
//        else {
//            ConnectionFigure connection = findConnection(ex, ey, drawing());
//            if (connection != null) {
//                if (!connection.joinSegments(ex, ey)) {
//                    fSplitPoint = connection.splitSegment(ex, ey);
//                    fEditedConnection = connection;
//                } else {
//                    fEditedConnection = null;
//                }
//            }
//        }
//    }
//
//    /**
//     * Adjust the created connection or split segment.
//     */
//    public void mouseDrag(MouseEvent e, int x, int y) {
//        Point p = new Point(e.getX(), e.getY());
//        if (fConnection != null) {
//            trackConnectors(e, x, y);
//            if (fConnectorTarget != null)
//                p = Geom.center(fConnectorTarget.displayBox());
//            fConnection.endPoint(p.x, p.y);
//        }
//        else if (fEditedConnection != null) {
//            Point pp = new Point(x, y);
//            fEditedConnection.setPointAt(pp, fSplitPoint);
//        }
//    }
//
//    /**
//     * Connects the figures if the mouse is released over another
//     * figure.
//     */
//    public void mouseUp(MouseEvent e, int x, int y) {
//        Figure c = null;
//        if (fStartConnector != null)
//            c = findTarget(e.getX(), e.getY(), drawing());
//
//        if (c != null) {
//            fEndConnector = findConnector(e.getX(), e.getY(), c);
//            if (fEndConnector != null) {
//                fConnection.connectStart(fStartConnector);
//                fConnection.connectEnd(fEndConnector);
//                fConnection.updateConnection();
//            }
//        } else if (fConnection != null)
//            view().remove(fConnection);
//
//        fConnection = null;
//        fStartConnector = fEndConnector = null;
//        editor().toolDone();
//    }
    
    public void mouseDown(MouseEvent e, int x, int y)
    {
        fAnchorPoint = new Point(x,y);
        fCreatedFigure = createFigure();
        fCreatedFigure.displayBox(fAnchorPoint, fAnchorPoint);
        view().add(fCreatedFigure);
        
        
        int ex = e.getX();
        int ey = e.getY();
        Figure fTarget = findConnectionStart(ex, ey, drawing());
        if (fTarget != null) {
            fStartConnector = findConnector(ex, ey, fTarget);
            if (fStartConnector != null) {
                Point p = new Point(ex, ey);
                fConnection = createConnection();
                fConnection.startPoint(p.x, p.y);
                fConnection.endPoint(p.x, p.y);
                view().add(fConnection);
            }
        }
        else {
            ConnectionFigure connection = findConnection(ex, ey, drawing());
            if (connection != null) {
                if (!connection.joinSegments(ex, ey)) {
                    fSplitPoint = connection.splitSegment(ex, ey);
                    fEditedConnection = connection;
                } else {
                    fEditedConnection = null;
                }
            }
        }
        
        //fConnection = new SatelliteConnection();
        //fConnection.basicDisplayBox(fAnchorPoint, fAnchorPoint);
        view().add(fConnection);
    }
    
    public void mouseDrag(MouseEvent e, int x, int y)
    {
        Point p = new Point(e.getX(), e.getY());
        if (fConnection != null) {
            trackConnectors(e, x, y);
            if (fConnectorTarget != null)
                p = Geom.center(fConnectorTarget.displayBox());
            fConnection.endPoint(p.x, p.y);
        }
        else if (fEditedConnection != null) {
            Point pp = new Point(x, y);
            fEditedConnection.setPointAt(pp, fSplitPoint);
        }
        //fCreatedFigure.displayBox(fAnchorPoint, new Point(x,y));
    }

    /**
     * Checks if the created figure is empty. If it is, the figure
     * is removed from the drawing.
     * @see Figure#isEmpty
     */
    public void mouseUp(MouseEvent e, int x, int y) {
        Figure c = null;
        if (fStartConnector != null)
            c = findTarget(e.getX(), e.getY(), drawing());

        if (c != null) {
            fEndConnector = findConnector(e.getX(), e.getY(), c);
            if (fEndConnector != null) {
                fConnection.connectStart(fStartConnector);
                fConnection.connectEnd(fEndConnector);
                fConnection.updateConnection();
            }
        } else if (fConnection != null)
            view().remove(fConnection);

        fConnection = null;
        fStartConnector = fEndConnector = null;
        editor().toolDone();
    }
    
     protected ConnectionFigure createConnection() {
        return (ConnectionFigure)fConPrototype.clone();
    }

    /**
     * Finds a connectable figure target.
     */
    protected Figure findSource(int x, int y, Drawing drawing) {
        return findConnectableFigure(x, y, drawing);
    }

    /**
     * Finds a connectable figure target.
     */
    protected Figure findTarget(int x, int y, Drawing drawing) {
        Figure target = findConnectableFigure(x, y, drawing);
        Figure start = fStartConnector.owner();

        if (target != null
             && fConnection != null
             && target.canConnect()
             && !target.includes(start)
             && fConnection.canConnect(start, target))
            return target;
        return null;
    }

    /**
     * Finds an existing connection figure.
     */
    protected ConnectionFigure findConnection(int x, int y, Drawing drawing) {
        Enumeration k = drawing.figuresReverse();
        while (k.hasMoreElements()) {
            Figure figure = (Figure) k.nextElement();
            figure = figure.findFigureInside(x, y);
            if (figure != null && (figure instanceof ConnectionFigure))
                return (ConnectionFigure)figure;
        }
        return null;
    }

    /**
     * Gets the currently created figure
     */
    protected ConnectionFigure createdFigure() {
        return fConnection;
    }

    protected void trackConnectors(MouseEvent e, int x, int y) {
        Figure c = null;

        if (fStartConnector == null)
            c = findSource(x, y, drawing());
        else
            c = findTarget(x, y, drawing());

        // track the figure containing the mouse
        if (c != fTarget) {
            if (fTarget != null)
                fTarget.connectorVisibility(false);
            fTarget = c;
            if (fTarget != null)
                fTarget.connectorVisibility(true);
        }

        Connector cc = null;
        if (c != null)
            cc = findConnector(e.getX(), e.getY(), c);
        if (cc != fConnectorTarget)
            fConnectorTarget = cc;

        view().checkDamage();
    }

    private Connector findConnector(int x, int y, Figure f) {
        return f.connectorAt(x, y);
    }

    protected Connector getStartConnector() {
        return fStartConnector;
    }

    protected Connector getEndConnector() {
        return fEndConnector;
    }

    protected Connector getTarget() {
        return fConnectorTarget;
    }

}
