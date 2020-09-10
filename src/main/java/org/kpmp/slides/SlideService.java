package org.kpmp.slides;

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

	public List<Slide> getSlidesForPaarticipant(String kpmpId) {
		Participant patient = participantRepo.findByKpmpId(kpmpId);
		if (patient != null) {
			return patient.getSlides();
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
