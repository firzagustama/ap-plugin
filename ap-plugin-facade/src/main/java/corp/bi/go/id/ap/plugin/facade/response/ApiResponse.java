package corp.bi.go.id.ap.plugin.facade.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class ApiResponse<T> {
    private String responseCode;
    private String responseMessage;
    private T responseData;
    private HashMap<String, Object> extParams;

    public static <T> ApiResponse<T> fromJson(String jsonString, Class<T> clazz) {
        return new Gson().fromJson(jsonString, TypeToken.getParameterized(ApiResponse.class, clazz).getType());
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public HashMap<String, Object> getExtParams() {
        return extParams;
    }

    public void setExtParams(HashMap<String, Object> extParams) {
        this.extParams = extParams;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
