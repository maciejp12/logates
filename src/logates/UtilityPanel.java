package logates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtilityPanel extends JPanel implements ActionListener  {

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

    /*
        Sets position of main draw panel to 0, 0 (top left corner)
     */
    protected JButton resetView;


    public UtilityPanel() {
        setBackground(backgroundColor);
        setLayout(new GridLayout(1, 4, 8, 8));

        resetView = new JButton("reset view");
        mousePosition = new JLabel(initMousePosString);
        selectedInfo = new JLabel(noneSelected);
        currentTool = new JLabel(noToolSelected);

        decorateButton(resetView);

        mousePosition.setForeground(textColor);
        currentTool.setForeground(textColor);
        selectedInfo.setForeground(textColor);

        add(mousePosition);
        add(currentTool);
        add(selectedInfo);
    }


    private void decorateButton(JButton b) {
        b.setFocusable(false);
        b.addActionListener(this);
        b.setBackground(ToolPanel.buttonBackground);
        add(b);
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

    /*
        Update currently selected tool information

        If no tool is selected (so is null) set currentTool text to
        noToolSelected string value
     */
    protected void updateCurrentTool(SceneObject so) {
        if (so == null) {
            currentTool.setText(noToolSelected);
        } else {
            String tool = so.getClass().getSimpleName();
            String s = "current tool : " + tool;
            currentTool.setText(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == resetView) {
            getScenePanel().resetView();
        }
    }

    public ScenePanel getScenePanel() {
        return (ScenePanel) getParent();
    }
}
