package logates.gates;

import logates.*;
import logates.ports.InPort;
import logates.ports.OutPort;
import logates.ports.Port;

import java.awt.*;

public class Cable extends Gate {

    private static Color activeCableColor = new Color(32, 224, 64);

    private static int thickness = 2;

    public static int rotationStates = 3;


    public Cable(int x1, int y1, int x2, int y2) {
        minInPorts = 1;
        maxInPorts = 1;
        minOutPorts = 1;
        maxOutPorts = 1;

        rotation = 0;
        state = false;
        position = new Position(x1, y1);
        initPorts();

        inPorts.add(new InPort(x1, y1));
        outPorts.add(new OutPort(x2, y2));

        getInPort().setTarget(this);
        getOutPort().setSource(this);

        getInPort().setMovable(true);
        getOutPort().setMovable(true);
    }

    public Cable() {
        this(0, 0, 0, 0);
    }

    public void updateState(boolean newState) {
        state = newState;
        getOutPort().updateState(state);
    }

    /*
        Always return false, cable is not draggable and in moved by moving its
        input port or its output port
     */
    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void rotate() {
        rotation++;
        if (rotation == rotationStates) {
            rotation = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));

        if (!state) {
            g2.setColor(idleColor);
        } else {
            g2.setColor(activeCableColor);
        }

        Port ip = getInPort();
        Port op = getOutPort();
        Position ipp = ip.getPosition();
        Position opp = op.getPosition();

        int ipx = ipp.getX();
        int ipy = ipp.getY();
        int ipw = ipp.getWidth();
        int iph = ipp.getHeight();

        int opx = opp.getX();
        int opy = opp.getY();
        int opw = opp.getWidth();
        int oph = opp.getHeight();

        if (rotation == 0) {
            g2.drawLine(ipx + (ipw / 2), ipy + (iph / 2),
                    opx + (opw / 2), ipy + (iph / 2));
            g2.drawLine(opx + (opw / 2), ipy + (iph / 2),
                    opx + (opw / 2), opy + (oph / 2));
        } else if (rotation == 1) {
            g2.drawLine(ipx + (ipw / 2), ipy + (iph / 2),
                    ipx + (ipw / 2), opy + (oph / 2));
            g2.drawLine(ipx + (ipw / 2), opy + (oph / 2),
                    opx + (opw / 2), opy + (oph / 2));
        } else if (rotation == 2) {
            g2.drawLine(ipx + (ipw / 2), ipy + (iph) / 2,
                    opx + (opw / 2), opy + (oph / 2));
        }

        ip.draw(g2);
        op.draw(g2);
    }
}
