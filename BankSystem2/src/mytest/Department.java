package mytest;

public class Department {

	// constructor
	public Department() {
	}
	public Department(String deptName, String deptNumber) {
		this.deptName = deptName;
		this.deptNumber = deptNumber;
	}
	
	// field
	private String deptName; // 학과명
	private String deptNumber; // 학과전화번호
	
	// method
	// business method
	// business method 나온 다음에 getter&setter (source메뉴로)
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	
}
