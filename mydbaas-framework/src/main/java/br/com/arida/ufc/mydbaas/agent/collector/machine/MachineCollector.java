package main.java.br.com.arida.ufc.mydbaas.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.MachineMetric;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 3.0
 * @since March 5, 2013
 */
public class MachineCollector extends AbstractCollector<MachineMetric>  {

	public MachineCollector(int identifier, String type) {
		super(identifier, type);
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = MachineMetric.getInstance();
		OperatingSystem sys = OperatingSystem.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		CpuInfo cpuInfo = sigar.getCpuInfoList()[0];		
		this.metric.setMachineOperatingSystem(sys.getDescription());
		this.metric.setMachineKernelName(sys.getName());
		this.metric.setMachineKernelVersion(sys.getVersion());
		this.metric.setMachineArchitecture(sys.getArch());
		this.metric.setMachineTotalMemory(Math.round((((mem.getTotal()/1024)/1024)/1024)*100.0)/100.0);
		this.metric.setMachineTotalSwap(Math.round((((swap.getTotal()/1024)/1024)/1024)*100.0)/100.0);
		this.metric.setMachineTotalCPUCores(cpuInfo.getTotalCores());
		this.metric.setMachineTotalCPUSockets(cpuInfo.getTotalSockets());
		this.metric.setMachineTotalCoresPerSocket(cpuInfo.getCoresPerSocket());
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the System metric values (Sigar)");
			e2.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = null;
		try {
			params = this.loadRequestParams(new Date(), 0, 0);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpResponse response;
		
		try {
			response = this.sendMetric(params);
			System.out.println(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() != 202) {
				System.out.println("System request error!");
				EntityUtils.consume(response.getEntity());
			}
			EntityUtils.consume(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Release any native resources associated with this sigar instance
		sigar.close();		
	}	
}
