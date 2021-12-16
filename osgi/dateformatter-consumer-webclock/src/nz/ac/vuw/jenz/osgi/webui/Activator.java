/*
 * Created on Fri Mar 02 13:42:43 NZDT 2007
 */
package nz.ac.vuw.jenz.osgi.webui;

import java.util.*;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/**
 * A simple web ui using the dataformatter service.
 * @author jens dietrich
 */
public class Activator implements BundleActivator {
	public static BundleContext bc = null;
	static LinkedHashMap<ServiceReference,DateFormatter> knownDateFormatters = new LinkedHashMap<ServiceReference,DateFormatter>();
	private ServiceListener httpSListener = null,dateformatterSListener = null;
	
	public void start(BundleContext c) throws Exception {
		this.bc = c;	
		ServiceReference sr  =  bc.getServiceReference(HttpService.class.getName());
		registerServlet(sr);
		
		ServiceReference[] srr = bc.getServiceReferences(DateFormatter.class.getName(),null);
		if (srr!=null) {
			for (ServiceReference s:srr)
				this.registerDateFormatter(s);
		}

		httpSListener = new ServiceListener() {
			public void serviceChanged(ServiceEvent e) {
				if (e.getType()==ServiceEvent.REGISTERED)
					registerServlet(e.getServiceReference());
				else if (e.getType()==ServiceEvent.UNREGISTERING)
					unregisterServlet(e.getServiceReference());
			}
		};
		String filter = "(objectclass="+HttpService.class.getName()+")";
		this.bc.addServiceListener(httpSListener, filter);
		
		dateformatterSListener = new ServiceListener() {
			public void serviceChanged(ServiceEvent e) {
				if (e.getType()==ServiceEvent.REGISTERED)
					registerDateFormatter(e.getServiceReference());
				else if (e.getType()==ServiceEvent.UNREGISTERING)
					unregisterDateFormatter(e.getServiceReference());
			}
		};
		filter = "(objectclass="+DateFormatter.class.getName()+")";
		this.bc.addServiceListener(dateformatterSListener, filter);

	}
	private void registerDateFormatter(ServiceReference sr) {
		if (sr!=null){
			System.out.println("registering date formatter service in web clock");
			DateFormatter formatter = (DateFormatter)bc.getService(sr);
			knownDateFormatters.put(sr,formatter);
		}
	}
	private void unregisterDateFormatter(ServiceReference sr) {
		if (sr!=null) {
			System.out.println("unregistering date formatter service from web clock");
			knownDateFormatters.remove(sr);
		}
	}
	private void registerServlet(ServiceReference sr) {
		try {
			if(sr != null) {
				HttpService http = (HttpService)bc.getService(sr);
				if(http != null) {
					System.out.println("registering date servlet");
					Hashtable props   = new Hashtable();
					props.put("alias","/date");
					http.registerServlet("/date", new DateServlet(), props,null);
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		} 
	}
	private void unregisterServlet(ServiceReference sr) {

		if(sr != null) {
			HttpService http = (HttpService)bc.getService(sr);
			if(http != null) {
				System.out.println("unregistering date servlet");
				http.unregister("/date");
			}
		}
	}	
	public void stop(BundleContext bc) throws Exception {

		ServiceReference sr  =  bc.getServiceReference(HttpService.class.getName());
		unregisterServlet(sr);
		
		// stop listener
		this.bc.removeServiceListener(this.httpSListener);
		this.bc.removeServiceListener(this.dateformatterSListener);
		
		// we assume there is only one instance of this service running !!
		this.knownDateFormatters.clear();
		
		// deref context
		Activator.bc = null;
		
	}
}