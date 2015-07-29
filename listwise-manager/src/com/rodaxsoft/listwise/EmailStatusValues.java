/*
	IEmailStatusValues.java
	
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

/**
 * Email address status values interface
 * @author John Boyer
 * @version 2015-07-24
 * @since 0.1
 *
 */
interface IEmailStatusValues {
	/**
	 * This address is invalid (fails syntax or RFC checks)
	 */
	String INVALID = "invalid";
	/**
	 * This address is not expected to receive any reply emails
	 */
	String NO_REPLY = "no-reply";
	/**
	 * This address has been detected as a spam-trap
	 */
	String SPAM_TRAP = "spam-trap";
	/**
	 * The mail server is not present or is not configured correctly
	 */
	String BAD_MX = "bad-mx";
	/**
	 * This address does not exist and will bounce back
	 */
	String BOUNCED = "bounced";
	/**
	 * The mail server accepts both fake and real addresses
	 */
	String CATCH_ALL = "catch-all";
	/**
	 * This address has been rejected or deferred after multiple checks 
	 * without a confirmed result. It may bounce
	 */
	String SUSPICIOUS = "suspicious";
	/**
	 * The mail server returns an unexpected result. This address may bounce
	 */
	String UNKNOWN = "unknown";
	/**
	 * This address is clean
	 */
	String CLEAN = "clean";
	/**
	 * This address is currently being processed, you should check back again 
	 * within 5-10 minutes
	 */
	String PROCESSING = "processing";	

}
