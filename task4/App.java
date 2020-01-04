package AAA.AAA;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

	public static void main(String[] args) {
			
		Gson gson = new Gson();
		
	    String filePath = "json.json";
	    String measurerJson = "";
	    try {
	    	measurerJson = new String ( Files.readAllBytes( Paths.get(filePath) ) );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    WaterMeasurer measurer = gson.fromJson(measurerJson, WaterMeasurer.class);
		     
	    try {
	    	measurer.startSensors();
			measurer.StartPublishing();
	    } catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}