# SBNetFlixTrain
netflix eureka, hystrix, zull and ribbon demo

account-service contains demo code to return account data for customer

customer-service will invoke account-service

discover-service is for eureka

hystrix-dashboard included dashboard for hystrix where we can view hystrix streams.

Lastly, there are two MMP projects for zuul and ribbon
ribbon -> is client side load balancer  and demo projects in that
zuul -> is gateway for ruoute/proxy/filtering etc.. and demo projects in that.
