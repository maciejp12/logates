package logates;

import logates.ports.InPort;

import java.util.List;

public class LogicCalculator {

    public static boolean[] portListToBooleanArray(List<InPort> ports) {
        boolean[] states = new boolean[ports.size()];
        for(int i = 0; i < ports.size(); i++) {
            states[i] = ports.get(i).getSourceState();
        }
        return states;
    }

    public  static boolean calculateAnd(boolean[] states) {
        if (states.length == 0) {
            return false;
        }

        if (states.length == 1) {
            return false;
        }

        boolean result = true;

        for (boolean state : states) {
            result = result && state;
        }

        return result;
    }

    public static boolean calculateOr(boolean[] states) {
        if (states.length == 0) {
            return false;
        }

        if (states.length == 1) {
            return states[0];
        }

        boolean result = false;

        for (boolean state : states) {
            result = result || state;
        }

        return result;
    }

    public static boolean calculateNot(boolean state) {
        return !state;
    }

    public static boolean calculateNand(boolean[] states) {
        return !calculateAnd(states);
    }

    public static boolean calculateNor(boolean[] states) {
        return !calculateOr(states);
    }

    public static boolean calculateXor(boolean[] states) {
        if (states.length == 0) {
            return false;
        }

        if (states.length == 1) {
            return states[0];
        }

        boolean result = states[0];

        for(int i = 1; i < states.length; i++) {
            if (states[i] != result) {
                return true;
            }
        }

        return false;
    }

    public static boolean calculateNxor(boolean[] states) {
        return !calculateXor(states);
    }
}
