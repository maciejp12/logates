package logates.sceneobject.gates;

import logates.sceneobject.Position;
import logates.sceneobject.SceneObjectImage;
import logates.sceneobject.ports.OutPort;
import logates.sceneobject.ports.Port;

import java.awt.*;

public class Button extends Gate {


    public Button(int x, int y) {
        minInPorts = 0;
        maxInPorts = 0;
        minOutPorts = 1;
        maxOutPorts = maxPortsNumber;

        image = new SceneObjectImage("button.png");

        state = false;
        position = new Position(x, y);
        initPorts();

        outPorts.add(new OutPort());

        for(Port p : outPorts) {
            p.setSource(this);
        }

        updatePortsPosition();
    }

    public Button() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        getOutPort().updateState(state);
    }

    @Override
    public void toggle() {
        state = !state;
        updateState(state);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (state) {
            g.setColor(activeColor);
        } else {
            g.setColor(inactiveColor);
        }

        int px = position.getX();
        int py = position.getY();
        int pw = position.getWidth();
        int ph = position.getHeight();

        g.fillOval(px + (pw / 4), py + (ph / 4), pw / 2, ph / 2);
    }
}
