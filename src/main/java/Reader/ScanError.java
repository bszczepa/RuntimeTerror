package Reader;
public class ScanError {
	private String filename = "";
	private String project = "";
	private Integer row = null;
	private String cell = "";
	private String description = "";
	public ScanError(String filename, String project, int row, String cell, String description) {
		super();
		this.filename = filename;
		this.project = project;
		this.row = row;
		this.cell = cell;
		this.description = description;
	}
	
	public ScanError(String filename, String project, String cell, String description) {
		super();
		this.filename = filename;
		this.project = project;
		this.cell = cell;
		this.description = description;
	}
	
	public ScanError(String filename, String project, int row, String description) {
		super();
		this.filename = filename;
		this.project = project;
		this.row = row;
		this.description = description;
	}
	
	public ScanError(String path, String project, String description) {
		this.filename = path;
		this.project = project;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ScanError [filename=" + filename + ", project=" + project + ", row=" + row + ", cell=" + cell
				+ ", description=" + description + "]" + "\n";
	}

	public String getFilename() {
		return filename;
	}

	public String getProject() {
		return project;
	}

	public Integer getRow() {
		return row;
	}

	public String getCell() {
		return cell;
	}

	public String getDescription() {
		return description;
	}

	

}