package main.java.br.com.arida.ufc.mydbaas.common.metric.database;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.common.metric.common.AbstractDatabaseMetric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 26, 2013
 */
public class Size extends AbstractDatabaseMetric {

	private double sizeUsed;
	
	public double getSizeUsed() {
		return sizeUsed;
	}
	
	public void setSizeUsed(double sizeUsed) {
		this.sizeUsed = sizeUsed;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<Size> jsonToList(String json) {
		Gson gson = new Gson();
		List<Size> sizeList = gson.fromJson(json, new TypeToken<List<Size>>(){}.getType());
		return sizeList;
	}

}
