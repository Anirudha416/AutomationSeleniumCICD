package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getJsonDataToMap {
	public List<HashMap<String, String>> getJsonMap() throws IOException {
		//read json into string
		String jsonContent=FileUtils.readFileToString(new File("E:\\Eclipse\\SeleniumFrameworkDesignInterview\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"), StandardCharsets.UTF_16);
		//String To HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		return data;
	}

}
