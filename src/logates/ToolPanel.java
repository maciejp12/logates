package logates;

import logates.sceneobject.Position;
import logates.sceneobject.SceneObject;
import logates.sceneobject.gates.*;
import logates.sceneobject.gates.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.List;


public class ToolPanel extends JPanel implements ActionListener {

    private JButton addCable;

    private JButton addAndGate;

    private JButton addOrGate;

    private JButton addNotGate;

    private JButton addNandGate;

    private JButton addNorGate;

    private JButton addXorGate;

    private JButton addNxorGate;

    private JButton addRepeater;

    private JButton addButton;

    private JButton addLamp;

    private JButton resetTool;

    private int toolButtonWidth;

    private int toolButtonHeight;

    private JPanel globalTools;

    private JPanel selectedTools;

    private JPanel specialPanel;

    private JButton addInPort;

    private JButton removeInPort;

    private JButton addOutPort;

    private JButton removeOutPort;

    private JButton addWidth;

    private JButton removeWidth;

    private JButton addHeight;

    private JButton removeHeight;

    private JButton rotateSelected;

    private JButton removeSelected;

    private JButton unselect;


    public static Color buttonBackground = new Color(224, 224, 224);

    private SceneObject selectedObject;

    public ToolPanel() {
        setBackground(new Color(244, 244, 244));
        setLayout(new GridLayout(1, 3, 8, 8));

        globalTools = new JPanel();
        globalTools.setLayout(new GridLayout(2, 6, 8, 8));

        selectedTools = new JPanel();
        selectedTools.setLayout(new GridLayout(2, 6, 8, 8));

        specialPanel = new JPanel();

        toolButtonWidth = 16;
        toolButtonHeight = 16;

        addCable = new JButton("cable");
        addAndGate = new JButton("and_gate");
        addOrGate = new JButton("or_gate");
        addNotGate = new JButton("not_gate");
        addNandGate = new JButton("nand_gate");
        addNorGate = new JButton("nor_gate");
        addXorGate = new JButton("xor_gate");
        addNxorGate = new JButton("nxor_gate");
        addRepeater = new JButton("repeater");
        addButton = new JButton("button");
        addLamp = new JButton("lamp");
        resetTool = new JButton("cancel");

        addInPort = new JButton("add in port");
        removeInPort = new JButton("remove in port");
        addOutPort = new JButton("add out port");
        removeOutPort = new JButton("remove out port");

        addWidth = new JButton("add width");
        removeWidth = new JButton("remove width");
        addHeight = new JButton("add height");
        removeHeight = new JButton("remove height");

        rotateSelected = new JButton("rotate selected");
        removeSelected = new JButton("remove selected");

        unselect = new JButton("unselect");

        decorateButton(addCable, globalTools);
        decorateButton(addAndGate, globalTools);
        decorateButton(addOrGate, globalTools);
        decorateButton(addNotGate, globalTools);
        decorateButton(addNandGate, globalTools);
        decorateButton(addNorGate, globalTools);
        decorateButton(addXorGate, globalTools);
        decorateButton(addNxorGate, globalTools);
        decorateButton(addRepeater, globalTools);
        decorateButton(addButton, globalTools);
        decorateButton(addLamp, globalTools);
        decorateButton(resetTool, globalTools);

        decorateButton(addInPort, selectedTools);
        decorateButton(removeInPort, selectedTools);
        decorateButton(addOutPort, selectedTools);
        decorateButton(removeOutPort, selectedTools);

        decorateButton(addWidth, selectedTools);
        decorateButton(removeWidth, selectedTools);
        decorateButton(addHeight, selectedTools);
        decorateButton(removeHeight, selectedTools);

        decorateButton(rotateSelected, selectedTools);
        decorateButton(removeSelected, selectedTools);

        decorateButton(unselect, selectedTools);

        add(globalTools);
        add(selectedTools);
        //add(specialPanel);
    }

    /*
        Load image to button b based on its text and remove its previous text
        If there is no image in resources found then leave the previous text

        Add action listener to button b
        Add button b to panel argument

        If panel argument is selected tools panel then set button disabled
     */
    protected void decorateButton(JButton b, JPanel panel) {
        b.setFocusable(false);
        b.addActionListener(this);
        try {
            String imgPath = "./resources/" + b.getText() + ".png";;
            Image image = ImageIO.read(new File(imgPath));
            Image iconImage = image.getScaledInstance(toolButtonWidth,
                                                      toolButtonHeight,
                                                      Image.SCALE_DEFAULT);
            b.setIcon(new ImageIcon(iconImage));
            b.setText("");
        } catch (Exception e) {

        }
        b.setPreferredSize(new Dimension(toolButtonWidth, toolButtonHeight));
        b.setBackground(buttonBackground);
        if (panel == selectedTools) {
            b.setEnabled(false);
        }
        panel.add(b);
    }

    public ScenePanel getScenePanel() {
        return (ScenePanel) getParent();
    }

    /*
        Set draw panel new object to be added to object type selected by button
     */
    public void setNewSceneObject(SceneObject s) {
        getScenePanel().drawPanel.newSceneObject = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SceneObject createdObject = null;
        if (e.getSource() == addCable) {
            createdObject = new Cable();
        } else if (e.getSource() == addAndGate) {
            createdObject = new AndGate();
        } else if (e.getSource() == addOrGate) {
            createdObject = new OrGate();
        } else if (e.getSource() == addNotGate) {
            createdObject = new NotGate();
        } else if (e.getSource() == addNandGate) {
            createdObject = new NandGate();
        } else if (e.getSource() == addNorGate) {
            createdObject = new NorGate();
        } else if (e.getSource() == addXorGate) {
            createdObject = new XorGate();
        } else if (e.getSource() == addNxorGate) {
            createdObject = new NxorGate();
        } else if (e.getSource() == addRepeater) {
            createdObject = new Repeater();
        } else if (e.getSource() == addButton) {
            createdObject = new Button();
        } else if (e.getSource() == addLamp) {
            createdObject = new Lamp();
        } else if (e.getSource() == resetTool) {
            setNewSceneObject(null);
            getUtilityPanel().updateCurrentTool(null);
        } else if (e.getSource() == addInPort) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    ((Gate) selectedObject).addNewInPort();
                }
            }
        } else if (e.getSource() == removeInPort) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    ((Gate) selectedObject).removeInPort(getSceneObjectsList());
                }
            }
        } else if (e.getSource() == addOutPort) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    ((Gate) selectedObject).addNewOutPort();
                }
            }
        } else if (e.getSource() == removeOutPort) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    ((Gate) selectedObject).removeOutPort(getSceneObjectsList());
                }
            }
        } else if (e.getSource() == addWidth) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    selectedObject.getPosition().addWidth();
                    ((Gate) selectedObject).updatePortsPosition();
                }
            }
        } else if (e.getSource() == removeWidth) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    selectedObject.getPosition().removeWidth();
                    ((Gate) selectedObject).updatePortsPosition();
                }
            }
        } else if (e.getSource() == addHeight) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    selectedObject.getPosition().addHeight();
                    ((Gate) selectedObject).updatePortsPosition();
                }
            }
        } else if (e.getSource() == removeHeight) {
            if (selectedObject != null) {
                if (selectedObject instanceof Gate) {
                    selectedObject.getPosition().removeHeight();
                    ((Gate) selectedObject).updatePortsPosition();
                }
            }
        } else if (e.getSource() == rotateSelected) {
            if (selectedObject != null) {
                selectedObject.rotate();
            }
        } else if (e.getSource() == removeSelected) {
            if (selectedObject != null) {
                getScenePanel().drawPanel.removeSelected();
                getScenePanel().utilityPanel.updateSelectedInfo(null);
                getScenePanel().toolPanel.updateSelected(null);
                getScenePanel().drawPanel.repaint();
            }
        } else if (e.getSource() == unselect) {
            if (selectedObject != null) {
                getScenePanel().drawPanel.cancelSelection();
            }
        }

        if (createdObject != null) {
            setNewSceneObject(createdObject);
            getUtilityPanel().updateCurrentTool(createdObject);
        }

        updateSelected(selectedObject);
        getScenePanel().drawPanel.repaint();
    }

    /*
        Enable all possible tools to use based on currently selected object
        If no object is selected disable all selected panel tools
     */
    public void updateSelected(SceneObject so) {
        selectedObject = so;
        addInPort.setEnabled(false);
        removeInPort.setEnabled(false);
        addOutPort.setEnabled(false);
        removeOutPort.setEnabled(false);
        addWidth.setEnabled(false);
        removeWidth.setEnabled(false);
        addHeight.setEnabled(false);
        removeHeight.setEnabled(false);
        rotateSelected.setEnabled(false);
        removeSelected.setEnabled(false);
        unselect.setEnabled(false);

        if (selectedObject != null) {
            removeSelected.setEnabled(true);
            rotateSelected.setEnabled(true);
            unselect.setEnabled(true);

            if (selectedObject instanceof Gate) {
                Gate selectedGate = (Gate) selectedObject;
                int inPortsSize = selectedGate.inPorts.size();
                int outPortsSize = selectedGate.outPorts.size();

                int minIn = selectedGate.getMinInPorts();
                int maxIn = selectedGate.getMaxInPorts();
                int minOut = selectedGate.getMinOutPorts();
                int maxOut = selectedGate.getMaxOutPorts();

                boolean canAddInPort = inPortsSize + 1 <= maxIn;
                boolean canRemoveInPort = inPortsSize - 1 >= minIn;
                boolean canAddOutPort = outPortsSize + 1 <= maxOut;
                boolean canRemoveOutPort = outPortsSize - 1 >= minOut;

                if (canAddInPort) {
                    addInPort.setEnabled(true);
                }

                if (canRemoveInPort) {
                    removeInPort.setEnabled(true);
                }

                if (canAddOutPort) {
                    addOutPort.setEnabled(true);
                }

                if (canRemoveOutPort) {
                    removeOutPort.setEnabled(true);
                }

                addWidth.setEnabled(true);
                addHeight.setEnabled(true);

                int minWid = Position.getMinWidth();
                int minHei = Position.getMinHeight();
                int wid = selectedGate.getPosition().getWidth();
                int hei = selectedGate.getPosition().getHeight();

                boolean canRemoveWidth = wid > minWid;
                boolean canRemoveHeight = hei > minHei;

                if (canRemoveWidth) {
                    removeWidth.setEnabled(true);
                }

                if (canRemoveHeight) {
                    removeHeight.setEnabled(true);
                }
            }
        }
    }

    private List<SceneObject> getSceneObjectsList() {
        return getScenePanel().drawPanel.sceneObjects;
    }

    private UtilityPanel getUtilityPanel() {
        return getScenePanel().utilityPanel;
    }
}
