package test;

import logates.logic.LogicCalculator;
import logates.sceneobject.gates.Cable;
import logates.sceneobject.ports.InPort;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class LogicCalculatorTest {

    @Test
    public void testEmptyPortListToBooleanArray() {
        List<InPort> ports = new ArrayList<InPort>();
        boolean[] states = LogicCalculator.portListToBooleanArray(ports);
        int expected = 0;
        int actual = states.length;
        Assert.assertNotNull(states);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSinglePortListToBooleanArray() {
        List<InPort> falsePorts = new ArrayList<InPort>();
        List<InPort> truePorts = new ArrayList<InPort>();
        InPort falsePort = new InPort();
        InPort truePort = new InPort();
        Cable source = new Cable();
        source.getOutPort().setConnectedPort(truePort);
        source.updateState(true);

        falsePorts.add(falsePort);
        truePorts.add(truePort);

        boolean[] falseArray =
                LogicCalculator.portListToBooleanArray(falsePorts);
        boolean[] trueArray = LogicCalculator.portListToBooleanArray(truePorts);

        int expected = 1;
        int actual = falseArray.length;

        Assert.assertEquals(expected, actual);

        actual = trueArray.length;

        Assert.assertEquals(expected, actual);

        boolean expectedState = false;
        boolean actualState = falseArray[0];

        Assert.assertEquals(expectedState, actualState);

        expectedState = true;
        actualState = trueArray[0];

        Assert.assertEquals(actualState, expectedState);
    }

    @Test
    public void testComplexPortListToBooleanArray() {
        List<InPort> ports = new ArrayList<InPort>();
        ports.add(new InPort());
        InPort truePort = new InPort();
        Cable source = new Cable();
        source.getOutPort().setConnectedPort(truePort);
        source.updateState(true);
        ports.add(truePort);
        ports.add(new InPort());

        boolean[] states = LogicCalculator.portListToBooleanArray(ports);

        int expected = 3;
        int actual = states.length;

        Assert.assertEquals(expected, actual);

        boolean[] expectedArray = {false, true, false};

        for(int i = 0; i < states.length; i++) {
            boolean expectedState = expectedArray[i];
            boolean actualState = states[i];
            Assert.assertEquals(expectedState, actualState);
        }
    }

    @Test
    public void testEmptyCalculateAnd() {
        boolean[] states = {};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateAnd(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateAnd() {
        boolean[] statesF = {false};
        boolean[] statesT = {true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateAnd(statesF);

        Assert.assertEquals(expected, actual);

        actual = LogicCalculator.calculateAnd(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateAnd() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateAnd(statesFF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateAnd(statesFT);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateAnd(statesTF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateAnd(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateAnd() {
        boolean[] statesF = {true ,false, true, true, true};
        boolean[] statesT = {true, true, true, true, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateAnd(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateAnd(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCalculateOr() {
        boolean[] states = {};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateOr(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateOr() {
        boolean[] falseStates = {false};
        boolean[] trueStates = {true};

        boolean falseResult = LogicCalculator.calculateOr(falseStates);
        boolean trueResult = LogicCalculator.calculateOr(trueStates);

        boolean expected = false;
        boolean actual = falseResult;

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = trueResult;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateOr() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateOr(statesFF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateOr(statesFT);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateOr(statesTF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateOr(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateOr() {
        boolean[] statesF = {false, false, false, false};
        boolean[] statesT = {false, false, false, false, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateOr(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateOr(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateNot() {
        boolean falseState = false;
        boolean trueState = true;

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNot(falseState);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNot(trueState);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCalculateNand() {
        boolean[] states = {};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNand(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateNand() {
        boolean[] statesF = {false};
        boolean[] statesT = {true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNand(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNand(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateNand() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNand(statesFF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNand(statesFT);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNand(statesTF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNand(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateNand() {
        boolean[] statesF = {true, true, true, true};
        boolean[] statesT1 = {false, true, false, false};
        boolean[] statesT2 = {false, false, false};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateNand(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNand(statesT1);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNand(statesT2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCalculateNor() {
        boolean[] states = {};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNor(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateNor() {
        boolean[] statesF = {false};
        boolean[] statesT = {true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNor(statesF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNor(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateNor() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNor(statesFF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNor(statesFT);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNor(statesTF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNor(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateNor() {
        boolean[] statesF1 = {true, true, true, true};
        boolean[] statesF2 = {false, true, false, false};
        boolean[] statesT = {false, false, false};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateNor(statesF1);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNor(statesF2);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNor(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCalculateXor() {
        boolean[] states = {};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateXor(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateXor() {
        boolean[] statesF = {false};
        boolean[] statesT = {true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateXor(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateXor(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateXor() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateXor(statesFF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateXor(statesFT);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateXor(statesTF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateXor(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateXor() {
        boolean[] statesF1 = {true, true, true, true};
        boolean[] statesF2 = {false, false, false, false};
        boolean[] statesT = {false, false, true, false, true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateXor(statesF1);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateXor(statesF2);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateXor(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCalculateNxor() {
        boolean[] states = {};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNxor(states);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSingleCalculateNxor() {
        boolean[] statesF = {false};
        boolean[] statesT = {true};

        boolean expected = false;
        boolean actual = LogicCalculator.calculateXor(statesF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateXor(statesT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleCalculateNxor() {
        boolean[] statesFF = {false, false};
        boolean[] statesFT = {false, true};
        boolean[] statesTF = {true, false};
        boolean[] statesTT = {true, true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNxor(statesFF);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNxor(statesFT);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNxor(statesTF);

        Assert.assertEquals(expected, actual);

        expected = true;
        actual = LogicCalculator.calculateNxor(statesTT);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testComplexCalculateNxor() {
        boolean[] statesT = {true, true, true, true};
        boolean[] statesF1 = {true, true, false, false};
        boolean[] statesF2 = {false, false, true, false, true};

        boolean expected = true;
        boolean actual = LogicCalculator.calculateNxor(statesT);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNxor(statesF1);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = LogicCalculator.calculateNxor(statesF2);

        Assert.assertEquals(expected, actual);
    }
}
