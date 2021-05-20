package org.kpmp.slides;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideService {

	private ParticipantRepository participantRepo;

	@Autowired
	public SlideService(ParticipantRepository participantRepo) {
		this.participantRepo = participantRepo;
	}

	public List<Slide> getSlidesForParticipant(String kpmpId) {
		Participant patient = participantRepo.findByKpmpId(kpmpId);
		if (patient != null) {
			List<Slide> slides = patient.getSlides();
			List<Slide> slidesToReturn = new ArrayList<Slide>();
			for (Slide slide : slides) {
				if (!(slide.getStain().getType().equals("tol") || slide.getStain().getType().equals("frz")
						|| slide.getStain().getType().equals("ihc"))) {
					slidesToReturn.add(slide);
				}
			}
			return slidesToReturn;
		}
		return Collections.emptyList();
	}

	public List<Participant> getAllParticipants() {
		List<Participant> participants = participantRepo.findByOrderByKpmpIdAsc();
		if (participants != null) {
			return participants;
		}
		return Collections.emptyList();
	}
}
