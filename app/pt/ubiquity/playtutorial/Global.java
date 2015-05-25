package pt.ubiquity.playtutorial;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;

public class Global extends GlobalSettings {

	private static ApplicationContext applicationContext;

	@Override
	public void onStart(Application application) {
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	}
	
	@Override
	public void onStop(Application app) {

	}
	
	public static void refreshContext(){
		((ConfigurableApplicationContext)applicationContext).refresh();
	}

	@Override
	public <A> A getControllerInstance(Class<A> clazz) throws Exception {
		try {
			return applicationContext.getBean(clazz);
		} catch (Exception ex) {
			return super.getControllerInstance(clazz);
		}
	}

	@Override
	public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
		return Promise.<SimpleResult> pure(internalServerError("Internal Server Error"));
	}

	@Override
	public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
		return Promise.<SimpleResult> pure(notFound(request.uri() + " not found"));
	}

	@Override
	public Promise<SimpleResult> onBadRequest(RequestHeader request, String error) {
		return Promise.<SimpleResult> pure(badRequest(error));
	}

}
