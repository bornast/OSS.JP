package AAA.AAA;

public class SensorFactory {

	public static Sensor GetSensor(String name, IUnitOfMeasure unitOfMeasure) {
		
		if (unitOfMeasure == IUnitOfMeasure.C) {
			return new Sensor(name, -32668, 32668, 10, IUnitOfMeasure.C);
		}
		else if (unitOfMeasure == IUnitOfMeasure.m3) {
			return new Sensor(name, 0, 65336, 10, IUnitOfMeasure.m3);		
		}
		else if (unitOfMeasure == IUnitOfMeasure.liter) {
			return new Sensor(name, 0, 65336, 0, IUnitOfMeasure.liter);
		}
		else if (unitOfMeasure == IUnitOfMeasure.Bar) {
			return new Sensor(name, -0, 65336, 1000, IUnitOfMeasure.Bar);
		}
		else 
			return null;
		
	}
	
}


