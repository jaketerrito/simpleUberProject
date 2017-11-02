# simpleUberProject
A simplified version of uber, mimicking the basic functionality of the uber app.
Afer complining the source code, the UberSimulation runs from a set of commands given in "sim.txt".

These commands are given in the format:  
     PRICERATE TIMERATE  
     Driver name balance rating [x y]  
     Client name balance [x y]  
     Request clientname [x y]  

**Driver** initializes a new driver with the given name, balance, and rating along with an optional starting location.  
**Client** initializes a new client with the given name and balance along with an optional starting location.  
**Request** starts a trip request from the given client to an optional location.  
Leaving the location field empty or giving an out of bounds location will result in a randomly assigned location.
The pricerate and time rate must be on the first line of "sim.txt", but other wise the order of commands doesn't matter.

When running the simulation you'll be prompted for input responding 'y' or 'n' for whether the driver accepts a trip
request and providing a rating for drivers from 0 to 5.0.

Project pages can be found [here](jaketerrito.github.io/simpleUberProject).
