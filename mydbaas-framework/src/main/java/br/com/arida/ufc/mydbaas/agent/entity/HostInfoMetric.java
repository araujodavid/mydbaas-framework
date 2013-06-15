package main.java.br.com.arida.ufc.mydbaas.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaas.common.metric.host.HostInfo;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 30, 2013
 */
public class HostInfoMetric extends HostInfo implements LoadMetric {

	private static HostInfoMetric uniqueInstance;	
	
	private HostInfoMetric() {}

	public static HostInfoMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new HostInfoMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("hostInfo.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("hostInfo.cycle")));		
	}

}
