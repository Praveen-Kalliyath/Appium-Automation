package utilities;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import libraries.Data;
import static com.jayway.jsonpath.JsonPath.*;
import java.io.File;
import java.io.IOException;

public class JsonUtil {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	/**
	 * Prepare Json Document using Jayway Json
	 * 
	 * @param jsonFile
	 * 
	 * @return Json Document
	 */
	public static DocumentContext prepareJsonDocument(String payloadPath) {
		Configuration confiugration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		DocumentContext jsonDocument = null;
		try {
			jsonDocument = using(confiugration).parse(new File(payloadPath));
		} catch (IOException e) {
			Report.exception(e);
			e.printStackTrace();
		}
		return jsonDocument;
	}

	/**
	 * Method to retrieve value from json path
	 * 
	 * @param jsonFile
	 * 
	 * @param jsonPathExp
	 * 
	 * @return Json Path Value
	 */
	public static Object getJsonPathValue(String jsonFile, String jsonPathExp) {
		Log.info("Getting json path value '" + parse(jsonFile).read(jsonPathExp) + "' for expression '" + jsonPathExp
				+ "'");
		return parse(jsonFile).read(jsonPathExp);
	}

	/**
	 * Main method to test the utility
	 */
	public static void main(String args[]) {
		ProjectConfig config = new ProjectConfig();
		config.loadConfig(Data.EBAY_CONFIG_FILE_LOCATION);
		String json = prepareJsonDocument(Data.EBAY_RESOURCE_FOLDER_LOCATION + ProjectConfig.getProperty("EBAY_JSON_DATA_PATH")).jsonString();
		String user = getJsonPathValue(json, "$.['User Name']").toString();
		System.out.println("User Name: " + user);
	}

}
