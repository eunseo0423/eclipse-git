package com.test5;

public class Secretary extends Employee implements Bonus {
	
	public Secretary() {
		
	}
	
	
	
	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
		
	} // source > generate constructor from superclass



	@Override
	public double tax() {
		return getSalary() * 0.1;
	}

	@Override
	public void incentive(int pay) {
		setSalary((int)((pay * 0.8) + getSalary()));
		
 		
	}

}
