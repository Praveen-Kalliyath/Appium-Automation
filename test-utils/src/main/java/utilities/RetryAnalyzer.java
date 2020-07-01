package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	private int count = 0;

	// Set MAX_RETRY_COUNT > 0 to run failed tests again
	private final int MAX_RETRY_COUNT = 0;

	/**
	 * Method to re run failed test cases
	 * 
	 * @param result
	 * 
	 * @return boolean
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */

	@Override
	public boolean retry(ITestResult result) {
		if (count < MAX_RETRY_COUNT) {
			Report.info("Re running failed testcase.");
			count++;
			return true;
		} else {
			return false;
		}
	}
}
