package main.java.br.com.arida.ufc.mydbaas.common.metric.common;

import java.util.regex.Pattern;

/**
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013 
 */
public abstract class AbstractDatabaseMetric extends AbstractMetric {
	
	private String[] dbms;
	private String[] databases;
	
	public String[] getDBMSs() {
		return dbms;
	}

	public void setDBMSs(String[] dbms) {
		this.dbms = dbms;
	}

	public String[] getDatabases() {
		return databases;
	}

	public void setDatabases(String[] databases) {
		this.databases = databases;
	}

	public void setEnabledDBMSs(String dbmsList) {
		this.dbms = dbmsList.split(Pattern.quote(","));
	}
	
	public void setEnabledDatabases(String databasesList) {
		this.databases = databasesList.split(Pattern.quote(","));
	}
}
