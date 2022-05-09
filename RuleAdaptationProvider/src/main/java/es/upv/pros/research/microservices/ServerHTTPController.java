package es.upv.pros.research.microservices;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ServerHTTPController {

	@RequestMapping(
			  value = "/rules/", 
			  method = RequestMethod.GET,
			  produces = "application/json")
	 public String getRule(@RequestBody String fetaureVector) throws IOException, InterruptedException  {
		//Save the Feature Vector in a csv file
		RandomAccessFile raf = new RandomAccessFile("featureVector.csv","rw");
		
		String line = raf.readLine();		
		FileWriter writer = new FileWriter("FeatureVector.csv");		
		StringBuilder sb = new StringBuilder();
		
		sb.append(line);		
		sb.append("\n");		
		sb.append(fetaureVector);		
		sb.append("\n");
		
		writer.write(sb.toString());	
		writer.close();
		
		//Execute the R script and the prediction will be in a txt file
		
		Runtime.getRuntime().exec("predictor.R");
		
		Thread.sleep(10000);
		
		return new String(Files.readAllBytes(Paths.get("microservicePrediction.txt")));
	 }
	
}
