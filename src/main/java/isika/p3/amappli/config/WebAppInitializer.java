package isika.p3.amappli.config;

import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(@NonNull ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
	    appContext.register(WebMvcConfig.class);
	    	      
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
	            "SpringDispatcher", new DispatcherServlet(appContext));
	    
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/"); 
	}
    
}
