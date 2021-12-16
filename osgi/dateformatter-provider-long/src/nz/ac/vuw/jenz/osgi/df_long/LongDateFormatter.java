package nz.ac.vuw.jenz.osgi.df_long;

import java.text.DateFormat;
import java.util.Date;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;

/**
 * Component providing a data formatter service.
 * @author jens dietrich
 */
public class LongDateFormatter implements DateFormatter {

	public String format(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(date);
	}

}
