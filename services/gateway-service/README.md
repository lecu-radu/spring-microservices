# Client service light

This module is a carbon copy of contact-service. It tries to show how you can use Zuul proxy to expose data from rest services.

You can mace any call by using the name of the service and the url.
For example:
instead of calling <url>/bookmarks/jlong you can call <url>/bookmark-service/bookmarks/jlong
This happens thanks to the inverse proxy Zuul and load balancer used by it called Ribbon. In addition Zuul brings Hystrix for load balancing.