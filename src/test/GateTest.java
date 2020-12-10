package test;

import logates.SceneObject;
import logates.gates.AndGate;
import logates.gates.Button;
import logates.gates.Gate;
import logates.gates.Lamp;
import logates.ports.OutPort;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GateTest {

    @Test
    public void testAddInPort() {
        Gate gate = new AndGate();
        SceneObject.updateDelayTime = 0;

        int actual = gate.inPorts.size();
        int expected = 2;

        Assert.assertEquals(expected, actual);

        gate.addNewInPort();

        actual = gate.inPorts.size();
        expected = expected + 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddInPortLimit() {
        Gate button = new Button();
        SceneObject.updateDelayTime = 0;

        button.addNewInPort();

        int actual = button.inPorts.size();
        int expected = 0;

        Assert.assertEquals(expected, actual);

        int portLimit = 1024;

        Gate gate = new AndGate();

        for (int i = 0; i < portLimit - 3; i++) {
            gate.addNewInPort();
        }

        actual = gate.inPorts.size();
        expected = 1023;

        Assert.assertEquals(expected, actual);

        gate.addNewInPort();

        actual = gate.inPorts.size();
        expected = expected + 1;

        gate.addNewInPort();
        Assert.assertEquals(expected, actual);

        gate.addNewInPort();
        actual = gate.inPorts.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveInPort() {
        Gate gate = new AndGate();
        SceneObject.updateDelayTime = 0;
        List<SceneObject> sceneObjects = new ArrayList<>();

        gate.addNewInPort();
        gate.addNewInPort();

        int preSize = gate.inPorts.size();

        gate.removeInPort(sceneObjects);

        int actual = gate.inPorts.size();
        int expected = preSize - 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveInPortLimit() {
        Gate lamp = new Lamp();
        SceneObject.updateDelayTime = 0;
        List<SceneObject> sceneObjects = new ArrayList<>();

        lamp.removeInPort(sceneObjects);

        int actual = lamp.inPorts.size();
        int expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddOutPort() {
        Gate gate = new AndGate();
        SceneObject.updateDelayTime = 0;

        int actual = gate.outPorts.size();
        int expected = 1;

        Assert.assertEquals(expected, actual);

        gate.addNewOutPort();

        actual = gate.outPorts.size();
        expected = expected + 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddOutPortLimit() {
        Gate lamp = new Lamp();
        SceneObject.updateDelayTime = 0;

        lamp.addNewOutPort();

        int actual = lamp.outPorts.size();
        int expected = 0;

        Assert.assertEquals(expected, actual);

        Gate gate = new AndGate();

        int portLimit = 1024;
        for (int i = 0; i < portLimit - 1; i++) {
            gate.outPorts.add(new OutPort());
        }

        gate.addNewOutPort();

        actual = gate.outPorts.size();
        expected = portLimit;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveOutPort() {
        Gate gate = new AndGate();
        SceneObject.updateDelayTime = 0;
        List<SceneObject> sceneObjects = new ArrayList<>();

        gate.addNewOutPort();
        gate.addNewOutPort();

        int preSize = gate.outPorts.size();

        gate.removeOutPort(sceneObjects);

        int actual = gate.outPorts.size();
        int expected = preSize - 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveOutPortLimit() {
        Gate lamp = new Lamp();
        SceneObject.updateDelayTime = 0;
        List<SceneObject> sceneObjects = new ArrayList<>();

        lamp.removeOutPort(sceneObjects);

        int actual = lamp.outPorts.size();
        int expected = 0;

        Assert.assertEquals(expected, actual);
    }
}