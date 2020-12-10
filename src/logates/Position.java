package logates;

public class Position {

    /*
        Left bound of position rectangle
     */
    private int x;

    /*
        Top bound of position rectangle
     */
    private int y;

    /*
        Width of position rectangle
     */
    private int width;

    /*
        Height of position rectangle
     */
    private int height;

    /*
        Minimum width of rectangle possible
     */
    private static int minWidth = 16;

    /*
        Minimum height of rectangle possible
     */
    private static int minHeight = 16;

    /*
        Default value of resizing rectangle horizontally
     */
    private static int resizeX = 10;

    /*
        Default value of resizing rectangle vertically
     */
    private static int resizeY = 10;

    /*
        Default initial width of rectangle
     */
    private static int initWidth = 32;

    /*
        Default initial height of rectangle
     */
    private static int initHeight = 32;

    public Position(int newX, int newY, int wid, int hei) {
        width = wid;
        height = hei;
        x = newX;
        y = newY;
    }

    public Position(int newX, int newY) {
        this(newX, newY, initWidth, initHeight);
    }

    /*
        Returns true is position rectangle contains (cx +- dx, cy +- dy) point
        else returns false
     */
    public boolean contains(int cx, int cy, int dx, int dy) {
        return cx >= x - dx && cx <= x + width + dx &&
               cy >= y - dy && cy <= y + height + dy;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    /*
        Set position rectangle width, if newWidth parameter is lower than
        minimum width then set position rectangle width to minimum width
     */
    public void setWidth(int newWidth) {
        if (newWidth < minWidth) {
            width = minWidth;
            return;
        }
        width = newWidth;
    }

    /*
        Set position rectangle height, if newHeight parameter is lower than
        minimum height then set position rectangle height to minimum height
     */
    public void setHeight(int newHeight) {
        if (newHeight < minHeight) {
            height = minHeight;
            return;
        }
        height = newHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addWidth() {
        addWidth(resizeX);
    }

    public void removeWidth() {
        addWidth((-1) * resizeX);
    }

    public void addWidth(int value) {
        setWidth(width + value);
    }

    public void addHeight() {
        addHeight(resizeY);
    }

    public void removeHeight() {
        addHeight((-1) * resizeY);
    }

    public void addHeight(int value) {
        setHeight(height + value);
    }

    public static int getMinWidth() {
        return minWidth;
    }

    public static int getMinHeight() {
        return minHeight;
    }
}
