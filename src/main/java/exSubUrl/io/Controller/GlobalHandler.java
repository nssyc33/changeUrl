package exSubUrl.io.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		mav.addObject("exception", e);
		return mav;
	}
}
