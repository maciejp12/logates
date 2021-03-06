package logates.sceneobject.gates;

import logates.logic.LogicCalculator;
import logates.sceneobject.Position;
import logates.sceneobject.SceneObjectImage;
import logates.sceneobject.ports.InPort;
import logates.sceneobject.ports.OutPort;
import logates.sceneobject.ports.Port;

public class NotGate extends Gate {

    public NotGate(int x, int y) {
        minInPorts = 1;
        maxInPorts = 1;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("not_gate.png");

        state = true;
        position = new Position(x, y);
        initPorts();

        inPorts.add(new InPort(x, y));
        getInPort().setTarget(this);

        for(Port p : inPorts) {
            p.setTarget(this);
        }

        outPorts.add(new OutPort(x, y));
        getOutPort().setSource(this);

        updatePortsPosition();
    }

    public NotGate() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = LogicCalculator.calculateNot(newState);
        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
