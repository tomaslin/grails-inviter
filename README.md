Grails Inviter Plugin
=====================

The grails inviter plugin allows you to quickly import contacts from various social networks and mail providers into your application. 

You can see it in action at http://inviter.cloudfoundry.com

It is similar in functionality to the following social sharing plugins:

* [OpenInviter](http://openinviter.com/) - PHP 
* [Plaxo Social Widget](http://www.plaxo.com/api/widget) - Web  
* [Jainrain Engage Social Sharing](http://www.janrain.com/products/engage/social-sharing) - Web 

The main difference of this plugin is that it uses Groovy / Grails and can be deeply integrated quickly and easily to any grails site. When possible, the plugin uses each platform's authentication to comply with the policies set by each provider ( rather than scrapping address book information ). 

Under the hood, the plugin uses the excellent [Scribe oAuth library](https://github.com/fernandezpablo85/scribe-java) by Pablo Fernandez.

Supported Networks
==================

The plugin currently supports the following networks

* Facebook - writes invitation on user's wall
* Google - retrieves email
* Yahoo - retrieves email
* Twitter - sends a direct message
* LinkedIn - sends a linkedIn message
* Windows Live - sends an Invitation

Warning - read me first
=======================

This plugin is considered early alpha and should not be used in production. 

Facebook
--------

Currently, Facebook APIs do not provide a mechanism for directly messaging users. As a result, this plugin implements an invitation by writing on a friend's wall ( similar to the AirBnB invite friends invitation ). The drawback of this approach is that if friends mark your post as Spam or hide it, your application might be flagged as spam. 

A better alternative is to use the send dialogs provided by facebook instead of this plugin. https://developers.facebook.com/docs/reference/dialogs/send/

Windows Live
------------

This plugin uses the 4.1 version of the windows live connector as the 5.0 version does not provide a way to access emails or send invitations ( http://social.msdn.microsoft.com/Forums/en-US/messengerconnect/thread/1b325483-30f8-4390-bf76-913378fd65d7 ). 


Configuration
=============

The plugin requires credentials to be added to each provider used by your application. Please read the Authentication Credentials section on how to configure each social network.

Authentication Credentials
==========================

The following section describes where to get each authentication values needed for this application. You can use the config.groovy file included in this plugin as a reference. However, these authentication tokens only work with the inviter.cloudfoundry.com site. 

Facebook
--------

You need to sign up to facebook and add the [Developer application](https://developers.facebook.com/apps)

Create a new application. Make sure you enter the website you want to enable the inviter plugin via Web -> Site URL and Domain

Click on the About tab for your application, you will see your key and secret in the form:


	App ID:
	179259788807475
	
	App Secret:	
	159677e3ed32592a27ebcb0e6f590340

add these to config.groovy as

	grails.plugins.inviter.facebook.key = 'your APP ID'
	grails.plugins.inviter.facebook.secret = 'your App Secret'


Google
------

You will need to add your domain to google by going to [Manage your Domains](https://www.google.com/accounts/ManageDomains)

Add your domain and go through the validation step. This would involve adding a meta tag to your site's index page.

Once you have added and verified your domain, click on Manage blah.example.com. 

You will see your consumer key and secret in the form:

	OAuth Consumer Key: 	inviter.cloudfoundry.com
	OAuth Consumer Secret: 	mIE865434SREFEJ9BYoz0k9 

add these to config.groovy as

	grails.plugins.inviter.google.key = 'your oAuth Consumer Key'
	grails.plugins.inviter.google.secret = 'your oAuth Consumer Secret'

Yahoo
-----

You will need to login to your Yahoo account and [add a new application](https://developer.apps.yahoo.com/dashboard/createKey.html)

Select that you'll application will need extended permissions and ask to be able to read from the Contacts API.

Next, you'll have to verify your domain by adding a html file to your document root. 

You will get a confirmation with your key and token like this:

	Consumer Key: key
	Consumer Secret: secret
	Application URL: myurl.example.com
 	App Domain: mydomain.example.com

Add this to your Config.groovy as 

	grails.plugin.inviter.yahoo.key = 'Your key'
	grails.plugin.inviter.yahoo.secret = 'Your secret'

Twitter
-------

You will need to [add a new application](https://dev.twitter.com/apps/new) in Twitter

IMPORTANT: Make sure you select that you want your app to have Read, Write and Direct Message permission if you want to use the send message functionality. 

	Consumer key 		G8rCU7AHBsZAbeLUAPddQ
	Consumer secret 	BKbt1ygWu0q4xsLuJ8ddfYvegojVHz2GXn1Z5leoXN8

add these to config.groovy as

	grails.plugin.inviter.twitter.key = 'Your Consumer Key'
	grails.plugin.inviter.twitter.secret = 'Your Consumer Secret'

LinkedIn
--------

You will need to get credentials by [adding a new application](https://www.linkedin.com/secure/developer?newapp=)

Once you have completed the details for this page, you will get a key and pass, 
	
	API Key:
	k25ddpolcvf1
	Secret Key:
	KBt3x418sdmu5eur

Enter these to config.groovy as
	
	grails.plugins.inviter.linkedin.key = 'your API Key'
	grails.plugins.inviter.linkedin.secret = 'your Secret Key'

Windows Live
============

You will need to first create an application on the [Windows Live Manage Applications](https://manage.dev.live.com/AddApplication.aspx) site.

After entering the name of your application, you will get a confirmation screen like the following:

	Application name:	Grails Inviter
	Client ID:	0000000040063240
	Client secret:	DribhiR753AieFnvWOfhXlFbF2NjAOtt
	
Enter these to config.groovy as	

	grails.plugins.inviter.windowslive.key = 'your client id'
	grails.plugins.inviter.windowslive.secret = 'your client secret'
