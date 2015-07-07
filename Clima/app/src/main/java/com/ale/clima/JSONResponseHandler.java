package com.ale.clima;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

/**
 * Created by LUIS on 05/07/2015.
 */
public class JSONResponseHandler implements ResponseHandler<Clima> {

    @Override
    public Clima handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        Clima result = new Clima(0, 0, 0, 0, 0);
        String JSONResponse = new BasicResponseHandler().handleResponse(response);
        try {
            JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();

            JSONObject mainObject=  responseObject.getJSONObject("main");
            result.setTempActual(mainObject.getDouble("temp"));
            result.setTempMaxima(mainObject.getDouble("temp_max"));
            result.setTempMinima(mainObject.getDouble("temp_min"));
            result.setHumedad(mainObject.getInt("humidity"));
            result.setPresion(mainObject.getInt("pressure"));

            JSONArray weatherSet = responseObject.getJSONArray("weather");
            JSONObject weather = (JSONObject) weatherSet.get(0);
            result.setDescripcion(weather.getString("description"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
