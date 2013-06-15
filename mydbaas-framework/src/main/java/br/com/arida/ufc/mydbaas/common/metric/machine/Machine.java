package main.java.br.com.arida.ufc.mydbaas.common.metric.machine;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.common.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since March 13, 2013
 */
public class Machine extends AbstractMetric {

	private String machineOperatingSystem;
	private String machineKernelName;
	private String machineKernelVersion;
	private String machineArchitecture;
	private double machineTotalMemory;
	private double machineTotalSwap;
	private int machineTotalCPUCores;
	private int machineTotalCPUSockets;
	private int machineTotalCoresPerSocket;
	
	public String getMachineOperatingSystem() {
		return machineOperatingSystem;
	}
	
	public void setMachineOperatingSystem(String machineOperatingSystem) {
		this.machineOperatingSystem = machineOperatingSystem;
	}
	
	public String getMachineKernelName() {
		return machineKernelName;
	}
	
	public void setMachineKernelName(String machineKernelName) {
		this.machineKernelName = machineKernelName;
	}
	
	public String getMachineKernelVersion() {
		return machineKernelVersion;
	}
	
	public void setMachineKernelVersion(String machineKernelVersion) {
		this.machineKernelVersion = machineKernelVersion;
	}
	
	public String getMachineArchitecture() {
		return machineArchitecture;
	}
	
	public void setMachineArchitecture(String machineArchitecture) {
		this.machineArchitecture = machineArchitecture;
	}
	
	public double getMachineTotalMemory() {
		return machineTotalMemory;
	}
	
	public void setMachineTotalMemory(double machineTotalMemory) {
		this.machineTotalMemory = machineTotalMemory;
	}
	
	public double getMachineTotalSwap() {
		return machineTotalSwap;
	}
	
	public void setMachineTotalSwap(double machineTotalSwap) {
		this.machineTotalSwap = machineTotalSwap;
	}
	
	public int getMachineTotalCPUCores() {
		return machineTotalCPUCores;
	}
	
	public void setMachineTotalCPUCores(int machineTotalCPUCores) {
		this.machineTotalCPUCores = machineTotalCPUCores;
	}
	
	public int getMachineTotalCPUSockets() {
		return machineTotalCPUSockets;
	}
	
	public void setMachineTotalCPUSockets(int machineTotalCPUSockets) {
		this.machineTotalCPUSockets = machineTotalCPUSockets;
	}
	
	public int getMachineTotalCoresPerSocket() {
		return machineTotalCoresPerSocket;
	}
	
	public void setMachineTotalCoresPerSocket(int machineTotalCoresPerSocket) {
		this.machineTotalCoresPerSocket = machineTotalCoresPerSocket;
	}

	@Override
	public String toString() {
		return "machine";
	}

	@Override
	public List<?> jsonToList(String json) {
		// TODO Auto-generated method stub
		return null;
	}	
}
