/*
	IEmailAddress.java
	
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
 * Email address interface
 * @author John Boyer
 * @version 2015-07-24
 * @since 0.1
 *
 */
public interface IEmailAddress {
	/**
	 * Returns the email address
	 * <i>(may be different from the original email passed in for example 
	 * if typo's have been fixed) </i>
	 */
	String getEmail();
	/**
	 * Returns the email status 
	 */
	EmailStatus getStatus();
	/**
	 * Returns <code>true</code> if this address is a free email account; 
	 * otherwise, <code>false</code>.
	 */
	boolean isFree();
	/**
	 * Returns <code>true</code> if this address has had typo's fixed;
	 * otherwise, <code>false</code>.
	 */
	boolean hasHadTyposFixed();
}
