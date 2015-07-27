/*
	EmailStatus.java
	
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
package com.rodaxsoft.mail;

/**
 * Email address status enum
 * @author John Boyer
 * @version 2015-07-24
 * @since 0.1
 *
 */
public enum EmailStatus {
	/**
	 * This address is currently being processed, you should check back again 
	 * within 5-10 minutes
	 */
	PROCESSING(1),
	/**
	 * This address is clean
	 */
	CLEAN(2),
	/**
	 * This address is invalid (fails syntax or RFC checks)
	 */
	INVALID(3), 
	/**
	 * This address is not expected to receive any reply emails
	 */
	NO_REPLY(5), 
	/**
	 * This address has been detected as a spam-trap
	 */
	SPAM_TRAP(7), 
	/**
	 * The mail server is not present or is not configured correctly
	 */
	BAD_MX(9),
	/**
	 * This address does not exist and will bounce back
	 */
	BOUNCED(11),
	/**
	 * The mail server accepts both fake and real addresses
	 */
	CATCH_ALL(12),
	/**
	 * This address has been rejected or deferred after multiple checks 
	 * without a confirmed result. It may bounce
	 */
	SUSPICIOUS(13),
	/**
	 * The mail server returns an unexpected result. This address may bounce
	 */
	UNKNOWN(15);
	/**
	 * The status
	 */
    private int status;

    /**
     * Constructor
     * @param status Integer status value
     */
    private EmailStatus(int status) {
            this.status = status;
    }
    
    /**
     * @return The integer value of the status
     */
    public int getStatusInt() {
		return status;
	}
    
};   

