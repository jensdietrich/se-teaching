# Using MBeans

The example illustrates the use of mbean / jxm.

`Main` will start a simple scenario where a number of *producers* put random strings into a blocking queue 
to be processed by some *consumers*. Consumers dont do anything useful, processing is mocked by 
pausing the thread. 

Each consumer and consumer runs in its own thread, and the threads are named accordingly 
`consumer*` and `producer*`. Once started, the program runs forever (stop it by killing the process).

The application has three parameters that can be used to finetune performance -- the consumer count,
the producer count, and the maximum size of the queue. Those are defined as static variables in `Main`. The challenge is 
to finetune those to achieve maximum throughput. 

Once the program starts, use a JMX client such as [VisualVM](https://visualvm.github.io/) to connect to the application.
Using the thread tab, checkout the consumer and producer threads. Sleeping is good here (this simulates 
active in this exampleas `Thread::sleep` is used to mock this). But parking is bad -- this means that 
thread are waiting idle for either space to put new objects in the queue for processing (for producers),
or for new objects in the queue to be picked up for processing (for consumers). 

The custom mbean used in this example allows us to see what is going on in the queue. When using
VisualVM, this requires the installation of the mbeans plugin. Then we can inspect the mbean `nz.ac.vuw.jenz.mbean::queue`
with the `size` property to see how many objects are in the queue.  If this number is constantly `Main::queueMaxSize`, then we have too many producers. If it is constantly
`0`, we have too many consumers. 