package utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {
    private final static Bus REST_BUS = new Bus(ThreadEnforcer.ANY);
    private final static Bus UI_BUS = new Bus();

    private BusProvider() {};

    public static Bus getRestBusInstance() {
        return REST_BUS;
    }

    public static Bus getUIBusInstance () {
        return UI_BUS;
    }
}
