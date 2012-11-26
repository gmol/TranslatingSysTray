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
	private String translation = "";
	
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
	
	@Override
	public String toString() {
//   "entryLabel": "go verb  MOVE/TRAVEL ",
//   "entryUrl": "http://dictionary.cambridge.org/dictionary/british/go_1",
//   "entryId": "go_1"
		String str = new String();
		// EntryId: go_1, Entry Label: go verb  MOVE/TRAVEL, Translated: TRUE/FALSE; 
		str = "Entry Id: " + entryId + ". Entry Label: " + entryLabel + ", Translated: " + (translation.isEmpty() ? "FALSE" : "TRUE");
		
		return str;
	}
		
}
