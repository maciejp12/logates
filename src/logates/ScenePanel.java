package logates;

import javax.swing.*;
import java.awt.*;

public class ScenePanel extends JPanel {

    /*
        Scene background color
     */
    private static Color backgroundColor = new Color(100, 100, 100);

    /*
        Default initial x cord of draw panel rectangle
     */
    private static int drawPanelInitX = 0;

    /*
        Default initial y cord of draw panel rectangle
     */
    private static int drawPanelInitY = 0;

    /*
        Default initial width of draw panel rectangle
     */
    private static int drawPanelInitWidth = 1280;

    /*
       Default initial height of draw panel rectangle
    */
    private static int drawPanelInitHeight = 600;

    /*
        Main panel of work area, handles mouse and keyboard input,
        draws all scene objects, can be moved inside drawPanelContainer and
        resized
     */
    protected DrawPanel drawPanel;

    /*
        Panel handling  base tools selection
     */
    protected ToolPanel toolPanel;

    /*
        Utility bottom panel, contains information of currently selected
        object, tool and mouse position
     */
    protected UtilityPanel utilityPanel;

    /*
        Container panel of main draw panel
     */
    protected JPanel drawPanelContainer;

    private static ScenePanel instance = new ScenePanel();

    private ScenePanel() {
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        toolPanel = new ToolPanel();
        drawPanel = new DrawPanel();
        utilityPanel = new UtilityPanel();

        drawPanelContainer = new JPanel();
        drawPanelContainer.setBackground(backgroundColor);
        add(drawPanelContainer, BorderLayout.CENTER);
        drawPanelContainer.setLayout(null);
        drawPanelContainer.add(drawPanel);
        drawPanel.setBounds(drawPanelInitX, drawPanelInitY, drawPanelInitWidth,
                            drawPanelInitHeight);

        add(toolPanel, BorderLayout.NORTH);
        add(utilityPanel, BorderLayout.SOUTH);
    }

    public static ScenePanel getInstance() {
        return instance;
    }
}
