package testClassPackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;

import commonFunctionPackage.API_Common_Function;
import commonFunctionPackage.utility_Common_Functions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_repository;

public class post_tc_1 {
	
	public static void execute() throws IOException {
		
		for (int i=0 ; i<5 ; i++) 	
		{
			int res_status_code = API_Common_Function.response_statusCode(post_req_repository.base_URI(), post_req_repository.post_resource(),
				post_req_repository.post_req_tc1());
			if(res_status_code == 201)
			{
				String responseBody = API_Common_Function.response_Body(post_req_repository.base_URI(), post_req_repository.post_resource(),
				post_req_repository.post_req_tc1());
				post_tc_1.validator(responseBody, res_status_code);
				utility_Common_Functions.EvidenceFileCreator("post_tc_1", post_req_repository.post_req_tc1(), responseBody);
				break;
			}
			else
			{
				System.out.println("Correct status code is not found hence retrying the API");
			}
		}
	}
	public static void validator(String responseBody, int res_status_code) {
		// Step 3 : Parse the Response Body
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
		res_createdAt = res_createdAt.substring(0,11);
		
		// Generate date in format as received in response
		LocalDateTime Date = LocalDateTime.now();
		String expectedDate = Date.toString().substring(0,11);
						
		// Step 4 : Validate The Response Body Parameters
		Assert.assertEquals(res_status_code, 201);
		Assert.assertEquals(res_name, "Rambo");
		Assert.assertEquals(res_job, "Manager");
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt,expectedDate);
		
		System.out.println("ResponseDate" + res_createdAt);
		System.out.println("Exp_date" + expectedDate); 
	
	}
}
