package task3;

import com.google.gson.Gson;

public class Task3 {
    
    public static void main(String[] args) {
        
        Gson gson = new Gson();
                        
        String measurerJson = "{"
                + "'waterTemperature':{'name':'3266.8 C'},"
                + "'waterPressure':{'name':'65.336 Bar'},"
                + "'waterConsuptionDay':{'name':'65336 Liter'},"
                + "'waterConsuptionYear':{'name':'6533.6 m3'}"
                + "}";
                                 
        WaterMeasurer measurer = gson.fromJson(measurerJson, WaterMeasurer.class);
        
        WaterMeasurer measurer1 = new WaterMeasurer();
        
        System.out.println("json result: " + measurer.GetWaterTemperature());        
        System.out.println("json result: " + measurer1.GetRandomWaterTemperature());                                
    }
    
}
