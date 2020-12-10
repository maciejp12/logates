package logates.gates;

import logates.*;
import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;

public class AndGate extends Gate {

    public AndGate(int x, int y) {
        minInPorts = 2;
        maxInPorts = maxPortsNumber;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("and_gate.png");

        state = false;
        position = new Position(x, y);
        initPorts();

        inPorts.add(new InPort(x, y));
        inPorts.add(new InPort(x, y));

        for(Port p : inPorts) {
            p.setTarget(this);
        }

        outPorts.add(new OutPort(x, y));
        getOutPort().setSource(this);

        updatePortsPosition();
    }

    public AndGate() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = LogicCalculator.calculateAnd(
                LogicCalculator.portListToBooleanArray(inPorts));
        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
