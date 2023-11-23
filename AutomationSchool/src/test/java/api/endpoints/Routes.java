package api.endpoints;


/*
*   Swagger URI --> https://petstore.swagger.io/
*
* */
public class Routes {

    public static  String baseUrl="https://petstore.swagger.io/v2";

    public static String dogBaseUrl="https://dog.ceo/api/breeds";
    public static String subBaseUrl="https://dog.ceo/api/breed";
    // User module

    public static  String postUrl=baseUrl+"/user";
    public static  String getUrl=baseUrl+"/user/{username}";
    public static  String updateUrl=baseUrl+"/user/{username}";
    public static  String deleteUrl=baseUrl+"/user/{username}";

    public static String getDogUrl=dogBaseUrl+"/list/{breed}";
    public static String getSubUrl=subBaseUrl+"/{sub}/list";
    public static String getRandomDogUrl=subBaseUrl+"/image/{image}";

}
