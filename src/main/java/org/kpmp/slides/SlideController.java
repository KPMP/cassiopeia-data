package org.kpmp.slides;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SlideController {

	@RequestMapping(value="/v1/slides/{kpmpId}", method=RequestMethod.GET)
	public @ResponseBody PatientSlides getSlidesForPatient(@PathVariable String kpmpId) {
		return null;
	}
	
}
