package requestRepositoryPackage;

import java.io.IOException;
import java.util.ArrayList;

import commonFunctionPackage.utility_Common_Functions;

public class post_req_repository {
	
	public static String base_URI() {
		
		String BaseURI = "https://reqres.in/";
		return BaseURI;
	}
	
	public static String post_resource() {
		String resource = "/api/users";
		return resource;	
	}
	
	public static String post_req_tc1() throws IOException {
		ArrayList<String> data = utility_Common_Functions.read_data_excel("Post_Test_Data","Post_TC_1");
		//System.out.println(data);
		String req_Name = data.get(1);
		String req_Job = data.get(2);
		
		String requestBody = "{\r\n"
				+ "    \"name\": \""+req_Name+"\",\r\n"
				+ "    \"job\": \""+req_Job+"\"\r\n"
				+ "}";
		return requestBody;
	}
}
