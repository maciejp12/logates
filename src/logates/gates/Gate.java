package logates.gates;

import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;
import logates.SceneObject;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Gate extends SceneObject {

    public static Color activeColor = new Color(0, 200, 0);
    public static Color inactiveColor = new Color(200, 0, 0);
    public static Color idleColor = new Color(0, 0, 0);

    /*
        Current active state of gate
     */
    protected boolean state;

    /*
        List of input ports of gate
     */
    public List<InPort> inPorts;

    /*
        List of output ports of gate
     */
    public List<OutPort> outPorts;

    /*
        Minimal number of input ports possible
     */
    protected int minInPorts;

    /*
        Maximum number of input ports possible
     */
    protected int maxInPorts;

    /*
        Minimal number of output ports possible
     */
    protected int minOutPorts;

    /*
        Maximum number of output ports possible
     */
    protected int maxOutPorts;

    /*
        Maximum number of input port list size or output port list size possible
     */
    public static int maxPortsNumber = 1024;
    /*
        Initialize ports lists
     */
    public void initPorts() {
        inPorts = new ArrayList<InPort>();
        outPorts = new ArrayList<OutPort>();
    }

    @Override
    public void updateState(boolean newState) {

    }

    public void setState(boolean newState) {
        state = newState;
    }

    public boolean getState() {
        return state;
    }

    public Port getInPort(int n) {
        return inPorts.get(n);
    }

    public Port getInPort() {
        return getInPort(0);
    }

    public Port getOutPort(int n) {
        return outPorts.get(n);
    }

    public Port getOutPort() {
        return getOutPort(0);
    }

    public int getMinInPorts() {
        return minInPorts;
    }

    public int getMaxInPorts() {
        return maxInPorts;
    }

    public int getMinOutPorts() {
        return minOutPorts;
    }

    public int getMaxOutPorts() {
        return maxOutPorts;
    }

    /*
        Create new input port add it to this gate list of input ports and
        connect new port to this gate

        If the number of ports in the input port list will be greater than
        maximum input ports number then do nothing
     */
    public void addNewInPort() {
        if (inPorts.size() + 1 <= maxInPorts) {
            InPort newPort = new InPort();
            newPort.setTarget(this);
            inPorts.add(newPort);
            updateState(false);
            updatePortsPosition();
        }
    }

    /*
        Disconnect last port in input ports list and remove it from this input
        ports list and from so argument list

        If the number of ports in the input port list will be lower than
        minimal number of input ports list then do nothing
     */
    public void removeInPort(List<SceneObject> so) {
        if (inPorts.size() - 1 >= minInPorts) {
            InPort toRemove = inPorts.get(inPorts.size() - 1);
            toRemove.disconnect();
            updateState(false);
            inPorts.remove(toRemove);
            so.remove(toRemove);
            updatePortsPosition();
        }
    }

    /*
       Create new output port add it to this gate list of output ports and
       connect new port to gate

       If the number of ports in the output port list will be greater than
       maximum output ports number then do nothing
    */
    public void addNewOutPort() {
        if (outPorts.size() + 1 <= maxOutPorts) {
            OutPort newPort = new OutPort();
            newPort.setSource(this);
            outPorts.add(newPort);
            updateState(false);
            updatePortsPosition();
        }
    }

    /*
        Disconnect last port in output ports list and remove it from this output
        ports list and from so argument list

        If the number of ports in the output port list will be lower than
        minimal number of output ports list then do nothing
     */
    public void removeOutPort(List<SceneObject> so) {
        if (outPorts.size() - 1 >= minOutPorts) {
            OutPort toRemove = outPorts.get(outPorts.size() - 1);
            toRemove.disconnect();
            updateState(false);
            outPorts.remove(toRemove);
            so.remove(toRemove);
            updatePortsPosition();
        }
    }

    @Override
    public void rotate() {
        rotation++;
        if (rotation == rotationStates) {
            rotation = 0;
        }

        image.rotate();
        updatePortsPosition();
    }

    @Override
    public void updatePortsPosition() {

        int px = position.getX();
        int py = position.getY();
        int pw = position.getWidth();
        int ph = position.getHeight();

        if (inPorts.size() > 0) {
            int inWGap = 0;
            int inHGap = 0;
            if (inPorts.size() >= 2) {
                inWGap = pw / (inPorts.size() - 1);
                inHGap = ph / (inPorts.size() - 1);
            }
            int inSum = 0;
            if (inPorts.size() == 1) {
                if (rotation == 0 || rotation == 2) {
                    inSum = ph / 2;
                } else {
                    inSum = pw / 2;
                }
            }

            for (InPort ip : inPorts) {

                int dx = ip.getPosition().getWidth() / 2;
                int dy = ip.getPosition().getHeight() / 2;

                if (rotation == 0) {
                    ip.setPosition(px - dx, py + inSum - dy);
                    inSum += inHGap;
                } else if (rotation == 1) {
                    ip.setPosition(px - dx + inSum, py - dy);
                    inSum += inWGap;
                } else if (rotation == 2) {
                    ip.setPosition(px + pw - dx, py + inSum - dy);
                    inSum += inHGap;
                } else if (rotation == 3) {
                    ip.setPosition(px - dx + inSum, py + ph - dy);
                    inSum += inWGap;
                }

                if (ip.getConnectedPort() != null) {
                    ip.getConnectedPort().setPosition(ip.getPosition().getX(),
                                                      ip.getPosition().getY());
                }
            }
        }

        if (outPorts.size() > 0) {
            int outWGap = 0;
            int outHGap = 0;
            if (outPorts.size() >= 2) {
                outWGap = pw / (outPorts.size() - 1);
                outHGap = ph / (outPorts.size() - 1);
            }
            int outSum = 0;
            if (outPorts.size() == 1) {
                if (rotation == 0 || rotation == 2) {
                    outSum = ph / 2;
                } else {
                    outSum = pw / 2;
                }
            }

            for (OutPort op : outPorts) {

                int dx = op.getPosition().getWidth() / 2;
                int dy = op.getPosition().getHeight() / 2;

                if (rotation == 0) {
                    op.setPosition(px + pw - dx, py + outSum - dy);
                    outSum += outHGap;
                } else if (rotation == 1) {
                    op.setPosition(px - dx + outSum, py + ph - dy);
                    outSum += outWGap;
                } else if (rotation == 2) {
                    op.setPosition(px - dx, py + outSum - dy);
                    outSum += outHGap;
                } else if (rotation == 3) {
                    op.setPosition(px - dx + outSum, py - dy);
                    outSum += outWGap;
                }

                if (op.getConnectedPort() != null) {
                    op.getConnectedPort().setPosition(op.getPosition().getX(),
                                                 op.getPosition().getY());
                }
            }
        }
    }

    /*
        Add this gate, all of its ports in inPorts list and all of its ports in
        outPort list to sceneObject list argument
     */
    @Override
    public void addToList(List<SceneObject> sceneObjects) {
        super.addToList(sceneObjects);
        sceneObjects.addAll(inPorts);
        sceneObjects.addAll(outPorts);
    }

    /*
        Remove this gate, all of its ports in inPorts list and all of its ports
        in outPort list from sceneObject list argument
        Before removing ports disconnect them from other gates
     */
    @Override
    public void removeFromList(List<SceneObject> sceneObjects) {
        super.removeFromList(sceneObjects);
        for(Port p : inPorts) {
            p.removeFromList(sceneObjects);
        }
        for(Port p : outPorts) {
            p.removeFromList(sceneObjects);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        for(InPort p : inPorts) {
            p.draw(g);
        }

        for(OutPort p : outPorts) {
            p.draw(g);
        }
    }
}
