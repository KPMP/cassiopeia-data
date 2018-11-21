package org.kpmp.slides;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Slide {

	@Id
	private String id;
	private String slideName;
	private String stain;

	public String getStain() {
		return stain;
	}

	public void setStain(String stain) {
		this.stain = stain;
	}

	public String getSlideName() {
		return slideName;
	}

	public void setSlideName(String slideName) {
		this.slideName = slideName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
