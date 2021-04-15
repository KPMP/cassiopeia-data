package org.kpmp.slides;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kpmp.logging.LoggingService;
import org.kpmp.shibboleth.ShibbolethUser;
import org.kpmp.shibboleth.ShibbolethUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SlideController {

	private SlideService slideService;
	private LoggingService logger;
	private ShibbolethUserService shibbolethUserService;

	@Autowired
	public SlideController(SlideService slideService, LoggingService logger,
			ShibbolethUserService shibbolethUserService) {
		this.slideService = slideService;
		this.logger = logger;
		this.shibbolethUserService = shibbolethUserService;
	}

	@RequestMapping(value = "/v1/slides/{kpmpId}", method = RequestMethod.GET)
	public @ResponseBody List<Slide> getSlidesForParticipant(@PathVariable String kpmpId, HttpServletRequest request) {
		logger.logInfoMessage(this.getClass(), "Getting slides for participant " + kpmpId, request);
		return slideService.getSlidesForParticipant(kpmpId);
	}

	@RequestMapping(value = "/v1/slides/summary", method = RequestMethod.GET)
	public @ResponseBody List<Participant> getAllParticipants(HttpServletRequest request) {
		logger.logInfoMessage(this.getClass(), "Getting all participants", request);
		return slideService.getAllParticipants();
	}

	@RequestMapping(value = "/v1/slides", method = RequestMethod.GET)
	public @ResponseBody List<Slide> getSlidesForParticipant(HttpServletRequest request) {
		ShibbolethUser user = shibbolethUserService.getUser(request);
		logger.logInfoMessage(this.getClass(), user, request.getRequestURI(), "Getting slides for user: " + user.toString());
		Enumeration<String> headerNames = request.getHeaderNames();
		StringBuilder headerInfo = new StringBuilder();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerInfo.append(headerName + " = " + request.getHeader(headerName) + "\n");
		}

		logger.logInfoMessage(this.getClass(), headerInfo.toString(), request);

		return slideService.getSlidesForParticipant(user.getKpmpId());
	}

}
