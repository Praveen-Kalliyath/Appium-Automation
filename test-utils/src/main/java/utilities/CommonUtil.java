package utilities;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import libraries.Data;

public class CommonUtil implements Data {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	private static DateFormat dateFormat;
	private static Date date;
	private static SoftAssert softAssert;
	private static String[] splitedString;

	/**
	 * Method to use default wait
	 * 
	 * @param seconds
	 */
	public static void sleep(int seconds) {
		try {
			Log.info("Waiting for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
			Log.info("Waited for " + seconds + " seconds");
		} catch (InterruptedException e) {
			Report.exception(e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to retrieve random alphabets
	 * 
	 * @param len
	 * 
	 * @return random alphabets
	 */
	public static String getRandomAlpha(int len) {
		char ch[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		Log.info("Random Alphabet: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random mix of alphabets & numbers
	 * 
	 * @param len
	 * 
	 * @return random alphaNumeric
	 */
	public static String getRandomAlphaNumeric(int len) {
		char ch[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		Log.info("Random AlphaNumeric: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random number
	 * 
	 * @param len
	 * 
	 * @return random number
	 */
	public static String getRandomNumber(int len) {
		char ch[];
		if (len == 1 || len == 2)
			ch = "123456789".toCharArray();
		else
			ch = "0123456789".toCharArray();

		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		Log.info("Random Number: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random number
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return random number within range of minimum value and maximum value
	 */
	public static String getRandomNumberInRange(int min, int max) {
		if (min > max) {
			Report.exception(new IllegalArgumentException("Minimum value provided is greater that maximum"));
			throw new IllegalArgumentException("Minimum value provided is greater that maximum");
		}
		Random random = new Random();

		return String.valueOf(random.nextInt((max - min) + 1) + min);
	}

	/**
	 * Method to validate regular expression
	 * 
	 * @param value
	 * 
	 * @param expression
	 * 
	 * @return boolean
	 */
	public static boolean regexValidator(String value, String expression) {
		Pattern pattern = Pattern.compile(expression);
		return pattern.matcher(value).matches();
	}

	/**
	 * Method to retrieve todays date
	 * 
	 * @return date
	 */
	public static String returnToday() {
		dateFormat = new SimpleDateFormat(DATE_PATTERN);
		date = new Date();
		Log.info("Today's Date: " + dateFormat.format(date));
		return dateFormat.format(date);
	}

	/**
	 * Method to retrieve date in desired pattern
	 * 
	 * @param pattern
	 * 
	 * @return date
	 */
	public static String returnToday(String pattern) {
		dateFormat = new SimpleDateFormat(pattern);
		date = new Date();
		Log.info("Today's Date: " + dateFormat.format(date));
		return dateFormat.format(date);
	}

	/**
	 * Method to retrieve date in specific pattern
	 * 
	 * @param currentDate
	 * 
	 * @param currentPattern
	 * 
	 * @param requestedPattern
	 * 
	 * @return date
	 */
	public static String convertDateToFormat(String currentDate, String currentPattern, String requestPattern) {
		Date requestedDate = null;
		try {
			requestedDate = new SimpleDateFormat(currentPattern).parse(currentDate);
		} catch (ParseException e) {
			Report.exception(e);
			e.printStackTrace();
		}
		Log.info("Requested Date in format: [" + requestPattern + "] :- "
				+ new SimpleDateFormat(requestPattern).format(requestedDate));
		return new SimpleDateFormat(requestPattern).format(requestedDate);
	}

	/**
	 * Assert Fail
	 * 
	 * @param message
	 */
	public static void hardAssertFail(String message) {
		Report.fail(message);
		Log.fatal("Hard Assert Fail: " + message);
		Assert.fail(message);
	}

	/**
	 * Hard Assert True:
	 * 
	 * @param condition
	 */
	public static void hardAssertTrue(boolean condition) {
		Log.fatal("Hard Assert True: " + condition);
		Assert.assertTrue(condition);
	}

	/**
	 * Hard Assert True:
	 * 
	 * @param condition
	 */
	public static void hardAssertFalse(boolean condition) {
		Log.fatal("Hard Assert False: " + condition);
		Assert.assertFalse(condition);
	}

	/**
	 * Soft Assert : Check Equals
	 * 
	 * @param actual
	 * 
	 * @param expected
	 */
	public static void softAssertEquals(Object actual, Object expected) {
		Log.info("Assert Equal: Actual: '" + actual.toString() + "' | Expected: '" + expected.toString() + "'");
		softAssert = new SoftAssert();
		softAssert.assertEquals(actual, expected);
	}

	/**
	 * Soft Assert : Check Not Equals
	 * 
	 * @param actual
	 * 
	 * @param expected
	 */
	public static void softAssertNotEquals(Object actual, Object expected) {
		Log.info("Assert Equal: Actual: '" + actual.toString() + "' | Expected: '" + expected.toString() + "'");
		softAssert = new SoftAssert();
		softAssert.assertNotEquals(actual, expected);
	}

	/**
	 * Soft Assert : Fail
	 * 
	 * @param message
	 */
	public static void softAssertFail(String message) {
		Report.logWithScreenShot(Status.FAIL, message);
		softAssert = new SoftAssert();
		softAssert.fail(message);
	}

	/**
	 * Soft Assert True:
	 * 
	 * @param condition
	 */
	public static void softAssertTrue(boolean condition) {
		Log.fatal("Soft Assert True: " + condition);
		softAssert = new SoftAssert();
		softAssert.assertTrue(condition);
	}

	/**
	 * Soft Assert False:
	 * 
	 * @param condition
	 */
	public static void softAssertFalse(boolean condition) {
		Log.fatal("Soft Assert True: " + condition);
		softAssert = new SoftAssert();
		softAssert.assertFalse(condition);
	}

	/**
	 * Soft Assert : Assert All
	 */
	public static void assertAll() {
		getSoftAssert().assertAll();
	}

	/**
	 * Getter method for softAssert object
	 */
	public static SoftAssert getSoftAssert() {
		return softAssert;
	}

	/**
	 * Compare Actual Value With Actual Value
	 * 
	 * @param message
	 * 
	 * @param actual
	 * 
	 * @param expected
	 */
	public static void compareActualWithExpected(String message, Object actual, Object expected) {
		if (actual.toString().equalsIgnoreCase(expected.toString())
				|| actual.toString().contains(expected.toString())) {
			Report.pass(message + " - Actual value '" + actual + "' matched with Expected value '" + expected + "'");
		} else {
			Report.fail(
					message + " - Actual value '" + actual + "' not matched with Expected value '" + expected + "'");
		}
	}

	/**
	 * Convert HashMap to Json
	 * 
	 * @param hashMap
	 * 
	 * @return jsonString
	 */
	public static String convertHashMapToJson(HashMap<String, Object> hashMap) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			Log.info("Map : " + hashMap);
			json = mapper.writeValueAsString(hashMap);
			Log.info("Converted Json: " + json);
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Convert Json to HashMap
	 * 
	 * @param jsonString
	 * 
	 * @return hashMap
	 */
	public static HashMap<String, Object> convertJsonToHashMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> jsonMap = new HashMap<>();
		try {
			Log.info("Json : " + json);
			jsonMap = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
			});
			Log.info("Converted Map : " + jsonMap);
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		return jsonMap;
	}

	/**
	 * Compare Two HashMap
	 * 
	 * @param mapA
	 * 
	 * @param mapB
	 * 
	 * @return boolean
	 */
	public static boolean compareHashMap(HashMap<String, Object> mapA, HashMap<String, Object> mapB) {
		if (mapA.equals(mapB)) {
			Report.pass("Map A is matched with Map B");
			return true;
		} else {
			Report.fail("Map A is not matched with Map B");
			return false;
		}
	}

	/**
	 * Get Random Index From List
	 * 
	 * @return index
	 */
	public static int getRandomIndex(int size) {
		Random random = new Random();
		int index = random.nextInt(size);
		Log.info("Returning Index: " + index);
		return index;
	}

	/**
	 * Split String
	 * 
	 * @param text
	 * 
	 * @para pattern
	 * 
	 * @return String[]
	 * 
	 */
	public static String[] splitString(String text, String pattern) {
		splitedString = text.split(pattern);
		// for (int i = 0; i < splitedString.length; i++) {
		// Log.info(i + " :: " + splitedString[i]);
		// }
		return splitedString;

	}

	/**
	 * Split String
	 * 
	 * @param locator
	 * 
	 * @return String[]
	 * 
	 */
	public static String[] getLocator(String locator) {
		// Report.info("LOCATOR: " + ProjectConfig.getProperty(locator));
		splitedString = splitString(ProjectConfig.getProperty(locator), ">>>");
		Log.info("Type: " + splitedString[0] + "  ::  Locator: " + splitedString[1]);
		return splitedString;
	}

	/**
	 * MathRound Function
	 * 
	 * @param value
	 * 
	 * @return roundedNumber
	 */
	public static String roundNumber(String value) {
		long roundedNumber = Math.round(Double.valueOf(value));
		Log.info("Rounded Number: " + roundedNumber);
		return String.valueOf(roundedNumber);
	}

	/**
	 * Method to return all digit from String
	 * 
	 * @param text
	 * 
	 * @return numbers
	 */
	public static String getDigitsFromString(String text) {
		char[] ch = text.toCharArray();
		String number = "";
		for (char c : ch) {
			if (Character.isDigit(c) | Character.toString(c).equals(".")) {
				number += c;
			}
		}
		return number;
	}

	/**
	 * Method to return all letters from String
	 * 
	 * @param text
	 * 
	 * @return numbers
	 */
	public static String getAlphabetsFromString(String text) {
		char[] ch = text.toCharArray();
		String value = "";
		for (char c : ch) {
			if (Character.isLetter(c)) {
				value += c;
			}
		}
		return value;
	}

	/**
	 * Method to return all letters & numbers removing special chars from String
	 * 
	 * @param text
	 * 
	 * @return alphanumeric
	 */
	public static String getAlphaNumericFromString(String text) {
		char[] ch = text.toCharArray();
		String value = "";
		for (char c : ch) {
			if (Character.isLetter(c) | Character.isDigit(c)) {
				value += c;
			}
		}
		return value;
	}

	/**
	 * Java main to test utility method
	 */
	public static void main(String args[]) {
		System.out.println("STRINGS ARE: " + splitString("abc:::text", ":::")[0]);
	}

}
