package Report;

import java.util.ArrayList;
import java.util.List;

import Model.Employee;
import Model.Model;

public abstract class ReportBuilder {
	
	protected List<Object> params = new ArrayList<Object>();
	protected List<Employee> employees = new ArrayList<Employee>();
	protected Report report = new Report();
	protected Model model;

	public Report buildReport(Model model) {
		filterEmployees(model);
		setReportTitle();
		setReportCollumnNames();
		setReportRows();
		return report;
	};
	
	abstract void filterEmployees(Model model);
	abstract void  setReportTitle();
	abstract void  setReportCollumnNames();
	abstract void  setReportRows();
	
	public void setParam(Object... params) {
		for (Object param : params) {
			this.params.add(param);
		}
	}
}
