package study.spring.springhelper.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.helper.WebHelper;
import uap_clj.java.api.Browser;
import uap_clj.java.api.Device;
import uap_clj.java.api.OS;

@Slf4j
public class AppInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	WebHelper webHelper;
    long startTime=0, endTime=0;

    /**
     * Controller 실행 요청 전에 수행되는 메서드
     * 클라이언트의 요청을 컨트롤러에 전달 하기 전에 호출된다.
     * return 값으로 boolean 값을 전달하는데 false 인 경우 controller를 실행 시키지 않고 요청을 종료한다.
     * 보통 이곳에서 각종 체크작업과 로그를 기록하는 작업을 진행한다.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 실행 직전에 현재 시각을 저장한다.
        startTime = System.currentTimeMillis();

        // GET방식인지, POST방식인지 조회한다.
        String methodName = request.getMethod();

        // 현재 URL을 획득한다.
        String url = request.getRequestURL().toString();

        // URL에서 "?"이후에 전달되는 GET파라미터 문자열을 모두 가져온다.
        String queryString = request.getQueryString();

        // 가져온 값이 있다면 URL과 결합하여 완전한 URL을 구성한다.
        if (queryString != null) {
            url = url + "?" + request.getQueryString();
        }

        // 획득한 정보를 로그로 표시한다.
        log.debug(String.format("[%s] %s", methodName, url));

        // 접근한 클라이언트의 정보 가져오기
        String ua = request.getHeader("User-Agent");

        Map<String, String> browser = Browser.lookup(ua);
        Map<String, String> os = (Map<String, String>) OS.lookup(ua);
        Map<String, String> device = (Map<String, String>) Device.lookup(ua);

        String browserStr = String.format("- Browser : {family=%s, patch=%s, major=%s, minor=%s}",
                browser.get("family"), browser.get("patch"), browser.get("major"), browser.get("minor"));

        String osStr = String.format("- OS : {family=%s, patch=%s, patch_minor=%s, major=%s, minor=%s}",
                os.get("family"), os.get("patch"), os.get("patch_minor"), os.get("major"), os.get("minor"));

        String deviceStr = String.format("- Device : {family=%s, model=%s, brand=%s}",
                device.get("family"), device.get("model"), device.get("brand"));

        log.debug(browserStr);
        log.debug(osStr);
        log.debug(deviceStr);

        // 이전에 머물렀던 페이지
        String referer = request.getHeader("referer");

        // 이전 종료 시간이 0보다 크다면 새로운 시작시간과의 차이는 이전 페이지에 머문 시간을 의미한다.
        if (referer != null && endTime > 0) {
            log.debug(String.format("- REFERER : time=%d, url=%s", startTime - endTime, referer));
        }

        // 모든 파라미터 객체 획득하여 로그로 기록하게 한다.
        Map<String, String[]> params = request.getParameterMap();

        for (String key : params.keySet()) {
            String[] value = params.get(key);
            log.debug(String.format("(p) <-- %s = %s", key, String.join(",", value)));
        }
        
        webHelper.init();
        
        return super.preHandle(request, response, handler);
    }

    /**
     * view 단으로 forward 되기 전에 수행.
     * 컨트롤러 로직이 실행된 이후 호출된다. 
     * 컨트롤러 단에서 에러 발생 시 해당 메서드는 수행되지 않는다. 
     * request로 넘어온 데이터 가공 시 많이 사용된다.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 종료시의 시각을 가져온다.
        endTime = System.currentTimeMillis();
        // 시작시간과 종료시간 사이의 차이를 구하면 페이지의 실행시간을 구할 수 있다.
        log.debug(String.format("running time: %d(ms)\n", endTime-startTime));
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 컨트롤러 종료 후 view가 정상적으로 랜더링 된 후 제일 마지막에 실행이 되는 메서드.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * Servlet 3.0부터 비동기 요청이 가능해짐에 따라 비동기 요청 시 
     * PostHandle와 afterCompletion메서드를 수행하지 않고 이 메서드를 수행하게 된다.
     * 거의 사용 안함.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}