package planetest;

public class Airplane extends Plane {
	
	public Airplane() {
		
	}
	
	public Airplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}
	
	@Override
	public void flight(int distance) {
		// 10 운항 시 연료 30 감소
		// 연료량은 운행거리 *3 만큼 줄이면됨
		setFuelSize(getFuelSize()-(distance*3));
	}
}

// 원칙은 class하나당 파일 하나
class CargoPlane extends Plane{
	
	public CargoPlane() {
		super();
		
	}

	public CargoPlane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
		
	}

	@Override
	public void flight(int distance) {
		setFuelSize(getFuelSize()-(distance*5));
		
	}
	
}