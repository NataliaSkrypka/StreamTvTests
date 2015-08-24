package ua.net.streamtv.steps;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

/**
 * Created by nskrypka on 8/20/2015.
 */
public class ApiSteps {
    private String createUrl;
    private String login;
    private String password;
    private String loginUrl;
    private String readUrl;
    private String searchUrl;
    private String updateUrl;
    private String deleteUrl;

    public ApiSteps(String createUrl, String login, String password, String loginUrl, String readUrl, String searchUrl, String updateUrl, String deleteUrl) {
        this.createUrl = createUrl;
        this.login = login;
        this.password = password;
        this.loginUrl = loginUrl;
        this.readUrl = readUrl;
        this.searchUrl = searchUrl;
        this.updateUrl = updateUrl;
        this.deleteUrl = deleteUrl;
    }

    public String addSportsman(String sessionId, Map<String, String> sportsman) throws JSONException {
        JSONObject jsonObject = new JSONObject(sportsman);
        Response response = given().contentType(JSON).cookie("PHPSESSID", sessionId).body(jsonObject.toString()).
                when().post(createUrl);
        response.then().statusCode(200);
        JSONObject jsonObject1 = new JSONObject(response.asString());
        return jsonObject1.get("id").toString();
    }

    public String login() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", login);
        credentials.put("password", password);
        JSONObject jsonObject = new JSONObject(credentials);
        Response response = given().contentType(JSON).body(jsonObject.toString()).
                when().post(loginUrl);
        response.then().statusCode(200);
        String sessionId = response.cookie("PHPSESSID");
        return sessionId;
    }

    public JSONObject readSportsman(String sessionId, String id) throws JSONException {
        Response response = given().cookie("PHPSESSID", sessionId).
                when().get(readUrl, id);
        response.then().statusCode(200);
        return new JSONObject(response.asString());
    }

    public String searchForSportsman(String sessionId, String searchParameter) throws JSONException {
        String sportsmanId = "";
        Response response = given().cookie("PHPSESSID", sessionId).
                when().get(searchUrl, searchParameter);
        response.then().statusCode(200);
        JSONArray rows = new JSONObject(response.asString()).getJSONArray("rows");
        if (rows.length() != 0) {
            sportsmanId = rows.getJSONObject(0).getString("id_wrestler");
        }
        return sportsmanId;
    }

    public void updateSportsman(String sessionId, Map<String, String> sportsman) throws JSONException {
        JSONObject jsonObject = new JSONObject(sportsman);
        Response response = given().contentType(JSON).cookie("PHPSESSID", sessionId).body(jsonObject.toString()).
                when().post(updateUrl);
        response.then().statusCode(200).body("result", not(containsString("Invalid query")));
    }

    public void deleteSportsman(String sessionId, String sporsmanId) {
        Response response = given().cookie("PHPSESSID", sessionId).
                when().delete(deleteUrl, sporsmanId);
        response.then().statusCode(200);
    }

    public void deleteAllProfiles(String fio) throws JSONException {
        String sessionId = login();
        String sportsmanId;
        while (!"".equals(sportsmanId = searchForSportsman(sessionId, fio))) {
            deleteSportsman(sessionId, sportsmanId);
        }
    }
}
