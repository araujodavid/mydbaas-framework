package main.java.br.com.arida.ufc.mydbaas.common.metric.host;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.common.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 28, 2013
 */
public class HostInfo extends AbstractMetric {

	private String hostInfoName;
	private String hostInfoHypervisor;
	private int hostInfoCores;
	private int hostInfoCpus;
	private double hostInfoMemory;
	private double hostInfoMhz;
	private String hostInfoModel;
	
	public String getHostInfoName() {
		return hostInfoName;
	}

	public void setHostInfoName(String hostInfoName) {
		this.hostInfoName = hostInfoName;
	}

	public String getHostInfoHypervisor() {
		return hostInfoHypervisor;
	}

	public void setHostInfoHypervisor(String hostInfoHypervisor) {
		this.hostInfoHypervisor = hostInfoHypervisor;
	}

	public int getHostInfoCores() {
		return hostInfoCores;
	}

	public void setHostInfoCores(int hostInfoCores) {
		this.hostInfoCores = hostInfoCores;
	}

	public int getHostInfoCpus() {
		return hostInfoCpus;
	}

	public void setHostInfoCpus(int hostInfoCpus) {
		this.hostInfoCpus = hostInfoCpus;
	}

	public double getHostInfoMemory() {
		return hostInfoMemory;
	}

	public void setHostInfoMemory(double hostInfoMemory) {
		this.hostInfoMemory = hostInfoMemory;
	}

	public double getHostInfoMhz() {
		return hostInfoMhz;
	}

	public void setHostInfoMhz(double hostInfoMhz) {
		this.hostInfoMhz = hostInfoMhz;
	}

	public String getHostInfoModel() {
		return hostInfoModel;
	}

	public void setHostInfoModel(String hostInfoModel) {
		this.hostInfoModel = hostInfoModel;
	}

	@Override
	public String toString() {
		return "host";
	}

	@Override
	public List<?> jsonToList(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
