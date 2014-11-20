Cordova-Fetch-Geo-coordinates-without-internet-connection
=========================================================

Cordova provides GeoLocation api to fetch geo coordinates. The default plugin provides information about the device's location, such as latitude and longitude. Common sources of location information include Global Positioning System (GPS) and location inferred from network signals such as IP address, RFID, WiFi and Bluetooth MAC addresses, and GSM/CDMA cell IDs.

This plugin retrives geo-coordinates data without internet or wifi connection. It mainly uses the GSM/CDMA cell IDs
to triangulate a location based on cell tower. Cell triangulation can be achieved by two ways viz.
1.Using Cell Network 
2.Using GPS Location 

The Cell Network retrives data from cell Tower based on signal strength and returns the latitude and longitude of nearest cell tower and GPS location requires the cellphone to enable GPS Location functionality which in turn retrives data from GPS satellite. Both of these process requires clear sky to fetch geo-coords data obstacles like wall,Multi-store building tends to weak the signal.

Kindly Note: The Geo-coordinates retrived from above techniques are approximate and should not be used in data critical senarios. The response from above techniques may vary from fews seconds to 20 minutes depending on native LocationManager class implementation.

This plugin also provides extra information like networkOperator,networkType,MCC,MNC,CID,LAC,etc using these information approximate location can be traced by mapping these data with cell tower location database.

WARNING: Collection and use of geolocation data raises important privacy issues. Your app's privacy policy should discuss how the app uses geolocation data, whether it is shared with any other parties, and the level of precision of the data (for example, coarse, fine, ZIP code level, etc.). Geolocation data is generally considered sensitive because it can reveal user's whereabouts and, if stored, the history of their travels. Therefore, in addition to the app's privacy policy, you should strongly consider providing a just-in-time notice before the app accesses geolocation data (if the device operating system doesn't do so already). That notice should provide the same information noted above, as well as obtaining the user's permission (e.g., by presenting choices for OK and No Thanks). For more information, please see the Privacy Guide.

Installing the plugin

1.Download the repo using GIT or just a ZIP from Github.

2.Add the plugin to your project (from the root of your project):

   phonegap plugin add https://github.com/jayrathod/Cordova-Fetch-Geo-coordinates-without-internet-connection.git

Using the plugin

The plugin creates the object cordova/plugin/CellIdInfo with following two methods :
1.getCellInfo(successCallback, failureCallback)
2.getGeoCords(successCallback, failureCallback)
