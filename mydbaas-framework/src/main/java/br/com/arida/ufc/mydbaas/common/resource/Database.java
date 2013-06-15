package main.java.br.com.arida.ufc.mydbaas.common.resource;

import main.java.br.com.arida.ufc.mydbaas.common.resource.common.AbstractEntity;

/**
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 18, 2013
 */
public class Database extends AbstractEntity {

	//dbms owner
	private DBMS dbms;
	//schema name
	private String name;
	//monitoring status
	private Boolean status;

	public DBMS getDbms() {
		return dbms;
	}

	public void setDbms(DBMS dbms) {
		this.dbms = dbms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "database";
	}
}
