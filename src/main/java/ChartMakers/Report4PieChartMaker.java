package ChartMakers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import Report.Report;

public class Report4PieChartMaker extends ReportChartMaker {
	private List<PieChart> charts = new ArrayList<PieChart>();

	public void makeChart(Report sourceReport) {

		this.report = sourceReport;
		List<List<String>> rows = report.getRows();
		int numberOfCharts = report.getRows().size();

		for (int employeeIndex = 0; employeeIndex < numberOfCharts; employeeIndex++) {
			PieChart chart = makeSingleChart(employeeIndex);
			charts.add(chart);
		}

		JFrame frame = new SwingWrapper<PieChart>(charts).displayChartMatrix();
		javax.swing.SwingUtilities.invokeLater(() -> frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));

	}

	private PieChart makeSingleChart(int employeeIndex) {
		PieChart chart = new PieChartBuilder().width(480).height(360).title(getClass().getSimpleName()).build();

		chart.setTitle(report.getRows().get(employeeIndex).get(1));
		for (int projectIndex = 2; projectIndex < report.getColumnNames().size(); projectIndex++) {
			String projectName = report.getColumnNames().get(projectIndex);
			String percentValue = "";
			percentValue = report.getRows().get(employeeIndex).get(projectIndex);
			if (percentValue.equals("")) {
				percentValue = "0%";
			}
			percentValue = percentValue.substring(0, percentValue.length() - 1);
			double percentValueDouble = Double.valueOf(percentValue);
			chart.addSeries(projectName, percentValueDouble);
		}

		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110),
				new Color(243, 180, 159), new Color(246, 199, 182) };
		chart.getStyler().setSeriesColors(sliceColors);
		return chart;
	}

}
