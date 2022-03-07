## README

An OSGi-based scenario showing how a service is defined, and then provided and consumed by components (OSGi bundles). There are 5 subprojects each defining an OSGi bundle (component) - the *spec*  (only defining the commonly used service interface `DateFormatter`), two *service providers* and two *service consumers*.

To build the projects, run `ant` in each folder, this will create the deployable components (as jars) in the respective `build/` folders. 

To deploy those components, use a container (I recommend [knopflerfish](https://www.knopflerfish.org/), it provides a nice visual console to manage components) and install and start the components. Make sure to deploy the spec component first, because it defines the service and all other components have runtime dependencies to it.

Further information is provied in the README files in the subprojects.
 
 
 
