package logates;

import logates.gates.Gate;
import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /*
        Number of pixels added to objects mouse drag rectangle
     */
    private static int mouseDragGap = 5;

    /*
        Color of panel background
     */
    private static Color backgroundColor = new Color(255, 255, 255);

    /*
        Color of frame around currently selected object
     */
    private static Color selectedFrameColor = new Color(16, 16, 224, 128);

    /*
        Thickness of frame bounds around currently selected objec in pixels
     */
    private static int selectedFrameThickness = 2;

    /*
        Color of grid lines on the background of panel
     */
    private static Color gridLinesColor = new Color(200, 200, 200);

    /*
        Thickness of background grid lines
     */
    private static int gridLinesThickness = 1;

    /*
        All scene objects displayed on the panel
     */
    public List<SceneObject> sceneObjects;

    /*
        Currently selected object
     */
    private SceneObject selected;

    /*
        Object to be added to scene by clicking after selecting its type
     */
    public SceneObject newSceneObject;

    /*
        True if currently dragging object with mouse
     */
    private boolean dragging;

    /*
        True if currently moving scene view with mouse
    */
    private boolean movingView;

    /*
        True if currently resizing scene view with mouse
     */
    private boolean resizingView;

    /*
        Current x cord of mouse position if mouse is dragged
     */
    private int dragX;

    /*
        Current x cord of mouse position if mouse is dragged
     */
    private int dragY;

    /*
        MouseEvent of last right mouse press action
     */
    private MouseEvent rightPress;

    /*
        True if currently drawing grid on scene
     */
    private boolean drawGrid;


    public DrawPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        drawGrid = true;
        dragging = false;
        movingView = false;
        dragX = 0;
        dragY = 0;
        sceneObjects = new ArrayList<SceneObject>();

        setBackground(backgroundColor);
    }

    /*
        Return parent scene panel
     */
    protected ScenePanel getScenePanel() {
        ScenePanel parent;
        parent = (ScenePanel) getParent().getParent();
        return parent;
    }

    /*
        Move current view to e mouse event location
     */
    protected void moveView(MouseEvent e) {
        Point p = getLocation();
        int x = p.x - rightPress.getX() + e.getX();
        int y = p.y - rightPress.getY() + e.getY();
        setLocation(x, y);;
    }

    /*
        Resize current view
     */
    protected void resizeView(MouseEvent e) {
        Rectangle r = getBounds();
        Point p = getLocation();
        int maxGap = 5;

        if (e.getX() <= maxGap) {
            setBounds(r.x + e.getX(), r.y, r.width - e.getX(), r.height);
        }

        if (e.getX() >= r.width - maxGap) {
            setBounds(r.x, r.y, e.getX(), r.height);
        }

        if (e.getY() <= maxGap) {
            setBounds(r.x, r.y + e.getY(), r.width, r.height - e.getY());
        }

        if (e.getY() >= r.height - maxGap) {
            setBounds(r.x, r.y, r.width, e.getY());
        }
    }

    protected void drawGridLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(gridLinesThickness));

        g2.setColor(gridLinesColor);

        int hGap = 32;
        int vGap = 32;
        int wid = getBounds().width;
        int hei = getBounds().height;

        for(int i = 0; i < hei; i += hGap) {
            g2.drawLine(0, i, wid, i);
        }

        for(int i = 0; i < wid; i += vGap) {
            g2.drawLine(i, 0, i, hei);
        }
    }

    protected void addNewObj(MouseEvent e) {
        newSceneObject.setPosition(e.getX(), e.getY());
        newSceneObject.updatePortsPosition();
        newSceneObject.addToList(sceneObjects);
        newSceneObject = null;
        getScenePanel().utilityPanel.updateCurrentTool(newSceneObject);
    }

    /*
        Remove selected object from scene
     */
    protected void removeSelected() {
        selected.removeFromList(sceneObjects);
        selected = null;
    }

    /*
        Return currently selected object
     */
    public void cancelSelection() {
        selected = null;
        getScenePanel().utilityPanel.updateSelectedInfo(selected);
        getScenePanel().toolPanel.updateSelected(selected);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawGrid) {
            drawGridLines(g);
        }

        for (SceneObject sceneObject : sceneObjects) {
            sceneObject.draw(g);
        }

        if (selected != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(selectedFrameThickness));

            g2.setColor(selectedFrameColor);
            Position sp = selected.position;
            g2.drawRect(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (newSceneObject != null) {
                addNewObj(e);
            } else {
                boolean found = false;
                for (SceneObject s : sceneObjects) {
                    if (s.contains(e.getX(), e.getY())) {
                        if (selected != null) {
                            if (s == selected) {
                                dragging = true;
                                dragX = e.getX() - selected.getPosition().getX();
                                dragY = e.getY() - selected.getPosition().getY();
                                found = true;
                                break;
                            }
                        }
                        selected = s;
                        repaint();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    selected = null;
                    Rectangle r = getBounds();

                    if (e.getX() <= mouseDragGap) {
                        resizingView = true;
                    }

                    if (e.getX() >= r.width - mouseDragGap) {
                        resizingView = true;
                    }

                    if (e.getY() <= mouseDragGap) {
                        resizingView = true;
                    }

                    if (e.getY() >= r.height - mouseDragGap) {
                        resizingView = true;
                    }

                    repaint();
                }

                getScenePanel().utilityPanel.updateSelectedInfo(selected);
                getScenePanel().toolPanel.updateSelected(selected);
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            rightPress = e;
            boolean found = false;
            for(SceneObject s : sceneObjects) {
                if (s.contains(e.getX(), e.getY())) {
                    s.toggle();
                    repaint();
                    found = true;
                    break;
                }
            }

            if (!found) {
                movingView = true;
                repaint();
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            boolean found = false;

            if (selected != null) {
                if (selected instanceof InPort) {
                   found = Port.checkConnections(e.getX(), e.getY(), mouseDragGap,
                           Port.getAllOutPorts(sceneObjects), (Port) selected);
                }

                if (selected instanceof OutPort) {
                    found = Port.checkConnections(e.getX(), e.getY(), mouseDragGap,
                            Port.getAllInPorts(sceneObjects), (Port) selected);
                }

                if (!found) {
                    if (selected instanceof Port) {
                        ((Port) selected).disconnect();
                    }
                }
            }

            dragging = false;
            resizingView = false;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            movingView = false;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            getScenePanel().utilityPanel.updateMousePosition(e.getX(),
                                                             e.getY());
            if (selected != null) {
                if (dragging) {
                    selected.setPosition(e.getX() - dragX, e.getY() - dragY);
                    selected.updatePortsPosition();
                    repaint();
                }
            }

            getScenePanel().utilityPanel.updateSelectedInfo(selected);
            getScenePanel().toolPanel.updateSelected(selected);

            if (resizingView) {
                resizeView(e);
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (movingView) {
                moveView(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getScenePanel().utilityPanel.updateMousePosition(e.getX(), e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r') {
            if (selected != null) {
                selected.rotate();
                repaint();
            }
        } else if (e.getKeyChar() == 'g') {
            drawGrid = !drawGrid;
            repaint();
        } else if (e.getKeyChar() == 'd') {
            if (selected != null) {
                removeSelected();
                getScenePanel().utilityPanel.updateSelectedInfo(selected);
                getScenePanel().toolPanel.updateSelected(selected);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
