package logates.sceneobject.gates;

import logates.logic.LogicCalculator;
import logates.sceneobject.Position;
import logates.sceneobject.SceneObjectImage;
import logates.sceneobject.ports.InPort;
import logates.sceneobject.ports.OutPort;
import logates.sceneobject.ports.Port;

public class NandGate extends Gate {


    public NandGate(int x, int y) {
        minInPorts = 2;
        maxInPorts = maxPortsNumber;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("nand_gate.png");

        state = true;
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

    public NandGate() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = LogicCalculator.calculateNand(
                LogicCalculator.portListToBooleanArray(inPorts));
        for(Port p : outPorts) {
            p.updateState(state);
        }
    }
}
