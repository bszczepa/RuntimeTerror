package services.reportServices;

import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Report;

public abstract class ReportBuilder {

	protected List<Object> params = new ArrayList<Object>();
	protected List<Employee> employees = new ArrayList<Employee>();
	protected Report report = new Report();

	public Report buildReport(List<Employee> employees) {
		this.employees = employees;
		filterEmployees();
		setReportTitle();
		setReportCollumnNames();
		setReportRows();
		return report;
	};

	abstract void filterEmployees();

	abstract void setReportTitle();

	abstract void setReportCollumnNames();

	abstract void setReportRows();

	public void addParam(Object... params) {
		for (Object param : params) {
			this.params.add(param);
		}
	}
}
