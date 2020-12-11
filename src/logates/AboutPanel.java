package logates;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AboutPanel extends JPanel {

    /*
        Text area containing about text
     */
    private JTextArea aboutTextArea;

    /*
        Path to file with about text
     */
    private static final String aboutFilePath = "./resources/abouttext.txt";

    /*
        Type of about text font
     */
    private static final String aboutTextFont = "Dialog";

    /*
        Size of about text font
     */
    private static final int aboutTextFontSize = 15;

    public AboutPanel() {
        setLayout(new BorderLayout());

        aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setFont(new Font(aboutTextFont, Font.PLAIN, aboutTextFontSize));

        loadAboutText(aboutFilePath);
        add(aboutTextArea, BorderLayout.CENTER);
    }

    /*
        Load text from file path argument and set aboutTextArea text with loaded
        text
     */
    public void loadAboutText(String filePath) {
        String aboutText = "";
        try {
            File aboutFileText = new File(filePath);
            Scanner reader = new Scanner(aboutFileText);
            while (reader.hasNextLine()) {
                aboutText += reader.nextLine() + "\n";
            }
            reader.close();
        } catch (FileNotFoundException e) {
            aboutText = "error while loading a file";
        }
        aboutTextArea.setText(aboutText);
    }
}
