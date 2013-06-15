package main.java.br.com.arida.ufc.mydbaas.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaas.common.metric.machine.Disk;

/**
 * @author Daivd Araújo
 * @version 2.0
 * @since March 13, 2013
 */
public class DiskMetric extends Disk implements LoadMetric {
	
	private static DiskMetric uniqueInstance;
	
	private DiskMetric() {}

	public static DiskMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DiskMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("disk.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("disk.cycle")));		
	}

}
