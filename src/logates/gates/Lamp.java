package logates.gates;

import logates.ports.InPort;
import logates.Position;
import logates.SceneObjectImage;

import java.awt.*;

public class Lamp extends Gate {

    public Lamp(int x, int y) {
        minInPorts = 1;
        maxInPorts = 1;
        minOutPorts = 0;
        maxOutPorts = 0;

        image = new SceneObjectImage("lamp.png");

        state = false;
        position = new Position(x, y);
        initPorts();

        inPorts.add(new InPort(x, y));
        getInPort().setTarget(this);

        updatePortsPosition();
    }

    public Lamp() {
        this(0, 0);
    }

    @Override
    public void updateState(boolean newState) {
        state = newState;
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

        g.fillOval(px + (pw / 4), py + (ph / 4), pw / 2,
                ph / 2);
    }

}
