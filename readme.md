## spring-hystrix-demo - _what it does_
This repository demonstrates application of Netflix's Hystrix circuit breaker. 

#### How to run

#### How to test this repo 
- Call `http://localhost:8080/numbers` with only this server and 'Invoked' will be printed to maximum of 3 
times before it stops being printed (Each print means an attempt to call API happened)
  - This is controlled by a `Hystrix` property called `circuitBreaker.requestVolumeThreshold` defined in `LuckyFacade`

#### Theoretical Takeaways
1. `Hystrix`, which is a `circuit breaker` is not related to `retries`. If a method wrapped in `@HystrixCommand` throws
an exception it'll stop invoking that method(and instead invoke its fallback if specified) until a specific time has 
passed, causing the circuit to be closed again.

#### Technical Takeaways
1. `@HystrixCommand` wraps a method in a proxy to monitor its status. Only works in a class annotated with `@Component` or `@Service`
1. `@HystrixCommand` accepts a `fallbackMethod` argument. This fallback method should be in the same class as where it is used. 
1. `@ExceptionHandler` in a `@ControllerAdvice` annotated class will **not** be able to intercept errors throw in `@HystrixCommand` annotated method
1. Spring's `spring-cloud-starter-netflix-hystrix` is a wrapper around the actual `Hystrix` contrib library. Hence, properties
cannot be defined in `application.yaml|properties` but is passed directly to a `@HystrixCommand`
1. By default, each Hystrix `command` is its own group unless if `groupKey` parameter is supplied to `@HystrixCommand` 