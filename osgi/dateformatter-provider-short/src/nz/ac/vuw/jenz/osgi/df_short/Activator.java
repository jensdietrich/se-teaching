package nz.ac.vuw.jenz.osgi.df_short;

import java.util.Hashtable;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Component providing a data formatter service.
 * @author jens dietrich
 */
public class Activator implements BundleActivator  {
	public static BundleContext bc = null;
	private ServiceRegistration registration =null;
	public void start(BundleContext bc) throws Exception {
		Activator.bc = bc;		
		DateFormatter service = new ShortDateFormatter();
		registration = bc.registerService(DateFormatter.class.getName(),service,new Hashtable());
		System.out.println("Service registered: " + service);

	}
	public void stop(BundleContext bc) throws Exception {
		registration.unregister();
		Activator.bc = null;
	}
}
