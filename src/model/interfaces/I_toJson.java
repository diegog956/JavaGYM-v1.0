package model.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface I_toJson {

    void fromJson(JSONObject jo)  throws JSONException;
    JSONObject toJsonObj() throws JSONException;
}
