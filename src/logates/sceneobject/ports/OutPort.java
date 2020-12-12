package logates.sceneobject.ports;

import logates.sceneobject.Position;
import logates.sceneobject.SceneObject;
import logates.sceneobject.SceneObjectImage;
import logates.sceneobject.gates.Cable;

import java.util.List;

public class OutPort extends Port {

    public OutPort() {
        this(0, 0);
    }

    public OutPort(int x, int y) {
        movable = false;
        position = new Position(x, y, 8, 8);
        image = new SceneObjectImage("out_port.png");
    }

    @Override
    public void updateState(boolean newState) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(updateDelayTime);
                    repaintScene();
                    if (connectedPort != null) {
                        connectedPort.updateState(newState);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }


    @Override
    public void rotate() {
        if (source != null) {
            if (source instanceof Cable) {
                source.rotate();
            }
        }
    }

    @Override
    public void disconnect() {
        Port tmp = connectedPort;

        if (connectedPort != null) {
            connectedPort.source = null;
            connectedPort.connectedPort = null;
            tmp.updateState(false);
        }
        connectedPort = null;
        target = null;
    }

    @Override
    public void setConnectedPort(Port p) {
        if (source == p.target) {
            return;
        }
        target = p.getTarget();
        p.setSource(source);
        super.setConnectedPort(p);
    }

    @Override
    public void removeFromList(List<SceneObject> sceneObjects) {
        super.removeFromList(sceneObjects);
        if(source.inPorts.size() != 0) {
            source.getInPort().disconnect();
            sceneObjects.remove(source.getInPort());
        }
        sceneObjects.remove(source);
    }
}
