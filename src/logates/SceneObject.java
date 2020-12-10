package logates;

import java.awt.Graphics;
import java.util.List;

public abstract class SceneObject {

    protected Position position;

    protected SceneObjectImage image;

    /*
        Current state of object rotation on scene
     */
    protected int rotation;

    /*
        Number of diffrent rotation tyoes
     */
    protected static int rotationStates = 4;

    /*
        Delay time in ms after updating active state of scene object
     */
    public static int updateDelayTime = 10;

    /*
        Update active state of scene object
     */
    public abstract void updateState(boolean state);

    /*
        Returns true is position rectangle contains (x +- dx, y +- dy) point
        else returns false
     */
    public boolean contains(int x, int y, int dx, int dy) {
        return position.contains(x, y, dx, dy);
    }

    /*
        Returns true is position rectangle contains (x, y) point
        else returns false
     */
    public boolean contains(int x, int y) {
        return contains(x, y, 0, 0);
    }

    public boolean inBounds(int x, int y, int dx, int dy) {
        return position.contains(x, y, dx, dy);
    }

    /*
        Change current rotation state to next rotation state
     */
    public void rotate() {

    }

    /*
        Update position of ports after moving or rotation object
     */
    public void updatePortsPosition() {

    }

    /*
        Toggles scene object's activity
     */
    public void toggle() {

    }

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(Graphics g) {
        if (image != null) {
            image.draw(g, position.getX(), position.getY(), position.getWidth(),
                       position.getHeight());
        }
    }

    public void addToList(List<SceneObject> sceneObjects) {
        sceneObjects.add(this);
    }

    public void removeFromList(List<SceneObject> sceneObjects) {
        sceneObjects.remove(this);
    }

    protected void repaintScene() {
        ScenePanel.getInstance().drawPanel.repaint();
    }

}
