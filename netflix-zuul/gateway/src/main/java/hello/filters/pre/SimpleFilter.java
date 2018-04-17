package hello.filters.pre;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public String filterType() {
    return "pre";    
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
	  boolean isNonMobile = true;
	  RequestContext ctx= getCurrentContext();
	  String param = ctx.getRequest().getParameter("source");
	  if(param !=null && param.equals("mobile")) {
		  return false;
	  }	  
    return isNonMobile;
  }

  @Override
  public Object run() {
	 Instant startTime = Instant.now();
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.set("startTime", startTime); 
    HttpServletRequest request = ctx.getRequest();
    log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

    return null;
  }

}
