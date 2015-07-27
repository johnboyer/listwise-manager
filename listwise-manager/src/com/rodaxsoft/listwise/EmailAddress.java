/*
	EmailAddress.java
	
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

import org.json.JSONObject;

import com.rodaxsoft.mail.EmailStatus;
import com.rodaxsoft.mail.IEmailAddress;

/**
 * Email address implementation class
 * 
 * @author John Boyer
 * @version 2015-07-24
 * @since 0.1
 * 
 */
final class EmailAddress implements IEmailAddress {

	/**
	 * Email address
	 */
	private String email;
	/**
	 * Email address status
	 */
	private EmailStatus status;
	/**
	 * Free email flag
	 */
	private boolean freeEmail;
	/**
	 * Typos fixed flag
	 */
	private boolean typosFixed;

	static class Builder {
		/**
		 * Response JSON object
		 */
		private JSONObject json;
		/**
		 * Email address object
		 */
		private EmailAddress emailAddress;

		/**
		 * Constructor
		 * @param json Response JSON object
		 */
		Builder(JSONObject json) {
			this.json = json;
			this.emailAddress = new EmailAddress();
		}

		/**
		 * Sets the email
		 * @return This builder object
		 */
		private Builder setEmail() {
			emailAddress.email = json.optString("email");
			return this;
		}

		/**
		 * Sets the free email flag
		 * @return This builder object
		 */
		private Builder setFreeEmail() {
			String value = json.optString("free_mail");
			emailAddress.freeEmail = value.equalsIgnoreCase("yes") ? true
					: false;
			return this;
		}

		/**
		 * Sets the typos fixed flag
		 * @return This builder object
		 */
		private Builder setTyposFixed() {
			String value = json.optString("typo_fixed");
			emailAddress.typosFixed = value.equalsIgnoreCase("yes") ? true
					: false;
			return this;
		}

		/**
		 * Sets the email status value
		 * @return This builder object
		 */
		private Builder setStatus() {
			String stringStatus = json.optString("email_status");

			if (stringStatus != null) {
				switch (stringStatus) {

				case IEmailStatusValues.BAD_MX:
					emailAddress.status = EmailStatus.BAD_MX;
					break;

				case IEmailStatusValues.BOUNCED:
					emailAddress.status = EmailStatus.BOUNCED;
					break;

				case IEmailStatusValues.CATCH_ALL:
					emailAddress.status = EmailStatus.CATCH_ALL;
					break;

				case IEmailStatusValues.CLEAN:
					emailAddress.status = EmailStatus.CLEAN;
					break;

				case IEmailStatusValues.INVALID:
					emailAddress.status = EmailStatus.INVALID;
					break;

				case IEmailStatusValues.NO_REPLY:
					emailAddress.status = EmailStatus.NO_REPLY;
					break;

				case IEmailStatusValues.PROCESSING:
					emailAddress.status = EmailStatus.PROCESSING;
					break;

				case IEmailStatusValues.SPAM_TRAP:
					emailAddress.status = EmailStatus.SPAM_TRAP;
					break;

				case IEmailStatusValues.SUSPICIOUS:
					emailAddress.status = EmailStatus.SUSPICIOUS;
					break;

				case IEmailStatusValues.UNKNOWN:
					emailAddress.status = EmailStatus.UNKNOWN;
					break;

				default:
					throw new IllegalArgumentException(
							"Unknown email status string");
				}
			}

			return this;
		}

		/**
		 * Builds and returns the email address object
		 * @return An email address object
		 */
		EmailAddress build() {
			setEmail().setFreeEmail().setTyposFixed().setStatus();
			return emailAddress;
		}

	}

	/**
	 * Default CTOR
	 */
	private EmailAddress() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#getStatus()
	 */
	@Override
	public EmailStatus getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#isFree()
	 */
	@Override
	public boolean isFree() {
		return freeEmail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#hasHadTyposFixed()
	 */
	@Override
	public boolean hasHadTyposFixed() {
		return typosFixed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailAddress [email=" + email + ", status=" + status
				+ ", isFree()=" + freeEmail + ", hasHadTyposFixed()="
				+ typosFixed + "]";
	}
}
