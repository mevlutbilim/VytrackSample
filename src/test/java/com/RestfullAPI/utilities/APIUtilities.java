package com.RestfullAPI.utilities;

import com.RestfullAPI.pojos.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class APIUtilities {
    private static String URI=ConfigurationReader.getProperty("spartan.uri");

    public static Response postSpartan(Spartan spartan){
        Response response= given().contentType(ContentType.JSON)
                .basePath(URI)
                .body(spartan)
                .when()
                .post("/spartans");
        return response;
    }

    public static Response postSpartan(Map<String,?> spartan){
        RestAssured.baseURI=ConfigurationReader.getProperty("spartan.uri");
        Response response=given().contentType(ContentType.JSON)
                .basePath(URI)
                .body(spartan)
                .when()
                .post("/spartans");
        return response;
    }
    public static Response postSpartan(String filePath){
        RestAssured.baseURI=ConfigurationReader.getProperty("spartan.uri");
        File file=new File(filePath);
        Response response=given().contentType(ContentType.JSON)
                .body(file)
                .post("/spartans");
        return response;
    }

    public static Response deleteSpartanById(int id){
        RestAssured.baseURI=ConfigurationReader.getProperty("spartan.uri");
        return RestAssured.when().delete("spartans/{id}",id);
    }

    public static void deleteAllSpartans(){
        Response response=given()
                .basePath(baseURI)
                .accept(ContentType.JSON)
                .when()
                .get("spartans");
        List<Integer> userIDs=response.jsonPath().getList("id");
        for(int i=0;i<userIDs.size();i++){
            when().delete("/spartans/{id}",userIDs.get(i)).then()
                    .assertThat().statusCode(204);
            System.out.println("Deleted spartan with id: "+userIDs.get(i));
        }
    }
    public static String getTokenForBookit(){
        Response response=given()
                .queryParam("email",ConfigurationReader.getProperty("team.leader.email"))
                .queryParam("password",ConfigurationReader.getProperty("team.leader.password"))
                .when().get("/sign").prettyPeek();
        return response.jsonPath().getString("accessToken");
    }

    public static String getTokenForBookit(String role){
        String userName="";
        String password="";
        if(role.toLowerCase().contains("lead")){
            userName=ConfigurationReader.getProperty("team.leader.email");
            password=ConfigurationReader.getProperty("team.leader.password");
        }else if(role.toLowerCase().contains("teacher")){
            userName=ConfigurationReader.getProperty("teacher.email");
            password=ConfigurationReader.getProperty("teacher.password");
        }else if(role.toLowerCase().contains("member")){
            userName=ConfigurationReader.getProperty("team.member.email");
            password=ConfigurationReader.getProperty("team.member.password");

        }else {
            throw new RuntimeException(("Invalid user type"));
        }
        Response response=given()
                .queryParam("email",userName)
                .queryParam("password",password)
                .when()
                .get("/sing");
        return response.jsonPath().getString("accessToken");
    }
    public static boolean hasDuplicates(List list){
        boolean hasDuplicates=true;
        for (int i=0;i<list.size();i++){
            for(int j=0;j<list.size();j++){
                if(list.get(i).equals(list.get(j))&&i!=j){
                    hasDuplicates=true;
                    System.out.println("Dublicate: "+list.get(i));
                    break;
                }
            }
        }
        return hasDuplicates;
    }
}
