package hello.filters.post;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimplePostFilter extends ZuulFilter {

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		Instant stopTime = Instant.now();
		RequestContext ctx = RequestContext.getCurrentContext();
		Instant startTime= (Instant) ctx.get("startTime");
		long TimeinMilliSec = ChronoUnit.MILLIS.between(startTime, stopTime);
		System.out.println("Time taken in MILISEC->"+TimeinMilliSec);  
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

}
