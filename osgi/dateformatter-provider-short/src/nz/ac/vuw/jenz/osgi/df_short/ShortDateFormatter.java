package nz.ac.vuw.jenz.osgi.df_short;

import java.text.DateFormat;
import java.util.Date;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;

/**
 * Component providing a data formatter service.
 * @author jens dietrich
 */
public class ShortDateFormatter implements DateFormatter {

	public String format(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
	}

}
