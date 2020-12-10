package logates.gates;

import logates.*;
import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;

public class Repeater extends Gate {

    public Repeater(int x, int y) {
        minInPorts = 1;
        maxInPorts = 1;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("repeater.png");

        state = true;
        position = new Position(x, y);
        initPorts();

        inPorts.add(new InPort(x, y));
        getInPort().setTarget(this);

        for(Port p : inPorts) {
            p.setTarget(this);
        }

        outPorts.add(new OutPort(x, y));
        outPorts.add(new OutPort(x, y));

        for(Port p : outPorts) {
            p.setSource(this);
        }
        updatePortsPosition();
    }

    public Repeater() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = newState;
        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
