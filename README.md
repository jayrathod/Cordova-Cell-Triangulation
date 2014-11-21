Cordova-Cell-Triangulation-Plugin
=========================================================

Cordova provides GeoLocation api to fetch geo coordinates. The default plugin provides information about the device's location, such as latitude and longitude. Common sources of location information include Global Positioning System (GPS) and location inferred from network signals such as IP address, RFID, WiFi and Bluetooth MAC addresses, and GSM/CDMA cell IDs.

This plugin retrives geo-coordinates data without internet or wifi connection. It mainly uses the GSM/CDMA cell IDs
to triangulate a location based on cell tower. To fetch data using GPS satellite the device should support GPS functionality and should grant permission to access my location feature in settings panel. Cell triangulation can be achieved by two ways viz.
<ul>
<li>Using Cell Network</li> 
<li>Using GPS Location</li> 
</ul>
The Cell Network retrives data from cell Tower based on signal strength and returns the latitude and longitude of nearest cell tower and GPS location requires the cellphone to enable GPS Location functionality which in turn retrives data from GPS satellite. Both of these process requires clear sky i.e no barrier between the requesting device and the open sky to fetch geo-coords data. Obstacles like wall,Multi-store building tends to weak the signal.

<strong>Kindly Note:</strong> The Geo-coordinates retrived from above techniques are approximate and should not be used in data critical senarios. The response from above techniques may vary from fews seconds to 20 minutes depending on native LocationManager class implementation.

This plugin also provides extra information like networkOperator,networkType,MCC,MNC,CID,LAC,etc using these information approximate location can be traced by mapping these data with cell tower location database.

<strong>WARNING:</strong> Collection and use of geolocation data raises important privacy issues. Your app's privacy policy should discuss how the app uses geolocation data, whether it is shared with any other parties, and the level of precision of the data (for example, coarse, fine, ZIP code level, etc.). Geolocation data is generally considered sensitive because it can reveal user's whereabouts and, if stored, the history of their travels. Therefore, in addition to the app's privacy policy, you should strongly consider providing a just-in-time notice before the app accesses geolocation data (if the device operating system doesn't do so already). That notice should provide the same information noted above, as well as obtaining the user's permission (e.g., by presenting choices for OK and No Thanks). For more information, please see the Privacy Guide.

<h3>Installing the plugin</h3>
<ol>
<li>Download the repo using GIT or just a ZIP from Github.</li>
<li>Add the plugin to your project (from the root of your project):<br/><br/>

   cordova plugin add https://github.com/jayrathod/Cordova-Cell-Triangulation.git</li>
   
</ol>
<h3>Using the plugin</h3>

The plugin creates the object cordova/plugin/CellIdInfo with following two methods :
<ol>
<li>getCellInfo(successCallback, failureCallback)</li>
<li>getGeoCords(successCallback, failureCallback)</li>
</ol>
