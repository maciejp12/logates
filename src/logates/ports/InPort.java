package logates.ports;

import logates.Position;
import logates.SceneObject;
import logates.SceneObjectImage;
import logates.gates.Cable;

import java.util.List;

public class InPort extends Port {

    public InPort() {
        this(0, 0);
    }

    public InPort(int x, int y) {
        movable = false;
        position = new Position(x, y,  8, 8);
        image = new SceneObjectImage("in_port.png");
    }

    @Override
    public void updateState(boolean newState) {
        Thread thread = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(updateDelayTime);
                        repaintScene();

                        if (target != null) {
                            target.updateState(newState);
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
        if (target != null) {
            if (target instanceof Cable) {
                target.rotate();
            }
        }
    }

    @Override
    public void disconnect() {
        if (connectedPort != null) {
            connectedPort.target = null;
            connectedPort.connectedPort = null;
        }
        connectedPort = null;
        source = null;
        updateState(false);
    }

    @Override
    public void setConnectedPort(Port p) {
        if (target == p.source) {
            return;
        }
        source = p.getSource();
        p.setTarget(target);
        super.setConnectedPort(p);
    }

    @Override
    public void removeFromList(List<SceneObject> sceneObjects) {
        super.removeFromList(sceneObjects);
        target.getOutPort().disconnect();
        sceneObjects.remove(target.getOutPort());
        sceneObjects.remove(target);
    }
}
