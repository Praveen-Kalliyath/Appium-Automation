package utilities;

import java.util.Random;

import com.github.javafaker.Faker;

public class DataFaker {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */
	private static Faker faker;

	public DataFaker() {
		faker = new Faker();
	}

	/**
	 * NAME
	 */

	/**
	 * Get Name Title
	 * 
	 * @return String: title
	 */
	public static String getNameTitle() {
		faker = new Faker();
		return faker.name().title();
	}

	/**
	 * Get Full Name
	 * 
	 * @return String: name fullName
	 */
	public static String getFullName() {
		faker = new Faker();
		return faker.name().fullName();
	}

	/**
	 * Get First Name
	 * 
	 * @return String: firstName
	 */
	public static String getFirstName() {
		faker = new Faker();
		return faker.name().firstName();
	}

	/**
	 * Get Last Name
	 * 
	 * @return String: lastName
	 */
	public static String getLastName() {
		faker = new Faker();
		return faker.name().lastName();
	}

	/**
	 * Get Full Name With Middle
	 * 
	 * @return String: nameWithMiddle
	 */
	public static String getFullNameWithMiddle() {
		faker = new Faker();
		return faker.name().nameWithMiddle();
	}

	/**
	 * ADDRESS
	 */

	/**
	 * Get Full Address
	 * 
	 * @return String: fullAddress
	 */
	public static String getFullAddress() {
		faker = new Faker();
		return faker.address().fullAddress();
	}

	/**
	 * Get Building Number
	 * 
	 * @return String: buildingNumber
	 */
	public static String getBuildingNumber() {
		faker = new Faker();
		return faker.address().buildingNumber();
	}

	/**
	 * Get City
	 * 
	 * @return String: city
	 */
	public static String getCity() {
		faker = new Faker();
		return faker.address().city();
	}

	/**
	 * Get City
	 * 
	 * @return String: cityName
	 */
	public static String getCityName() {
		faker = new Faker();
		return faker.address().cityName();
	}

	/**
	 * Get Country
	 * 
	 * @return String: country
	 */
	public static String getCountry() {
		faker = new Faker();
		return faker.address().country();
	}

	/**
	 * Get Country Code
	 * 
	 * @return String: countryCode
	 */
	public static String getCountryCode() {
		faker = new Faker();
		return faker.address().countryCode();
	}

	/**
	 * Get State
	 * 
	 * @return String: streetAddress
	 */
	public static String getState() {
		faker = new Faker();
		return faker.address().state();
	}

	/**
	 * Get Street Address
	 * 
	 * @return String: streetAddress
	 */
	public static String getStreetAddress() {
		faker = new Faker();
		return faker.address().streetAddress();
	}

	/**
	 * Get Street Name
	 * 
	 * @return String: streetName
	 */
	public static String getStreetName() {
		faker = new Faker();
		return faker.address().streetName();
	}

	/**
	 * Get Zip Code
	 * 
	 * @return String: zipCode
	 */
	public static String getZipCode() {
		faker = new Faker();
		return faker.address().zipCode();
	}

	/**
	 * Get Time Zone
	 * 
	 * @return String: timeZone
	 */
	public static String getTimeZone() {
		faker = new Faker();
		return faker.address().timeZone();
	}

	/**
	 * Get Latitude
	 * 
	 * @return String: latitude
	 */
	public static String getLatitude() {
		faker = new Faker();
		return faker.address().latitude();
	}

	/**
	 * Get Longitude
	 * 
	 * @return String: longitude
	 */
	public static String getLongitude() {
		faker = new Faker();
		return faker.address().longitude();
	}

	/**
	 * JOB
	 */

	/**
	 * Get Job Position
	 * 
	 * @return String: position
	 */
	public static String getJobPosition() {
		faker = new Faker();
		return faker.job().position();
	}

	/**
	 * Get Job Title
	 * 
	 * @return String: title
	 */
	public static String getJobTitle() {
		faker = new Faker();
		return faker.job().title();
	}

	/**
	 * CONTACT INFORMATION
	 */

	/**
	 * Get cellPhone
	 * 
	 * @return String: cellPhone
	 */
	public static String getPhoneNumber() {
		faker = new Faker();
		return faker.phoneNumber().cellPhone().replaceAll("{^za-zA-Z0-9}+", "");
	}

	/**
	 * Get Phone Extension
	 * 
	 * @return String: extension
	 */
	public static String getPhoneExtensionNumber() {
		faker = new Faker();
		return faker.phoneNumber().extension();
	}

	/**
	 * Get Email Address
	 * 
	 * @return String: safeEmailAddress
	 */
	public static String getEmailAddress() {
		faker = new Faker();
		return faker.internet().safeEmailAddress("noreply");
	}

	/**
	 * Get Paragraph
	 * 
	 * @param sentanceCount
	 * 
	 * @return String: paragraph
	 */
	public static String getRandomParagragh(int sentanceCount) {
		faker = new Faker();
		return faker.lorem().paragraph(sentanceCount);
	}

	/**
	 * Get Paragraph
	 * 
	 * @param wordCount
	 * 
	 * @return String: sentence
	 */
	public static String getRandomParagraghWithCount(int wordCount) {
		faker = new Faker();
		return faker.lorem().sentence(wordCount);
	}

	/**
	 * Get Characters
	 * 
	 * @param charCount
	 * 
	 * @return String: characters
	 */
	public static String getRandomParagraghWithSpecificNumberOfCharacters(int charCount) {
		faker = new Faker();
		return faker.lorem().characters(charCount);
	}

	/**
	 * 
	 */

	/**
	 * Get Color
	 * 
	 * @return String: name
	 */
	public static String getColor() {
		faker = new Faker();
		return faker.color().name();
	}

	/**
	 * Get Random Item Name
	 * 
	 * @return String: itemName
	 */
	public static String getRandomItemName() {
		Log.info("Selecting Random Value From Faker");
		faker = new Faker();
		Random random = new Random();
		String value = "Magic";
		int i = random.nextInt(5);
		switch (i) {
		case 0:
			value = faker.animal().name();
			break;
		case 1:
			value = faker.book().title();
			break;
		case 2:
			value = faker.beer().name();
			break;
		case 3:
			value = faker.food().fruit();
			break;
		case 4:
			value = faker.food().vegetable();
			break;
		case 5:
			value = faker.music().instrument();
			break;
		default:
			value = faker.superhero().name();
			break;
		}
		Log.info("Returning Random Value: " + value);
		return value;
	}

	/**
	 * Java main method to test utility
	 */
	public static void main(String args[]) {
		faker = new Faker();
		Log.info(getRandomItemName());
	}

}
