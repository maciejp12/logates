package logates;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScenePanel sp = ScenePanel.getInstance();
        frame.getContentPane().add(sp);
        frame.setTitle("logates");
        frame.setVisible(true);

    }
}
