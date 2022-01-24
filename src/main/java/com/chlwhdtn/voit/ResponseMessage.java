package com.chlwhdtn.voit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResponseMessage {
	
	public boolean sucess;
	public String message;
	public Object data;
	
	public ResponseMessage(boolean sucess, String message) {
		this.sucess = sucess;
		this.message = message;
	}
	
	public ResponseMessage(boolean sucess, String message, Object data) {
		this.sucess = sucess;
		this.message = message;
		this.data = data;
	}
	
	@Override
	public String toString() {
		JsonObject obj = new JsonObject();
		obj.addProperty("success", sucess);
		obj.addProperty("message", message);
		if(data != null) {
			obj.addProperty("data", new Gson().toJson(data));
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

}
