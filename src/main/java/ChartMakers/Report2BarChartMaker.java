package ChartMakers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler.LegendPosition;

import Report.Report;

public class Report2BarChartMaker extends ReportChartMaker {

	private ArrayList<String> projects = new ArrayList<String>();
	private ArrayList<Double> hours = new ArrayList<Double>();

	public void makeChart(Report sourceReport) {
		
		

		this.report = sourceReport;

		for (List<String> row : report.getRows()) {
			hours.add(Double.parseDouble(row.get(2)));
		}

		for (List<String> row : report.getRows()) {
			projects.add(row.get(1));
		}

		CategoryChart chart = new CategoryChartBuilder().width(1000).height(500).title(report.getTitle())
				.xAxisTitle(report.getColumnNames().get(1)).yAxisTitle(report.getColumnNames().get(2)).build();

		chart.getStyler().setHasAnnotations(true);

		chart.addSeries("Czasochłonność", projects, hours);
		
		JFrame frame = new SwingWrapper<CategoryChart>(chart).displayChart();
		javax.swing.SwingUtilities.invokeLater(
			    ()->frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
			);

	}

}
