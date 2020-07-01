package utilities;

import java.util.Base64;

public class StringEncrypt {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	/**
	 * Java Base64 method to encrypt string
	 * 
	 * @param message
	 * 
	 * @return Encoded string
	 */
	public static String encrpytString(String message) {
		return new String(Base64.getEncoder().encode(message.getBytes()));
	}

	/**
	 * Java Base64 method to decode encrypted string
	 * 
	 * @param encrptyedMessage
	 * 
	 * @return Decoded string
	 */
	public static String decrpytString(String encrptyedMessage) {
		return new String(Base64.getDecoder().decode(encrptyedMessage.getBytes()));
	}

	/**
	 * Main method to test functionality
	 */
	public static void main(String args[]) {
		String messageToBeEncrypted = "MYPASSWORD";
		Log.info("Decrpyted String: " + decrpytString(encrpytString(messageToBeEncrypted)));
	}
}
