package ChartMakers;

import Report.Report;

public abstract class ReportChartMaker {
	
	protected Report report;
	public abstract void makeChart(Report sourceReport);

}
