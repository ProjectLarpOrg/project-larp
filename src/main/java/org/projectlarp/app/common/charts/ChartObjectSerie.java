package org.projectlarp.app.common.charts;

import java.util.List;

public class ChartObjectSerie<T> {
	public String key;
	public List<T> values;
	
	public Long precision; // OPTIONNAL
}
	