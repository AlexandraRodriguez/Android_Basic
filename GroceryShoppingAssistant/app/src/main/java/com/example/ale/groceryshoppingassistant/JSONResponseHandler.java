package com.example.ale.groceryshoppingassistant;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class JSONResponseHandler implements ResponseHandler<Food> {
    @Override
    public Food handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        Food result = new Food("", 0, 0, "");
        String JSONResponse = new BasicResponseHandler().handleResponse(response);
        try{
            JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();

            //JSONObject food =  responseObject.getJSONObject("main");

            JSONArray mainArray = responseObject.getJSONArray("hits");
            JSONObject food = (JSONObject) mainArray.get(0);
            JSONObject fields = (JSONObject) food.get("fields");

            result.setName(fields.getString("item_name"));
            result.setCalories(fields.getInt("nf_calories"));
            result.setFat(fields.getInt("nf_total_fat"));
            result.setServing(fields.getString("nf_serving_size_unit"));

            /*JSONObject mainObject=  responseObject.getJSONObject("hits");
            */
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
