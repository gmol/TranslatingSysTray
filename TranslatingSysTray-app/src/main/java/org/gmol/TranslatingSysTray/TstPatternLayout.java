package org.gmol.TranslatingSysTray;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
// org.gmol.TranslatingSysTray.TstPatternLayout
public class TstPatternLayout extends PatternLayout {
	
		public TstPatternLayout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TstPatternLayout(String pattern) {
		super(pattern);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PatternParser createPatternParser(String pattern) {
		// TODO Auto-generated method stub
		return super.createPatternParser(pattern);
		
	}

}
