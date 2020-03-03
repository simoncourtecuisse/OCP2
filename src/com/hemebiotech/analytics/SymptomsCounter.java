package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SymptomsCounter {

	// Variables
	private List<String> symptomsList;
	private Map<String, Integer> mapSymptoms = new HashMap<>();
	ReadSymptomDataFromFile readSDFF;

	private Map<String, Integer> orderedSymptoms;

	public SymptomsCounter() {

		/**
		 * read data from symptoms.txt using ReadSymptomDataFromFile class creates a
		 * symptomsList
		 */

		readSDFF = new ReadSymptomDataFromFile("symptoms.txt");
		symptomsList = readSDFF.getSymptoms();
	}

	public void symptomsHandler() {

		/**
		 * using a HashMap to count the occurrences in the symptomsList using a TreeMap
		 * to sort them alphabetically return an orderedSymptoms
		 */

		for (String symptom : symptomsList) {
			if (mapSymptoms.containsKey(symptom)) {
				int currentValue = mapSymptoms.get(symptom);
				mapSymptoms.put(symptom, currentValue + 1);
			} else {
				mapSymptoms.put(symptom, 1);

			}
		}
		orderedSymptoms = new TreeMap<>(mapSymptoms);
		System.out.println(orderedSymptoms);
	}

	public void result() {

		/**
		 * print orderedSymptoms into result.out everytime the code is used result.out
		 * is updated with a current time stamp
		 */

		Set<Map.Entry<String, Integer>> set = orderedSymptoms.entrySet();
		Iterator<Map.Entry<String, Integer>> it = set.iterator();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("result.out", true));
			writer.append(Instant.now().toString());
			String result = "\n";

			while (it.hasNext()) {
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
				// to create the file
				String resultmap = entry.getKey() + ": " + entry.getValue();
				result = result + resultmap + "\n";

			}

			writer.append(result + "\n");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}