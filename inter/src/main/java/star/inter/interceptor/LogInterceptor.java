package star.inter.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LogInterceptor implements AsyncHandlerInterceptor  {
    private final ObjectMapper objectMapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
        System.out.println(">> Response \n" + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(cachingResponse.getContentAsByteArray())));
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
    	System.out.println("--> ["+request.getRequestURI()+"] END ");
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> headerMap = new HashMap<String, String>();
    	Map<String, String> bodyMap   = new HashMap<String, String>();

    	Enumeration<String> headerEm = request.getHeaderNames();

        while(headerEm.hasMoreElements()) {
    		String name = headerEm.nextElement() ;
    		String val = request.getHeader(name) ;

    		headerMap.put(name, val);
    	}

		Set<Entry<String,String[]>> bodyEntrySet = request.getParameterMap().entrySet();
		for(Entry<String, String[]> entry : bodyEntrySet) {
			bodyMap.put(entry.getKey(), entry.getValue()[0]);
		}

        String header = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(headerMap);
		String body = null;

        if(bodyMap.isEmpty()) {
            ContentCachingRequestWrapper cachingRequestWrapper = (ContentCachingRequestWrapper) request;
            body = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(cachingRequestWrapper.getInputStream()));
        } else {
            body = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bodyMap);
        }

    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
    	System.out.println("--> ["+request.getRequestURI()+"] START ");
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
    	System.out.println(">> HEADER \n" + header);
    	System.out.println(">> BODY \n" + body);
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");

        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}
