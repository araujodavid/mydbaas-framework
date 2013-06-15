package main.java.br.com.arida.ufc.mydbaas.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaas.common.metric.machine.Cpu;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 5, 2013
 */
public class CpuCollector extends AbstractCollector<CpuMetric> {
		
	private List<Cpu> cpuMetrics;
	
	public CpuCollector(int identifier, String type) {
		super(identifier, type);
		this.cpuMetrics = new ArrayList<Cpu>();
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {		
		Sigar sigar = (Sigar) args[0];
		this.metric = CpuMetric.getInstance();
		for (CpuPerc cpuPerc : sigar.getCpuPercList()) {
			Cpu cpu = new Cpu();
			cpu.setCpuUser(Double.valueOf(CpuPerc.format(cpuPerc.getUser()).replace("%", "")));
			cpu.setCpuSystem(Double.valueOf(CpuPerc.format(cpuPerc.getSys()).replace("%", "")));
			cpu.setCpuIdle(Double.valueOf(CpuPerc.format(cpuPerc.getIdle()).replace("%", "")));
			cpu.setCpuNice(Double.valueOf(CpuPerc.format(cpuPerc.getNice()).replace("%", "")));
			cpu.setCpuWait(Double.valueOf(CpuPerc.format(cpuPerc.getWait()).replace("%", "")));
			cpu.setCpuCombined(Double.valueOf(CpuPerc.format(cpuPerc.getCombined()).replace("%", "")));
			this.cpuMetrics.add(cpu);
		}			
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();		
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});			
		} catch (SigarException e2) {
			System.out.println("Problem loading the CPU metric values (Sigar)");
			e2.printStackTrace();
		}		
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = null;
		try {
			params = this.loadRequestParams(new Date(), cpuMetrics, 0 , 0);
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
				System.out.println("CPU request error!");
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
		this.cpuMetrics.clear();
		sigar.close();
	}
}
