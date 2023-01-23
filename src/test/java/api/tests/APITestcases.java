package api.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.automation.core.utils.APIUtil;
import com.google.gson.Gson;
import com.utils.FormTag;
import com.utils.LoginAPI;

import io.restassured.response.Response;

public class APITestcases {
	
	
	
	public String string;
	@Test
	public void test() {
		
		String uri= "https://o2power.api.kitchen.sgrids.io/setting/cmms/jobstatus";
		Map<String,String> params = new HashMap<>();
		String filePath = "./data/Login.json";
		File reqBody = new File(filePath);
		//String reqBody = "{\"email\":\"azhar.b@sgrids.io\",\"password\":\"ArmaX1234\"}";
		//JSONObject obj = new JSONObject(reqBody);
		//String email = obj.getString("email");
		Response response = APIUtil.get(uri, params,200);
		System.out.println(response.asString());
		
		/*
		 * Gson gson = new Gson(); LoginAPI loginApi =
		 * gson.fromJson(response.asString(), LoginAPI.class);
		 * 
		 * System.out.println(loginApi.data.accessToken);
		 */
		//setting/cmms/jobstatus
	}
	
	@Test
	public void test2() {
		
		/*
		 * String s = FormTagRequestBody.getRequestBody(); System.out.println(s);
		 */
		
		char[] charr = {'-','_','a','z'};
		String s = RandomStringUtils.random(10, 0, 0, false, false,charr);
		System.out.println(s);
		
		
	}
	
	//create new Name
	@Test
	public void create() {
		
		FormTag formTag = new FormTag();
		String formTagName = "Testing_Auto_1";
		Response response = formTag.create(formTagName,200);
		response.then().body("error", Matchers.equalTo(false))
		.body("data.formtagname", Matchers.equalTo(formTagName.toLowerCase()))
		.body("data._id", Matchers.hasSize(24));
		System.out.println(response.asPrettyString());
		
		
	}
	
	//create existing, should throw error
	@Test
	public void createExisting() {
		
		
		FormTag formTag = new FormTag();
		String formTagName = "Testing_Auto_1";
		String errormessage = String.format("tag with this formtagname /%s/ already exists.",formTagName);
		Response response = formTag.create(formTagName,400);
		response.then().body("error", Matchers.equalTo(true))
		  .body("errormessage", Matchers.equalTo(errormessage))
		  .body("data", Matchers.equalTo(false));
		 
		//System.out.println(response.asPrettyString());
		
	}
	
	@Test
	public void createEmpty() {
				
		FormTag formTag = new FormTag();
		String formTagName = "";
		String errormessage = "\"formtagname\" is not allowed to be empty";
		Response response = formTag.create(formTagName,400);
		response.then().body("error", Matchers.equalTo(true))
		  .body("errormessage", Matchers.equalTo(errormessage))
		  .body("data", Matchers.equalTo(false));
				
	}
	
		
	

	
	
}
