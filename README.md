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

The plugin currently supports the following networks

* Facebook
* Google
* Yahoo
* Twitter
* LinkedIn
* Windows Live
* CSV Import

Configuration
=============

The plugin requires credentials to be added to each provider used by your application. 


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

Twitter
-------

LinkedIn
--------
