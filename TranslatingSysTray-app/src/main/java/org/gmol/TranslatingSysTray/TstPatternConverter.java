package org.gmol.TranslatingSysTray;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public class TstPatternConverter extends PatternConverter {
	
	  int min = -1;
	  int max = 0x7FFFFFFF;
	  boolean leftAlign = false;
	  
	  static final int RELATIVE_TIME_CONVERTER = 2000;
	  static final int THREAD_CONVERTER = 2001;
	  static final int LEVEL_CONVERTER = 2002;
	  static final int NDC_CONVERTER = 2003;
	  static final int MESSAGE_CONVERTER = 2004;



	public TstPatternConverter() {
		// TODO Auto-generated constructor stub
	}

	public TstPatternConverter(FormattingInfo fi) {
		super(fi);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String convert(LoggingEvent event) {
//	      switch(type) {
//	      case RELATIVE_TIME_CONVERTER:
//		return (Long.toString(event.timeStamp - LoggingEvent.getStartTime()));
//	      case THREAD_CONVERTER:
//		return event.getThreadName();
//	      case LEVEL_CONVERTER:
//		return event.getLevel().toString();
//	      case NDC_CONVERTER:
//		return event.getNDC();
//	      case MESSAGE_CONVERTER: {
//		return event.getRenderedMessage();
//	      }
//	      default: return null;
//	      }
//	    }
		return null;
	}

	@Override
	public void format(StringBuffer sbuf, LoggingEvent e) {
		   String s = convert(e);

		    if(s == null) {
		      if(0 < min)
			spacePad(sbuf, min);
		      return;
		    }

		    int len = s.length();

		    if(len > max)
		      sbuf.append(s.substring(0 - max));
		    else if(len < min) {
		      if(leftAlign) {	
			sbuf.append(s);
			spacePad(sbuf, min - len);
		      }
		      else {
			spacePad(sbuf, min - len);
			sbuf.append(s);
		      }
		    }
		    else
		      sbuf.append(s);
	}
}
