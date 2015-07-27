/*
	ListWiseManager.java
	
	Created by John Boyer on Jul 24, 2015
	(c) Copyright 2015 Rodax Software, Inc. All Rights Reserved. 

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.rodaxsoft.listwise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ContextedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.rodaxsoft.mail.EmailStatus;
import com.rodaxsoft.mail.IEmailAddress;

/**
 * ListWise API manager class
 * 
 * @author John Boyer
 * @version 2015-07-25
 * @since 0.1
 * @see IEmailAddress
 * 
 */
public final class ListWiseManager {

	/**
	 * ListWise API URL Format string
	 */
	private static final String LIST_WISE_API_URL_FORMAT = "https://api.listwisehq.com/clean/deep.php?email=%s&api_key=%s";

	/**
	 * Logging object
	 */
	private static final Log LOG = LogFactory.getLog(ListWiseManager.class);
	
	/**
	 * ListWise API key
	 * @see ListWiseManager#registerAPIKey(String)
	 */
	private static String sAPIKey;

	/**
	 * Invokes the deep clean API and returns the email address object to 
	 * query for cleanliness
	 * 
	 * <p><i>It's recommend that clients perform a bulk upload of an email list and 
	 * wait until processing is complete, so that the <code>email_status</code> 
	 * is final.</i></p>
	 * 
	 * @param email The email string
	 * @return An email address object to validate for cleanliness
	 * @throws ContextedException if an underlying API error occurs
	 * @throws IOException if an I/O error occurs
	 * @see <a href="http://www.listwisehq.com/support/">http://www.listwisehq.com/support</a>
	 */
	public static IEmailAddress deepClean(final String email)
			throws ContextedException, IOException {

		IEmailAddress emailAddress = null;

		final JSONObject obj = new JSONObject(deepCleanInternal(email));
		final String emailStatus = obj.optString("email_status");
		final Integer errorCode = obj.optInt("error_code");

		// emailStatus may be an empty string
		if (!StringUtils.isEmpty(emailStatus)) {

			emailAddress = new EmailAddress.Builder(obj).build();
			LOG.debug(emailAddress);

			if (emailAddress.getStatus() == EmailStatus.PROCESSING) {
				LOG.info("Still processing");
			}
		}

		else if (errorCode != null) {
			final String errorMsg = obj.getString("error_msg");

			if (errorCode != 1) {

				ContextedException ce = new ContextedException(
						"ListWise API Error");
				ce.setContextValue("error_code", errorCode);
				ce.setContextValue("error_msg", errorMsg);
				ce.setContextValue("email", email);
				
				LOG.fatal("Unrecoverable API Error", ce);
				throw ce;
			}
			
			else {
				String msg = String.format("Invalid email address: `%s`", email);
				LOG.error(msg);
				emailAddress = makeInvalidEmailAddress(email);
			}
		}

		return emailAddress;

	}

	/**
	 * Returns a JSON string from the deep clean API
	 * @param email
	 *            The email address to clean
	 * @return A JSON string from the deep clean API
	 * @throws IOException if an I/O error occurs
	 * @throws ContextedException if no API key exists
	 */
	private static String deepCleanInternal(String email) throws IOException, ContextedException {
		if(null == sAPIKey) {
			ContextedException cre;
			cre = new ContextedException("No API key, must call registerAPIKey(String) first");
			cre.addContextValue("apiKey", null);
			throw cre;
		}
		
		String apiUrl;
		apiUrl = String.format(LIST_WISE_API_URL_FORMAT, email, sAPIKey);

		URL url = new URL(apiUrl);
		URLConnection conn = url.openConnection();
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuilder buf = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null) {
			LOG.info(inputLine);
			buf.append(inputLine);
		}

		in.close();

		return buf.toString();
	}

	/**
	 * Returns Invalid email address object
	 * @param email Invalid email address
	 * @return An Invalid email address object
	 */
	private static IEmailAddress makeInvalidEmailAddress(final String email) {
		IEmailAddress emailAddress;
		emailAddress = new IEmailAddress() {
			
			@Override
			public String getEmail() {
				return email;
			}
			
			@Override
			public EmailStatus getStatus() {
				return EmailStatus.INVALID;
			}
			
			@Override
			public boolean hasHadTyposFixed() {
				return false;
			}
			
			@Override
			public boolean isFree() {
				return true;
			}
		};
		return emailAddress;
	}

	public static void registerAPIKey(final String apiKey) {
		sAPIKey = apiKey;
	}
	
	/**
	 * CTOR
	 */
	private ListWiseManager() {
		// TODO Auto-generated constructor stub
	}

}
