package nz.ac.vuw.jenz.osgi.consoleclock;

import java.util.*;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

/**
 * A simple dataformatter service client printing timestamps to the console.
 * @author jens dietrich
 */
public class Activator implements BundleActivator  {
	public static BundleContext bc = null;
	private LinkedHashMap<ServiceReference,DateFormatter> knownDateFormatters = new LinkedHashMap<ServiceReference,DateFormatter>();
	private Thread thread = null;
	private boolean onOff = false;
	private ServiceListener httpSListener = null,dateformatterSListener = null;
	
	public void start(BundleContext c) throws Exception {
		this.bc = c;	

		ServiceReference[] srr = bc.getServiceReferences(DateFormatter.class.getName(),null);
		if (srr!=null) {
			for (ServiceReference s:srr)
				this.registerDateFormatter(s);
		}
	
		dateformatterSListener = new ServiceListener() {
			public void serviceChanged(ServiceEvent e) {
				if (e.getType()==ServiceEvent.REGISTERED)
					registerDateFormatter(e.getServiceReference());
				else if (e.getType()==ServiceEvent.UNREGISTERING)
					unregisterDateFormatter(e.getServiceReference());
			}
		};
		String filter = "(objectclass="+DateFormatter.class.getName()+")";
		this.bc.addServiceListener(dateformatterSListener, filter);
				
		onOff = true;
		Runnable run = new Runnable() {
			public void run() {
				while (onOff) {
					DateFormatter dateFormatter = null;
					if (knownDateFormatters.size()>0) {
						// choose last formatter - quick hack
						for (DateFormatter f:knownDateFormatters.values())
							dateFormatter = f;
					}
					System.out.println(dateFormatter==null?(""+new Date()+" (no date formatter available)"):dateFormatter.format(new Date()));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread = new Thread(run);
		thread.start();
		

	}
	
	private void registerDateFormatter(ServiceReference sr) {
		if (sr!=null){
			System.out.println("registering date formatter service in console clock");
			DateFormatter formatter = (DateFormatter)bc.getService(sr);
			knownDateFormatters.put(sr,formatter);
		}
	}
	private void unregisterDateFormatter(ServiceReference sr) {
		if (sr!=null) {
			System.out.println("unregistering date formatter service from console clock");
			knownDateFormatters.remove(sr);
		}
	}
	
	public void stop(BundleContext bc) throws Exception {
		// stop thread
		onOff = false;
		thread.join();
		thread = null;	
		
		// stop listener
		this.bc.removeServiceListener(this.dateformatterSListener);
		
		knownDateFormatters.clear();
		
		// deref context
		Activator.bc = null;
		
	}
}
