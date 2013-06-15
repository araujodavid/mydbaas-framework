package main.java.br.com.arida.ufc.mydbaas.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaas.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaas.common.metric.database.ProcessStatus;

/**
 * @author Daivd Araújo
 * @version 1.0
 * @since June 1, 2013
 */
public class ProcessStatusMetric extends ProcessStatus implements LoadMetric {

	private static ProcessStatusMetric uniqueInstance;	
	
	private ProcessStatusMetric() {}

	public static ProcessStatusMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ProcessStatusMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("processStatus.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("processStatus.cycle")));
		this.setEnabledDBMSs(properties.getProperty("processStatus.dbms"));
	}

}
