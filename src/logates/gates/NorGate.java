package logates.gates;

import logates.*;
import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;

public class NorGate extends Gate {


    public NorGate(int x, int y) {
        minInPorts = 2;
        maxInPorts = maxPortsNumber;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("nor_gate.png");

        state = true;
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

    public NorGate() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = LogicCalculator.calculateNor(
                LogicCalculator.portListToBooleanArray(inPorts));
        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
