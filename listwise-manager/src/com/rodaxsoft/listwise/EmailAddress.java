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


/**
 * Email address implementation class
 * 
 * @author John Boyer
 * @version 2015-07-29
 * @since 0.1
 * 
 */
public class EmailAddress {

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

		public Builder() {
			super();
			this.emailAddress = new EmailAddress();
		}
		/**
		 * Constructor use a JSON object to populate an email address object
		 * in the {@link #build()} method.
		 * @param json Response JSON object
		 */
		Builder(JSONObject json) {
			this();
			this.json = json;
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
		 * Sets the email
		 * @param email The email
		 * @return This builder object
		 */
		public Builder setEmail(String email) {
			emailAddress.email = email;
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
		 * Sets the free email flag
		 * @param freeEmail The free email flag
		 * @return This builder object
		 */
		public Builder setFreeEmail(boolean freeEmail) {
			emailAddress.freeEmail = freeEmail;
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
		 * Sets the typos fixed flag
		 * @param typosFixed The typos fixed flag
		 * @return This builder object
		 */
		public Builder setTyposFixed(boolean typosFixed) {
			emailAddress.typosFixed = typosFixed;
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

				case EmailStatusValues.BAD_MX:
					emailAddress.status = EmailStatus.BAD_MX;
					break;

				case EmailStatusValues.BOUNCED:
					emailAddress.status = EmailStatus.BOUNCED;
					break;

				case EmailStatusValues.CATCH_ALL:
					emailAddress.status = EmailStatus.CATCH_ALL;
					break;

				case EmailStatusValues.CLEAN:
					emailAddress.status = EmailStatus.CLEAN;
					break;

				case EmailStatusValues.INVALID:
					emailAddress.status = EmailStatus.INVALID;
					break;

				case EmailStatusValues.NO_REPLY:
					emailAddress.status = EmailStatus.NO_REPLY;
					break;

				case EmailStatusValues.PROCESSING:
					emailAddress.status = EmailStatus.PROCESSING;
					break;

				case EmailStatusValues.SPAM_TRAP:
					emailAddress.status = EmailStatus.SPAM_TRAP;
					break;

				case EmailStatusValues.SUSPICIOUS:
					emailAddress.status = EmailStatus.SUSPICIOUS;
					break;

				case EmailStatusValues.UNKNOWN:
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
		 * Sets the email status value
		 * @return This builder object
		 */
		public Builder setEmailStatus(EmailStatus emailStatus) {
			emailAddress.status = emailStatus;
			return this;
		}

		/**
		 * Builds and returns the email address object
		 * @return An email address object
		 */
		public EmailAddress build() {
			
			if (json != null) {
				setEmail().setFreeEmail().setTyposFixed().setStatus();
			}
			
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
	public String getEmail() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#getStatus()
	 */
	public EmailStatus getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#isFree()
	 */
	public boolean isFree() {
		return freeEmail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rodaxsoft.skedi.email.IEmailAddress#hasHadTyposFixed()
	 */
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
