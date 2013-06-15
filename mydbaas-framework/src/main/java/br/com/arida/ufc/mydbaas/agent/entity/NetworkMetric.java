package main.java.br.com.arida.ufc.mydbaas.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaas.common.metric.machine.Network;

/** 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 13, 2013
 */
public class NetworkMetric extends Network implements LoadMetric {
	
	private static NetworkMetric uniqueInstance;
	
	private NetworkMetric() {}

	public static NetworkMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NetworkMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("network.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("network.cycle")));		
	}

}
