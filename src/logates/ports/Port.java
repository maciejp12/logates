package logates.ports;

import logates.Position;
import logates.SceneObject;
import logates.gates.Gate;

import java.util.List;
import java.util.ArrayList;

public abstract class Port extends SceneObject {

    /*
        Connected source input gate
     */
    protected Gate source;

    /*
       Connected target output gate
    */
    protected Gate target;

    /*
        Port connected to this port
        If this is input port connected port can be output port and if this
        is output port connected port can be input port
     */
    protected Port connectedPort;

    /*
        True if port draggable on scene else false
     */
    protected boolean movable;

    public void setSource(Gate newSource) {
        source = newSource;
    }

    public void setTarget(Gate newTarget) {
        target = newTarget;
    }


    public Gate getSource() {
        return source;
    }

    public Gate getTarget() {
        return target;
    }

    /*
        Return active state of source gate
        If source is null,  return false
     */
    public boolean getSourceState() {
        if (source != null) {
            return source.getState();
        }
        return false;
    }

    /*
        Return true if position rectangle contains point (x +- dx, y +- dy)
        else or if port is not draggable return false
     */
    @Override
    public boolean contains(int x, int y, int dx, int dy) {
        if (!movable) {
            return false;
        }
        return super.contains(x, y, dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return contains(x, y, 0, 0);
    }

    public void setMovable(boolean b) {
        movable = b;
    }

    /*
        Connect this port with port p argument port and update its state
        If port is movable then set its position to port p argument position
        else set port p position to this ports position
     */
    public void setConnectedPort(Port p) {
        connectedPort = p;
        p.connectedPort = this;
        updateState(getSourceState());

        if (movable) {
            Position pp = p.getPosition();
            setPosition(pp.getX(), pp.getY());
        } else {
            p.setPosition(position.getX(), position.getY());
        }
    }

    public Port getConnectedPort() {
        return connectedPort;
    }

    /*
        Disconnect from currently connected port
     */
    public abstract void disconnect();

    @Override
    public void removeFromList(List<SceneObject> sceneObjects) {
        disconnect();
        super.removeFromList(sceneObjects);
    }

    /*
        Returns all input ports of gates in list argument
     */
    public static List<Port> getAllInPorts(List<SceneObject> gates) {
        List<Port> inPorts = new ArrayList<Port>();

        for(SceneObject so : gates) {
            if (so instanceof Gate) {
                Gate g = (Gate) so;
                inPorts.addAll(g.inPorts);
            }
        }

        return inPorts;
    }

    /*
        Returns all input ports of gates in list argument
    */
    public static List<Port> getAllOutPorts(List<SceneObject> gates) {
        List<Port> outPorts = new ArrayList<Port>();

        for(SceneObject so : gates) {
            if (so instanceof Gate) {
                Gate g = (Gate) so;
                outPorts.addAll(g.outPorts);
            }
        }

        return outPorts;
    }

    /*
     *  Find first port in ports list argument containing point (x +- d, y +- d)
     *  and connect it to selected argument port
     *
     *  If no port on the list is found return false else return true
     */
    public static boolean checkConnections(int x, int y, int d,
                                           List<Port> ports, Port selected) {
        for (Port p : ports) {
            if (p.inBounds(x, y, d, d)) {
                p.setConnectedPort(selected);
                return true;
            }
        }
        return false;
    }

}
