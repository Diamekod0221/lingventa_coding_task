# lingventa_coding_task

A simple spring boot microservice that allows getting data from OpenMeteoAPI. 

The server shares an endpoint which users can call with longitude and latitude, to get specfific past data from the OpenMeteoApi in a Json format. The data from the MeteoAPI is serialized into java objects, worked over and serialized into a json again.
The server comes embedded with H2 in-memory DB and saves successfull requests. Basic validation has been implemented.


