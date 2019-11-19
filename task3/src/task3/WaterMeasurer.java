package task3;

public class WaterMeasurer {

    private Sensor waterTemperature;
    private Sensor waterPressure;
    private Sensor waterConsuptionDay;
    private Sensor waterConsuptionYear;
	
    public WaterMeasurer() {
        this.waterTemperature = new Sensor();
        this.waterPressure = new Sensor();
        this.waterConsuptionDay = new Sensor();
        this.waterConsuptionYear = new Sensor();
    }

    public WaterMeasurer(Sensor waterTemperature, Sensor waterPressure, Sensor waterConsuptionDay, Sensor waterConsuptionYear) {
        this.waterTemperature = waterTemperature;
        this.waterPressure = waterPressure;
        this.waterConsuptionDay = waterConsuptionDay;
        this.waterConsuptionYear = waterConsuptionYear;
    }
                      
    public String GetWaterTemperature() {	
        return this.waterTemperature.GetName();
    }
	
    public String GetWaterPressure() {
        return this.waterPressure.GetName();
    }

    public String GetWaterConsuptionDay() {
        return this.waterConsuptionDay.GetName();
    }

    public String GetWaterConsuptionYear() {
        return this.waterConsuptionYear.GetName();
    }

    public String GetRandomWaterTemperature() {	
        this.waterTemperature.SetRandomName(-32668, 32668, 10, IUnitOfMeasure.C);
        return GetWaterTemperature();
    }

    public String GetRandomWaterPressure() {
        this.waterTemperature.SetRandomName(-0, 65336, 1000, IUnitOfMeasure.Bar);
        return GetWaterPressure();
    }

    public String GetRandomWaterConsuptionDay() {
        this.waterTemperature.SetRandomName(0, 65336, 0, IUnitOfMeasure.liter);
        return GetWaterConsuptionDay();
    }

    public String GetRandomWaterConsuptionYear() {
        this.waterTemperature.SetRandomName(0, 65336, 10, IUnitOfMeasure.m3);
        return GetWaterConsuptionYear();
    }
	
}
