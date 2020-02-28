package com.automation.cucumberjsoncomparsion.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cucumber.api.DataTable;
import cucumber.api.java8.En;

/**
 * @author Sudharsan
 *
 */
public class StepDefinition implements En {
	static Map<String, Object> msGetTrigerFirstApi;
	static Map<String, Object> msGetTrigerSecondApi;

	public StepDefinition() {

		Given("^the GET rest service is up and running with the below parameters for the first service$",
				(DataTable dataRequest) -> {

					List<Map<String, String>> list = dataRequest.asMaps(String.class, String.class);
					String getUri = list.get(0).get("URI");
					String getPath = list.get(0).get("Path");
					String getInputParam = list.get(0).get("InputParam");
					String getHeaderParam = list.get(0).get("HeaderParam");

					Gson gson = new Gson();
					Type type = new TypeToken<TreeMap<String, String>>() {
					}.getType();
					Map<String, String> getHeaderParam2 = gson.fromJson(getHeaderParam, type);

					msGetTrigerFirstApi = ApiTrigger.getOperation(getUri, getPath, getInputParam, getHeaderParam2);

				});

		When("^the GET rest service is up and running with the below parameters for the second service$",
				(DataTable dataRequest) -> {

					List<Map<String, String>> list = dataRequest.asMaps(String.class, String.class);
					String getUri = list.get(0).get("URI");
					String getPath = list.get(0).get("Path");
					String getInputParam = list.get(0).get("InputParam");
					String getHeaderParam = list.get(0).get("HeaderParam");

					Gson gson = new Gson();
					Type type = new TypeToken<TreeMap<String, String>>() {
					}.getType();
					Map<String, String> getHeaderParam2 = gson.fromJson(getHeaderParam, type);

					msGetTrigerSecondApi = ApiTrigger.getOperation(getUri, getPath, getInputParam, getHeaderParam2);

				});

		Then("^compare the response for two service$", () -> {
			Comparison.compareObjectValidation(msGetTrigerFirstApi, msGetTrigerSecondApi);

		});

	}

}
