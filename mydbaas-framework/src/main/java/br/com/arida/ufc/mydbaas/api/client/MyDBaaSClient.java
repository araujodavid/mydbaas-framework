package main.java.br.com.arida.ufc.mydbaas.api.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.api.pool.DBMSPool;
import main.java.br.com.arida.ufc.mydbaas.api.pool.DBaaSPool;
import main.java.br.com.arida.ufc.mydbaas.api.pool.DatabasePool;
import main.java.br.com.arida.ufc.mydbaas.api.pool.HostPool;
import main.java.br.com.arida.ufc.mydbaas.api.pool.VirtualMachinePool;
import main.java.br.com.arida.ufc.mydbaas.common.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaas.common.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaas.common.resource.Database;
import main.java.br.com.arida.ufc.mydbaas.common.resource.Host;
import main.java.br.com.arida.ufc.mydbaas.common.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaas.util.SendResquest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Class that handles the access information to the server.
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 28, 2013
 */
public class MyDBaaSClient {

	private String serverUrl;
	
	/**
	 * Default constructor
	 * @param serverUrl
	 */
	public MyDBaaSClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * Method to check server connection
	 * @return true if exitir, otherwise false
	 */
	public boolean hasConnection() {
		HttpResponse response = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/connection", null, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() != 200) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that returns the metrics available for resources
	 * @param typeResource (eg.: host, machine or database)
	 * @return a list of metric about the resource type
	 */
	public List<String> getEnabledMetrics(String typeResource) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("metricsType", typeResource));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/metrics", params, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		List<String> metrics = gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
		return metrics;
	}
	
	public DBaaSPool getMyDBaaSs() {
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/dbaas", null, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		DBaaSPool pool = gson.fromJson(json, DBaaSPool.class);
		pool.setClient(this);
		return pool;
	}
	
	public HostPool getMyHosts() {
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/hosts", null, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		HostPool pool = gson.fromJson(json, HostPool.class);
		pool.setClient(this);
		return pool;
	}
	
	public VirtualMachinePool getMyVirtualMachines() {
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/machines", null, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		VirtualMachinePool pool = gson.fromJson(json, VirtualMachinePool.class);
		pool.setClient(this);
		return pool;
	}
	
	public DBMSPool getMyDBMSs() {
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/dbmss", null, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		DBMSPool pool = gson.fromJson(json, DBMSPool.class);
		pool.setClient(this);
		return pool;
	}
	
	public DatabasePool getMyDatabases() {
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/databases", null, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		DatabasePool pool = gson.fromJson(json, DatabasePool.class);
		pool.setClient(this);
		return pool;	
	}
	
	public Object resourceLookupByID(int resourceId, String resourceType) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("resourceId", String.valueOf(resourceId)));
		params.add(new BasicNameValuePair("resourceType", resourceType));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/resource/find", params, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		switch (resourceType) {
		case "dbaas":
			DBaaS dBaaS = gson.fromJson(json, DBaaS.class);
			return dBaaS;
		case "host":
			Host host = gson.fromJson(json, Host.class);
			return host;
		case "machine":
			VirtualMachine virtualMachine = gson.fromJson(json, VirtualMachine.class);
			return virtualMachine;
		case "dbms":
			DBMS dbms = gson.fromJson(json, DBMS.class);
			return dbms;
		case "database":
			Database database = gson.fromJson(json, Database.class);
			return database;
		}
		return null;
	}

	//Getters and Setters	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	
	
}
