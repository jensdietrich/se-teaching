import java

from Class cl
where cl.getName()="ClassB"
select cl as a_class,cl.getASupertype+() as super_class
// note that alias cannot be keyword