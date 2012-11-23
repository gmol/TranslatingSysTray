package org.gmol.TranslatingSysTray.translator;

public class TranslatorEx extends Exception {

	private static final long serialVersionUID = -7184495451329503864L;
	
	TranslatorEx(Exception ex) {
		this(ex.getMessage(), ex.getCause());
	}
	
    TranslatorEx(String msg) {
    	super(msg);
    }
    TranslatorEx(String msg, Throwable cause) {
    	super(msg, cause);
    }

    TranslatorEx(Throwable cause) {
    	super(cause);
    }

}
