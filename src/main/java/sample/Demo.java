package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import server.BpCalcMakeCalc;

public class Demo implements Runnable {
	private static final String DLL_NAME = "BpDLL.dll";
	private static final File BP_DIR = new File("C:\\BondProbe\\bin");

	private final String[] bpData = {
		"CALCTYPE=112=SECURITYID=325=SECTYPE=SBAM-SEMI-30/360=RV=103.875=SETTDT=10/15/2013=COUPON=7.75="
		+ "PAR=1000000.0=ODD1DT=6/15/2010=ISSDT=6/11/2010=MATDT=10/18/2013",
		"CALCTYPE=112=SECURITYID=1288=SECTYPE=US CORP=RV=102.0=SETTDT=10/15/2013=COUPON=0.0="
		+ "PAR=1000000.0=ODD1DT=6/19/2007=ISSDT=12/19/2006=MATDT=10/18/2013",
		"CALCTYPE=112=SECURITYID=1302=SECTYPE=US CORP=RV=101.5=SETTDT=10/15/2013=COUPON=8.5="
		+ "PAR=1000000.0=ODD1DT=4/15/2013=ISSDT=10/10/2012=MATDT=10/20/2013",
	};

	@Override
	public void run() {
		try (BpCalcMakeCalc bp = new BpCalcMakeCalc(BP_DIR)) {
			for (String bpin : bpData) {
				String bpout = bp.calculate(bpin);
				System.out.println(bpout);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		final File dll = new File(BP_DIR, DLL_NAME);
		if (!dll.exists()) throw new FileNotFoundException(dll.toString());
		System.load(dll.getAbsolutePath());
		Demo demo = new Demo();
		demo.run();
	}

}
