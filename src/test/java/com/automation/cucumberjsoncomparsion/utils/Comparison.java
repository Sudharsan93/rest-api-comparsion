package com.automation.cucumberjsoncomparsion.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.cucumber.listener.Reporter;

/**
 * @author Sudharsan
 *
 */
public class Comparison {

	/**
	 * Comparing two json response.
	 * @param msActualResponse
	 * @param msExpectedResponse
	 */
	public static void compareObjectValidation(Map<String, Object> msActualResponse,
			Map<String, Object> msExpectedResponse) {

		if (msActualResponse.size() == msExpectedResponse.size()) {

			System.out.println(
					"The records size is matched. " + "Size of Non Clous mS Response: " + msActualResponse.size()
							+ " == " + "Size of Cloud mS Response: " + msExpectedResponse.size());

			for (int i = 1; i <= 1; i++) {

				Iterator<Entry<String, Object>> expectedIterator = msExpectedResponse.entrySet().iterator();
				Iterator<Entry<String, Object>> actualIterator = msActualResponse.entrySet().iterator();

				while (expectedIterator.hasNext()) {
					Entry<String, Object> expectedEntry = expectedIterator.next();
					Entry<String, Object> actualEntry = actualIterator.next();

					if (expectedEntry.getKey().equals(actualEntry.getKey())) {

						if (expectedEntry.getValue().toString().equalsIgnoreCase(actualEntry.getValue().toString())) {

							System.out.println("Microservice(Non Cloud) Data (Key): " + actualEntry.getKey()
									+ "; --> \n" + "Microservice(Cloud) Data (Key): " + expectedEntry.getKey() + ";\n"
									+ "Microservice(Non Cloud) Data (Value) : " + actualEntry.getValue() + "; --> \n"
									+ "Microservice(Cloud) Data (Value): " + expectedEntry.getValue());

							System.out.println("Test Case Passed. \n");
							System.out.println("-------------------------------------------------------------");
						} else {
							System.out.println("Microservice(Non Cloud) Data (Key): " + actualEntry.getKey()
									+ "; --> \n" + "Microservice(Cloud) Data (Key): " + expectedEntry.getKey() + ";\n"
									+ "Microservice(Non Cloud) Data (Value) : " + actualEntry.getValue() + "; --> \n"
									+ "Microservice(Cloud) Data (Value): " + expectedEntry.getValue());
							System.out.println("Test Case Failed");
							Assert.assertEquals(
									"Test Case Failed(Value), The Non cloud endpoint response doesn't match with cloud endpoint response.",
									actualEntry.getValue().toString(), expectedEntry.getValue().toString());
							StringBuilder stringBuilder = new StringBuilder().append(buildParaGrap(""))
									.append("response mismatched at path { } ").append(actualEntry.getValue())
									.append(buildParaGrap("")).append(buildParaGrap("Expected"))
									.append(buildParaGrap(msActualResponse.get(actualEntry.getValue().toString())))
									.append(buildParaGrap("Actual"))
									.append(buildParaGrap(msExpectedResponse.get(expectedEntry.getValue().toString())));
							Reporter.addStepLog(stringBuilder.toString());
							System.out.println("-------------------------------------------------------------");
						}
					} else {
						Assert.assertEquals(
								"Test Case Failed(Key), The Non cloud endpoint response doesn't match with cloud endpoint response. ",
								actualEntry.getKey(), expectedEntry.getKey());
						System.out.println("Microservice(Non Cloud) Data (Key): " + actualEntry.getKey()
								+ "Microservice(Cloud) Data (Key): " + expectedEntry.getKey());
						StringBuilder stringBuilder = new StringBuilder().append(buildParaGrap(""))
								.append("response mismatched at path { } ").append(actualEntry.getKey())
								.append(buildParaGrap("")).append(buildParaGrap("Expected"))
								.append(buildParaGrap(msActualResponse.get(actualEntry.getKey().toString())))
								.append(buildParaGrap("Actual"))
								.append(buildParaGrap(msExpectedResponse.get(expectedEntry.getKey().toString())));
						Reporter.addStepLog(stringBuilder.toString());
						System.out.println("-------------------------------------------------------------");
					}
				}
			}
		} else {
			Assert.assertEquals("Test Case Failed, Record Size doesn't match. ", msActualResponse.size(),
					msExpectedResponse.size());
			System.out.println("Record Size doesn't match. \n" + "Size of Microservice(Non Cloud) Response: "
					+ msActualResponse.size() + " \n " + "Size of Microservice(Cloud) Response: "
					+ msExpectedResponse.size());
			System.out.println("-------------------------------------------------------------");
		}

	}

	public static void compareStatusCodeValidation(int msActualResponse, int msExpectedResponse) {
		if (msActualResponse == msExpectedResponse) {
			System.out.println("Test Case Passed with matching Status Code. The Noncloud Status Code is: "
					+ msActualResponse + ". The Clous Status Code is: " + msExpectedResponse);
			System.out.println("-------------------------------------------------------------");
		} else {
			Assert.assertEquals("Test Case Failed, The NonCloud Status Code doesn't match with Cloud Status Code. ",
					msActualResponse, msExpectedResponse);
			System.out.println("Test Case Failed, Status Code doesn't match. The Noncloud Status Code is: "
					+ msActualResponse + ". The Clous Status Code is: " + msExpectedResponse);
			System.out.println("-------------------------------------------------------------");

		}

	}

	/**
	 * @param msActualResponse
	 * @param msExpectedResponse
	 */
	public static void comparePositiveStringFlow(String msActualResponse, String msExpectedResponse) {
		if (msActualResponse.equals(msExpectedResponse)) {
			System.out.println("Test Case Passed with matching Status Code. The Noncloud Status Code is: "
					+ msActualResponse + ". The Clous Status Code is: " + msExpectedResponse);
			System.out.println("-------------------------------------------------------------");
		} else {
			Assert.assertEquals("Test Case Failed, The NonCloud Status Code doesn't match with Cloud Status Code. ",
					msActualResponse, msExpectedResponse);
			System.out.println("Test Case Failed, Status Code doesn't match. The Noncloud Status Code is: "
					+ msActualResponse + ". The Clous Status Code is: " + msExpectedResponse);
			System.out.println("-------------------------------------------------------------");

		}

	}

	/**
	 * @param object
	 * @return
	 */
	private static String buildParaGrap(Object object) {
		String html = "<p style='color:yellow;'\">" + object + "</p>";
		return html;

	}

	/**
	 * @param string
	 * @return
	 */
	private static String buildParaGrap(String string) {
		String contentColor = string.equals("Expected") ? "orange" : "red";
		String html = "<p style='color:" + contentColor + ";'\"><font size=\"3.8\">" + string + "</p>";
		return html;
	}
}
