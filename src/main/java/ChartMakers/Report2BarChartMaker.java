package ChartMakers;


import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler.LegendPosition;

import Report.Report;

public class Report2BarChartMaker extends ReportChartMaker {
	
	public void makeChart(Report report) {
		ArrayList<String> projects = new ArrayList<String>();
		ArrayList<Double> hours = new ArrayList<Double>();
		
		for (List<String> row : report.getRows() ) {
			hours.add(Double.parseDouble(row.get(2)));
		}
		
		for (List<String> row : report.getRows() ) {
			projects.add(row.get(1));
		}

		CategoryChart chart = new CategoryChartBuilder().width(1000).height(500).title(report.getTitle())
				.xAxisTitle(report.getColumnNames().get(1)).yAxisTitle(report.getColumnNames().get(2)).build();

		chart.getStyler().setHasAnnotations(true);
		
		chart.addSeries("Dane", projects, hours);
		
		new SwingWrapper<CategoryChart>(chart).displayChart();
		
		
	}

	}

