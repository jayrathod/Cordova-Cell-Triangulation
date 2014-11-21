package com.example.cellidinfo;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class CellIdInfoPlugin extends CordovaPlugin implements LocationListener {

	private CallbackContext callbackContext;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude
	float accuracy; // accuracy
	double altitude;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	public boolean execute(String action, JSONArray args,CallbackContext callbackContext) {

		final String LOGTAG = "Cell ID";
		this.callbackContext = callbackContext;
		// retrieve a reference to an instance of TelephonyManager
		TelephonyManager telephonyManager = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);

		if (action.equals("getCellInfo")) {
			GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

			String networkOperator = telephonyManager.getNetworkOperator();
			String networkOperatorName = telephonyManager
					.getNetworkOperatorName();
			int networkType = telephonyManager.getNetworkType();

			String mcc = networkOperator.substring(0, 3);
			String mnc = networkOperator.substring(3);
			int cid = cellLocation.getCid();
			int lac = cellLocation.getLac();

			try {
				JSONObject cellResult = new JSONObject();
				cellResult.put("networkOperator", networkOperator);
				cellResult.put("networkOperatorName", networkOperatorName);
				cellResult.put("networkType", networkType);
				cellResult.put("mcc", mcc);
				cellResult.put("mnc", mnc);
				cellResult.put("cid", cid);
				cellResult.put("lac", lac);

				callbackContext.success(cellResult);
				return true;

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				callbackContext.error("Failed to retrive cell Info: "
						+ e.getMessage());
				return false;
			}
		}
		if (action.equals("getGeoCords")) {
			try {
				LocationManager locationManager = (LocationManager) this.cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
				isGPSEnabled = locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER);
				isNetworkEnabled = locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
				if (!isGPSEnabled && !isNetworkEnabled) {
					Log.d("CellIdInfoPlugin", "GPS functionality is turned Off or no sim card detected");
					// no gps provider is enabled
				} else {
					if (isNetworkEnabled) {
					  //request locations frequently. Use MIN_DISTANCE_CHANGE_FOR_UPDATES and MIN_TIME_BW_UPDATES as parameters below to delay frequent requests
						locationManager.requestLocationUpdates(
								LocationManager.NETWORK_PROVIDER, 0, 0, this);
					
						if (locationManager != null) {
							
							PluginResult r1 = new PluginResult(
									PluginResult.Status.NO_RESULT);
							r1.setKeepCallback(true);
							callbackContext.sendPluginResult(r1);
							return true;
						}
					}
					if (isGPSEnabled) {
					  //request locations frequently. Use MIN_DISTANCE_CHANGE_FOR_UPDATES and MIN_TIME_BW_UPDATES as parameters below to delay frequent requests
						if (location == null) {
							locationManager.requestLocationUpdates(
									LocationManager.GPS_PROVIDER, 0, 0, this);
						
							if (locationManager != null) {
							
								PluginResult r = new PluginResult(
										PluginResult.Status.NO_RESULT);
								r.setKeepCallback(true);
								callbackContext.sendPluginResult(r);
								return true;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		try {
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				accuracy = location.getAccuracy();
				altitude = location.getAltitude();
				
				JSONObject position = new JSONObject();			
				position.put("longitude", longitude);
				position.put("latitude", latitude);
				position.put("Accuracy", longitude);
				position.put("Altitude", altitude);
				callbackContext.success(position);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		System.out.println("Provider disabled");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		System.out.println("Provider enabled");

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		System.out.println("Provider status changed : " + status);

	}
}
