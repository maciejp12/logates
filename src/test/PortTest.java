package test;

import logates.SceneObject;
import logates.gates.Cable;
import logates.ports.Port;
import org.junit.Assert;
import org.junit.Test;

public class PortTest {

    @Test
    public void testInPortSetConnected() {
        Cable source = new Cable(0, 0, 64, 64);
        Cable target = new Cable(96, 96, 128, 128);
        Port ip = target.getInPort();

        ip.setConnectedPort(source.getOutPort());

        Assert.assertSame(ip.getSource(), source);
        Assert.assertSame(ip.getTarget(), target);

        Assert.assertSame(source.getOutPort().getSource(), source);
        Assert.assertSame(source.getOutPort().getTarget(), target);

        Assert.assertSame(ip, source.getOutPort().getConnectedPort());
        Assert.assertSame(ip.getConnectedPort(), source.getOutPort());

        int expected = source.getOutPort().getPosition().getX();
        int actual = ip.getPosition().getX();

        Assert.assertEquals(expected, actual);

        expected = source.getOutPort().getPosition().getY();
        actual = ip.getPosition().getY();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testOutPortSetConnected() {
        Cable source = new Cable(0, 0, 64, 64);
        Cable target = new Cable(96, 96, 128, 128);
        Port op = source.getOutPort();

        op.setConnectedPort(target.getInPort());

        Assert.assertSame(op.getSource(), source);
        Assert.assertSame(op.getTarget(), target);

        Assert.assertSame(target.getInPort().getSource(), source);
        Assert.assertSame(target.getInPort().getTarget(), target);

        Assert.assertSame(op, target.getInPort().getConnectedPort());
        Assert.assertSame(op.getConnectedPort(), target.getInPort());

        int expected = target.getInPort().getPosition().getX();
        int actual = op.getPosition().getX();

        Assert.assertEquals(expected, actual);

        expected = target.getInPort().getPosition().getY();
        actual = op.getPosition().getY();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInPortDisconnect() {
        Cable source = new Cable(0, 0, 64, 64);
        Cable target = new Cable(96, 96, 128, 128);
        Port ip = target.getInPort();

        ip.setConnectedPort(source.getOutPort());
        ip.disconnect();

        Assert.assertSame(ip.getTarget(), target);
        Assert.assertNull(ip.getSource());
        Assert.assertNull(ip.getConnectedPort());
        Assert.assertNull(source.getOutPort().getTarget());
        Assert.assertNull(source.getOutPort().getConnectedPort());
    }

    @Test
    public void testOutPortDisconnect() {
        Cable source = new Cable(0, 0, 64, 64);
        Cable target = new Cable(96, 96, 128, 128);
        Port op = source.getOutPort();

        op.setConnectedPort(target.getInPort());
        op.disconnect();

        Assert.assertSame(op.getSource(), source);
        Assert.assertNull(op.getTarget());
        Assert.assertNull(op.getConnectedPort());
        Assert.assertNull(target.getInPort().getSource());
        Assert.assertNull(target.getInPort().getConnectedPort());
    }

    @Test
    public void testPortUpdateState() {
        Cable source = new Cable(0, 0, 64, 64);
        Cable target = new Cable(96, 96, 128, 128);
        SceneObject.updateDelayTime = 0;
        int waitTime = 32;
        Port ip = target.getInPort();
        Port op = source.getOutPort();
        ip.setConnectedPort(op);

        Assert.assertFalse(source.getState());
        Assert.assertFalse(target.getState());

        source.updateState(true);

        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {

        }

        Assert.assertTrue(source.getState());

        source.updateState(false);

        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {

        }

        Assert.assertFalse(source.getState());

        source.updateState(true);
        op.disconnect();

        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {

        }

        Assert.assertTrue(source.getState());

        source.updateState(false);

        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {

        }

        Assert.assertFalse(source.getState());
        Assert.assertFalse(target.getState());
    }

    @Test
    public void testCheckConnections() {

    }

}
