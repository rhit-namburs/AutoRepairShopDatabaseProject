package gui;

public class EmployeeTaskAssignments {

	public int repairID;
	public String name;
	public int taskID;
	public String desc;
	String username;
	int status;

	public EmployeeTaskAssignments(int repairID, int taskID, String name, String desc, int completion,
			String username) {
		this.repairID = repairID;
		this.taskID = taskID;
		this.name = name;
		this.desc = desc;
		this.status = completion;
		this.username = username;
	}
}
