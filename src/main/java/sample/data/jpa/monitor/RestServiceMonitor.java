package sample.data.jpa.monitor;

import java.util.logging.Logger; 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestServiceMonitor {

	private static Logger logger = Logger.getLogger(RestServiceMonitor.class.getName());
	
	@Before("execution(* sample.data.jpa.web.*.*(..))")
	public void logRestServiceAccess(JoinPoint joinPoint) {
		
		logger.info("# Class Name: " + joinPoint.getTarget().getClass().getName());
		logger.info("# Method Name: " + joinPoint.getSignature().getName());
	}

}