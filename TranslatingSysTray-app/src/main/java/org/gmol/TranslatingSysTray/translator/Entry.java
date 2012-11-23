package org.gmol.TranslatingSysTray.translator;

class Entry {
	Entry(String entryId, String entryLabel) {
		this.entryId = entryId;
		this.entryLabel = entryLabel;
	}
	//
	private String entryLabel;
	//
	private String entryId;
	//
	private String translation;
	
	String getEntryLabel() {
		return entryLabel;
	}
	String getEntryId() {
		return entryId;
	}
	String getTranslation() {
		return translation;
	}
	void setTranslation(String translation) {
		this.translation = translation;
	}
		
}
