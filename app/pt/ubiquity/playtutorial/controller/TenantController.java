package pt.ubiquity.playtutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.Result;
import pt.ubiquity.playtutorial.exeception.NotFoundException;
import pt.ubiquity.playtutorial.model.Tenant;
import pt.ubiquity.playtutorial.service.TenantService;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class TenantController extends ApplicationController {

	protected static TenantService tenentService;
	
	private static ALogger log = play.Logger.of("application");
	
	@Autowired(required = true)
	private void setTenentService(TenantService tenentService) {
		TenantController.tenentService = tenentService;
	}
	
	public synchronized static Result add() {
		try {
			JsonNode json = request().body().asJson();
			if (json != null) {
				Tenant tenent = Json.fromJson(json, Tenant.class);
				Json.toJson(tenentService.add(tenent));
				log.info("Tenent " + tenent.getName() + " created.");
				return ok();
			} else {
				return badRequest("Invalid Json data");
			}
		} catch (Exception e) {
			return badRequest("Could not execute operation.");
		}
	}

	public synchronized static Result getAll() {
		try {
			List<Tenant> tenants = tenentService.getAll();
			return ok(Json.toJson(tenants));
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}

	public synchronized static Result get(String id) {
		try {
			return ok(Json.toJson(tenentService.getById(id)));
		} catch (NotFoundException e) {
			return notFound(e.getMessage());
		}
	}

	public synchronized static Result delete(String id) {
		try {
			tenentService.delete(id);
			log.info("Tenent with the id " + id + " deleted.");
			return ok();
		} catch (NotFoundException e) {
			return notFound(e.getMessage());
		}
	}

	public synchronized static Result update() {
		try {
			JsonNode json = request().body().asJson();
			if (json != null) {
				Tenant tenent = Json.fromJson(json, Tenant.class);
				tenentService.update(tenent);
				log.info("Tenent " + tenent.getName() + " updated.");
			} else {
				badRequest("Json data invalid.");
			}
			return ok();
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
	}

}
