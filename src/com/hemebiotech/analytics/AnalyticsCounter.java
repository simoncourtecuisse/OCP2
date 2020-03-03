package com.hemebiotech.analytics;

public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {
		SymptomsCounter symptomsCounter = new SymptomsCounter();
		symptomsCounter.symptomsHandler();
		symptomsCounter.result();
	}
}
