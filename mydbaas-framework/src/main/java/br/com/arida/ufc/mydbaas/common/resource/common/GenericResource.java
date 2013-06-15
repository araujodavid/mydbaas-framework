package main.java.br.com.arida.ufc.mydbaas.common.resource.common;

/** 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since April 17, 2013
 */
public abstract class GenericResource extends AbstractEntity {

	private String address;
	private Integer port;
	private String user;
	private String password;
	private Boolean status;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
