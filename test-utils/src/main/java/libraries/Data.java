package libraries;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public interface Data {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */
	public final Path PARENT_FOLDER_PATH = FileSystems.getDefault().getPath(".").toAbsolutePath().getParent()
			.getParent();

	public final String TEST_UTILS_RESOURCE_FOLDER_LOCATION = PARENT_FOLDER_PATH + "/test-utils/src/main/resources/";

	public final String REPORT_LOCATION = System.getProperty("user.home") + "/Appium-Execution-Result/Report.html";					//PARENT_FOLDER_PATH + "/ebay-module/execution-result/Report.html";

	public final String DATE_PATTERN = "MMM dd, yyyy";
	
	public final long DEFAULT_WAIT = 20;
	
	public final long CUSTOM_WAIT = 20;
	
	public final long APP_LAUNCH_WAIT = 60;
	
	/**
	 * EBAY MODULE IDENTIFIERS
	 */
	public final String EBAY_RESOURCE_FOLDER_LOCATION = PARENT_FOLDER_PATH + "/ebay-module/src/test/resources/";

	public final String EBAY_CONFIG_FILE_LOCATION = EBAY_RESOURCE_FOLDER_LOCATION + "envConfig/ebay.properties";

}
