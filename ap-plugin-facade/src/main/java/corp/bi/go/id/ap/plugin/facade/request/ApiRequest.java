package corp.bi.go.id.ap.plugin.facade.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class ApiRequest<T> {
    private T data;
    private HashMap<String, Object> extParams;

    public static <T> ApiRequest<T> fromJson(String jsonString, Class<T> clazz) {
        return new Gson().fromJson(jsonString, TypeToken.getParameterized(ApiRequest.class, clazz).getType());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
