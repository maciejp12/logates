package logates.sceneobject.gates;

import logates.logic.LogicCalculator;
import logates.sceneobject.Position;
import logates.sceneobject.SceneObjectImage;
import logates.sceneobject.ports.InPort;
import logates.sceneobject.ports.OutPort;
import logates.sceneobject.ports.Port;

public class OrGate extends Gate {

    public OrGate(int x, int y) {
        minInPorts = 2;
        maxInPorts = maxPortsNumber;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("or_gate.png");

        state = false;
        position = new Position(x, y);
        initPorts();

        inPorts.add(new InPort(x, y));
        inPorts.add(new InPort(x, y));

        for(Port p : inPorts) {
            p.setTarget(this);
        }

        outPorts.add(new OutPort(x, y));
        outPorts.get(0).setSource(this);
        updatePortsPosition();
    }

    public OrGate() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = LogicCalculator.calculateOr(
                LogicCalculator.portListToBooleanArray(inPorts));

        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
