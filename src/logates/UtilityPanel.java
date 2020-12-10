package logates;

import javax.swing.*;
import java.awt.*;

public class UtilityPanel extends JPanel {

    /*
        Background color of panel
     */
    private static Color backgroundColor = new Color(199, 199, 199);

    /*
        Base text foreground color
     */
    private static Color textColor = new Color(0, 0, 0);

    /*
        Text value of selected info in no object is selected
     */
    private static String noneSelected = "nothing selected";

    /*
        Text value of selected tool info if no tool is selected
     */
    private static String noToolSelected = "no tool selected";

    /*
        Default initial mousePosition text value before first mouse movement
     */
    private static String initMousePosString = "( , )";

    /*
        Displays information of current mouse position on draw panel
     */
    protected JLabel mousePosition;

    /*
        Displays information of currently selected tool
     */
    protected JLabel currentTool;

    /*
        Displays information of currently selected object
     */
    protected JLabel selectedInfo;

    public UtilityPanel() {
        setBackground(backgroundColor);

        mousePosition = new JLabel(initMousePosString);
        selectedInfo = new JLabel(noneSelected);
        currentTool = new JLabel(noToolSelected);

        mousePosition.setForeground(textColor);
        currentTool.setForeground(textColor);
        selectedInfo.setForeground(textColor);

        setLayout(new GridLayout(1, 3));

        add(mousePosition);
        add(currentTool);
        add(selectedInfo);
    }

    /*
        Update current mouse position information after mouse movement event
        width x, y cords
     */
    protected void updateMousePosition(int x, int y) {
        String xAsString = String.valueOf(x);
        String yAsString = String.valueOf(y);
        mousePosition.setText("(" + xAsString + " , " + yAsString + ")");
    }

    /*
        Update currently selected object information

        If no object is selected (so is null) set selectedInfo text to
        noneSelected string value
     */
    protected void updateSelectedInfo(SceneObject so) {
        if (so == null) {
            selectedInfo.setText(noneSelected);
        } else {
            String s = "selected object : ";
            s += so.getClass().getSimpleName() + " at (";
            s += so.getPosition().getX() + " , ";
            s += so.getPosition().getY() + ")";
            selectedInfo.setText(s);
        }
    }

    protected void updateCurrentTool(SceneObject so) {
        if (so == null) {
            currentTool.setText(noToolSelected);
        } else {
            String tool = so.getClass().getSimpleName();
            String s = "current tool : " + tool;
            currentTool.setText(s);
        }
    }
}
