package com.attra.scan;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.attra.scan.test.AllTestCases;

public class ScanProgress {

	public static void main(String[] args) {
//		System.out.println("Hello");
		JUnitCore engine = new JUnitCore();
        engine.addListener(new TextListener(System.out));		
        engine.run(AllTestCases.class);
        System.exit(0);
	}

}
