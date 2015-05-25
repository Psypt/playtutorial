package pt.ubiquity.playtutorial.controller;

import play.mvc.Controller;
import play.mvc.Result;
import com.fasterxml.jackson.databind.JsonNode;

@org.springframework.stereotype.Controller
public class ApplicationController extends Controller {
		
	protected static void setResponseHeader() {
		response().setContentType("application/json");	
		response().getHeaders().put("Access-Control-Allow-Origin", "*");
		response().getHeaders().put("Access-Control-Max-Age", "1728000");
		response().getHeaders().put("Access-Control-Allow-Credentials", "true");
		response().getHeaders().put("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response().getHeaders().put("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, Accept, Authorization, Origin");
	}
	
	public static Status ok() {
		setResponseHeader();
        return Controller.ok();
    }
	
	public static Status ok(String content) {
		setResponseHeader();
        return Controller.ok(content);
	}

    public static Status ok(JsonNode content) {
    	setResponseHeader();
        return Controller.ok(content);
    }

    public static Status notFound() {
    	setResponseHeader();
        return Controller.notFound();
    }
    
    public static Status notFound(String content) {
    	setResponseHeader();
        return Controller.notFound(content);
    }

    public static Status notFound(JsonNode content) {
    	setResponseHeader();
        return Controller.notFound(content);
    }
    
    public static Status badRequest() {
    	setResponseHeader();
        return Controller.badRequest();
    }
    
    public static Status badRequest(String content) {
    	setResponseHeader();
        return Controller.badRequest(content);
    }
    
    public static Status badRequest(JsonNode content) {
    	setResponseHeader();
        return Controller.badRequest(content);
    }
    
    public static Status conflict() {
    	setResponseHeader();
        return Controller.status(CONFLICT);
    }
    
    public static Status conflict(String content) {
    	setResponseHeader();
        return Controller.status(CONFLICT,content);
    }
    
    public static Status conflict(JsonNode content) {
    	setResponseHeader();
        return Controller.status(CONFLICT,content);
    }
    
    public static Status notAcceptable() {
    	setResponseHeader();
        return Controller.status(NOT_ACCEPTABLE);
    }
    
    public static Status notAcceptable(String content) {
    	setResponseHeader();
        return Controller.status(NOT_ACCEPTABLE,content);
    }
    
    public static Status notAcceptable(JsonNode content) {
    	setResponseHeader();
        return Controller.status(NOT_ACCEPTABLE,content);
    }
    
    public static Status forbidden() {
    	setResponseHeader();
        return Controller.forbidden();
    }
    
    public static Status forbidden(String content) {
    	setResponseHeader();
        return Controller.forbidden(content);
    }
    
    public static Status forbidden(JsonNode content) {
    	setResponseHeader();
        return Controller.forbidden(content);
    }
    
    public static Result getOptions(String url) {
    	return ok();
    }
    
}