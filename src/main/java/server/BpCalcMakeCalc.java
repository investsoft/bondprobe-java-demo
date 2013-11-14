/* Copyright (c) 2012 - 2013 Investsoft Technology.  All Rights Reserved. */
package server;

import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getLogger;

import java.io.Closeable;
import java.io.File;
import java.util.logging.Logger;

public class BpCalcMakeCalc implements Closeable {

    private static final Logger LOG = getLogger(BpCalcMakeCalc.class.getName());

    synchronized private native void bpStringInitialize(String bpdirectory);

    synchronized private native String bpStringCalculate(String ignore, String input);

    synchronized private native void bpStringClose();

    private final String path;

    public BpCalcMakeCalc(File dir) {
        LOG.log(INFO, "bpStringInitialize(\"{0}\")", dir);
        path = dir.getAbsolutePath();
        bpStringInitialize(path);
    }

    public void reset() {
        LOG.log(INFO, "closing dll....");
        bpStringClose();
        LOG.log(INFO, "reInitializing dll path: {0}", path);
        bpStringInitialize(path);
    }

    public String calculate(String input) {
        LOG.log(INFO, "bpStringCalculate(\"\", \"{0}\")", input);
        return bpStringCalculate("", input);
    }

    @Override
    public void close() {
        LOG.info("bpStringClose()");
        bpStringClose();
    }
}
