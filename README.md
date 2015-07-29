# ListWise Manager for Java v0.1
*Updated Wed, Jul 29, 2015*

## Introduction
ListWise Manager is a Java implementation of [ListWise's API](http://www.listwisehq.com/support/index.php). It's easy for clients to make API calls and query the [POJO](https://en.wikipedia.org/wiki/Plain_Old_Java_Object) result object.
## Usage
Using the library is simple. However, it's recommended that clients perform a [bulk upload](http://www.listwisehq.com/support/support_detail.php?cat=List+Management&title=Import+A+New+List+for+Cleaning) of an email list and  wait until processing is complete, so that the ListWise `email_status` is final.

###Preconditions
Before performing API operations register your API key.

	//Register the API key before
	ListWiseManager.registerAPIKey(API_KEY);
				

### Deep Clean
	
	EmailAddress emailAddress = ListWiseManager.deepClean("john@example.com");
		

## Motivation
I created this library because 1) we use Java to manage the status of email addresses in our system and 2) we want to weed out invalid addresses before sending emails to our subscribers.

## Installation

### Prerequisites
1. Install and configure [Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunasr1)
2. Install and configure the [Egit](http://www.eclipse.org/egit) plugin
3. Install and configure the [Apache IvyDE](http://ant.apache.org/ivy/ivyde/index.html) plugin

This project is dependent on the following libraries:

* json 20140107
* commons-lang3 3.4
* commons-logging 1.2

If you're using [Apache Ivy](http://ant.apache.org/ivy) for dependency management, an `ivy.xml` file has been included in the root of the project. Otherwise, it will need to be converted to a Maven POM file. 

### Import Project
The following steps assume that you're using Eclipse with the [Egit](http://www.eclipse.org/egit) and [Apache IvyDE](http://ant.apache.org/ivy/ivyde/index.html) plugin.

1. In Eclipse, click File > Import > Git > Projects from Git.
2. Then click Next > Clone URI, set the Connection to Git, set the URI, and click Next > Next > Next > Finish.
3. Click Import existing projects, click Working Directory, and click Next > Finish.
4. In the Project Explorer, right-click the project, and click Ivy > Resolve.


## API Reference

See the Javadoc comments in `ListWiseManager.java` for details.

## Contributors
Contributions can be made by following these steps:

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

If you have any questions, please don't hesitate to contact me at john@rodaxsoft.com.

## License
This project is licensed under the Apache License, Version 2.0. See the LICENSE file for more information or go to:  http://www.apache.org/licenses/LICENSE-2.0.html
