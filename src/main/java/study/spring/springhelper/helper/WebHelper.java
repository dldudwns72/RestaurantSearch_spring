package study.spring.springhelper.helper;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebHelper {
    /** 기본 인코딩 타입 설정 */
    private String encType;

    /** 업로드 된 결과물이 저장될 폴더 */
    private String uploadDir;

    /** 업로드 가능한 최대 용량 */
    private long uploadMaxFileSize;

    /** 쿠키에서 사용할 도메인 */
    // 상용화시에 수정해야 함.
    // 사이트 주소가 "http://www.itpaper.co.kr"인 경우 -> ".itpaper.co.kr"
    private String domain;

    /** JSP의 request 내장 객체 */
    // --> import javax.servlet.http.HttpServletRequest;
    private HttpServletRequest request;

    /** JSP의 response 내장 객체 */
    // --> import javax.servlet.http.HttpServletResponse;
    private HttpServletResponse response;

    /** JSP의 session 내장 객체 */
    // --> import javax.servlet.http.HttpSession;
    private HttpSession session;

    /** File정보를 저장하기 위한 컬렉션 */
    private List<UploadItem> fileList;

    /** 그 밖의 일반 데이터를 저장하기 위한 컬렉션 */
    private Map<String, String> paramMap;

    public String getEncType() {
        return encType;
    }

    public void setEncType(String encType) {
        this.encType = encType;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public long getUploadMaxFileSize() {
        return uploadMaxFileSize;
    }

    // Spring Bean이 int 타입만 전달 가능하므로 mb단위를 int로 받아서 long타입의 byte 단위로 변환
    public void setUploadMaxFileSize(long uploadMaxFileSize) {
        this.uploadMaxFileSize = uploadMaxFileSize;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * JSP의 주요 내장객체를 멤버변수에 연결한다.
     * 
     * @param request
     * @param response
     */
    public void init() {
        /** JSP 내장객체를 담고 있는 Spring의 객체를 통해서 내장객체 획득하기 */
        // --> import
        // org.springframework.web.context.request.RequestContextHolder;
        // --> import
        // org.springframework.web.context.request.ServletRequestAttributes;
        ServletRequestAttributes requestAttr 
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        // JSP 내장객체 참조하기 --> getInstance()에 전달된 객체를 받는다.
        this.request = requestAttr.getRequest();
        this.response = requestAttr.getResponse();

        // request 객체를 사용하여 세션 생성하기
        this.session = this.request.getSession();

        /** 내장객체 초기화 -> utf-8 설정 */
        try {
            this.request.setCharacterEncoding(this.encType);
            this.response.setCharacterEncoding(this.encType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메시지 표시 후, 페이지를 지정된 곳으로 이동한다.
     * 
     * @param url - 이동할 페이지의 URL, Null일 경우 이전페이지로 이동
     * @param msg - 화면에 표시할 메시지. Null일 경우 표시 안함
     */
    //public void redirect(String url, String msg) {
    public ModelAndView redirect(String url, String msg) {

        // 획득한 정보를 로그로 표시한다.
        log.debug(String.format(" --> [redirect] %s >> %s", url, msg));

        // 가상의 View로 만들기 위한 HTML 태그 구성
        String html = "<!doctype html>";
        html += "<html>";
        html += "<head>";
        html += "<meta charset='" + this.encType + "'>";

        // 메시지 표시
        if (msg != null) {
            html += "<script type='text/javascript'>alert('" + msg + "');</script>";
        }

        // 페이지 이동
        if (url != null) {
            html += "<meta http-equiv='refresh' content='0; url=" + url + "' />";
        } else {
            html += "<script type='text/javascript'>history.back();</script>";
        }

        html += "</head>";
        html += "<body></body>";
        html += "</html>";

        // 구성된 HTML을 출력한다.
        /*try {
            PrintWriter out = this.response.getWriter();
            out.print(html);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        return this.virtualView(html);
    }

    /**
     * 파라미터를 전달받아서 리턴한다.
     * 
     * @param fieldName    - 파라미터 이름
     * @param defaultValue - 값이 없을 경우 사용될 기본값
     * @return String
     */
    public String getString(String fieldName, String defaultValue) {
        // 리턴을 위한 값을 두 번째 파라미터(기본값)로 설정해 둔다.
        String result = defaultValue;
        // GET,POST 파라미터를 받는다.
        String param = this.request.getParameter(fieldName);

        if (param != null) { // 값이 null이 아니라면?
            param = param.trim(); // 앞뒤 공백을 제거한다.
            if (!param.equals("")) { // 공백제거 결과가 빈 문자열이 아니라면?
                result = param; // 리턴을 위해서 준비한 변수에 수신한 값을 복사한다.
            }
        }

        log.debug(String.format("(p) <-- %s = %s", fieldName, result));

        // 값을 리턴. param값이 존재하지 않을 경우 미리 준비한 기본값이 그대로 리턴된다.
        return result;
    }

    /**
     * 파라미터를 전달받아서 리턴한다. 값이 없을 경우 null을 리턴한다.
     * 
     * @param fieldName - 파라미터 이름
     * @return String
     */
    public String getString(String fieldName) {
        return this.getString(fieldName, null);
    }

    /**
     * 파라미터를 전달받아서 int로 형변환 하여 리턴한다.
     * 
     * @param fieldName    - 파라미터 이름
     * @param defaultValue - 값이 없을 경우 사용될 기본값
     * @return int
     */
    public int getInt(String fieldName, int defaultValue) {
        // 리턴을 위한 값을 두 번째 파라미터(기본값)로 설정해 둔다.
        int result = defaultValue;

        // getString()메서드를 통해서 파라미터를 문자열 형태로 받는다.
        // 파라미터가 존재하지 않는다면 두 번째로 전달한 값이 리턴된다.
        String param = this.getString(fieldName, null);

        // 숫자형인 경우 숫자값으로 변환한다.
        try {
            result = Integer.parseInt(param);
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 파라미터를 전달받아서 int로 형변환 하여 리턴한다. 값이 없을 경우 0을 리턴한다.
     * 
     * @param fieldName - 파라미터 이름
     * @return int
     */
    public int getInt(String fieldName) {
        return this.getInt(fieldName, 0);
    }

    /**
     * 배열 형태의 파라미터를 리턴한다. 체크박스 전용 기능
     * 
     * @param fieldName    - 파라미터 이름
     * @param defaultValue - 값이 없거나 배열의 길이가 0인 경우 사용될 기본값
     * @return String[]
     */
    public String[] getStringArray(String fieldName, String[] defaultValue) {
        // 리턴을 위한 값을 두 번째 파라미터(기본값)로 설정해 둔다.
        String[] result = defaultValue;
        // 배열 형태의 GET,POST 파라미터를 받는다.
        String[] param = this.request.getParameterValues(fieldName);

        if (param != null) { // 수신된 파라미터가 존재한다면?
            if (param.length > 0) { // 배열의 길이가 0보다 크다면?
                result = param; // 리턴을 위해서 준비한 변수에 수신한 값을 복사한다.
            }
        }

        if (result != null) {
            log.debug(String.format("(p) <-- %s = %s", fieldName, String.join(", ", result)));
        } else {
            log.debug(String.format("(p) <-- %s = null", fieldName));
        }

        // 값을 리턴. param값이 존재하지 않을 경우 미리 준비한 기본값이 그대로 리턴된다.
        return result;
    }

    /**
     * 배열 형태의 파라미터를 리턴한다. 값이 없을 경우 null을 리턴한다.
     * 
     * @param fieldName - 파라미터 이름
     * @return String[]
     */
    public String[] getStringArray(String fieldName) {
        return this.getStringArray(fieldName, null);
    }

    /**
     * 쿠키값을 저장한다.
     * 
     * @param key     - 쿠키이름
     * @param value   - 값
     * @param timeout - 설정시간. 브라우저를 닫으면 즉시 삭제할 경우 -1
     */
    public void setCookie(String key, String value, int timeout) {
        /** 전달된 값을 URLEncoding 처리 한다. */
        if (value != null) {
            try {
                // import java.net.URLEncoder
                value = URLEncoder.encode(value, this.encType);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        /** 쿠키 객체 생성 및 기본 설정 */
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setDomain(this.domain);

        /** 유효시간 설정 */
        // 시간값이 0보다 작은 경우는 이 메서드를 설정하지 않도록 한다. (브라우저를 닫으면 삭제)
        // 0으로 설정할 경우 setMaxAge(0)이라고 설정되므로 즉시 삭제된다.
        if (timeout > -1) {
            cookie.setMaxAge(timeout);
        }

        /** 쿠키 저장하기 */
        this.response.addCookie(cookie);
    }

    /**
     * 쿠키값을 조회한다.
     * 
     * @param key          - 쿠키이름
     * @param defaultValue - 값이 없을 경우 사용될 기본값
     * @return String
     */
    public String getCookie(String key, String defaultValue) {
        /** 리턴할 값을 설정 */
        String result = defaultValue;

        /** 쿠키 배열 가져오기 */
        // import javax.servlet.http.Cookie
        Cookie[] cookies = this.request.getCookies();

        /** 쿠키가 있다면? 추출된 배열의 항목 수 만큼 반복하면서 원하는 이름의 값을 검색 */
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                // 쿠키의 이름 얻기
                String cookieName = cookies[i].getName();
                // 원하는 이름이라면?
                if (cookieName.equals(key)) {
                    // 값을 추출 --> 이 값이 리턴된다.
                    result = cookies[i].getValue();
                    try {
                        // import java.net.URLDecoder
                        result = URLDecoder.decode(result, this.encType);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                } // end if
            } // end for
        } // end if

        return result;
    }

    /**
     * 쿠키값을 조회한다. 값이 없을 경우 Null을 리턴한다.
     * 
     * @param key - 쿠키이름
     * @return String
     */
    public String getCookie(String key) {
        return this.getCookie(key, null);
    }

    /**
     * 지정된 키에 대한 쿠키를 삭제한다.
     * 
     * @param key
     */
    public void removeCookie(String key) {
        this.setCookie(key, null, 0);
    }

    /**
     * 세션값을 저장한다.
     * 
     * @param key   - 세션이름
     * @param value - 저장할 데이터
     */
    public void setSession(String key, Object value) {
        this.session.setAttribute(key, value);
    }

    /**
     * 세션값을 조회한다.
     * 
     * @param key          - 조회할 세션의 이름
     * @param defaultValue - 값이 없을 경우 대체할 기본값
     * @return Object이므로 명시적 형변환 필요함
     */
    public Object getSession(String key, Object defaultValue) {
        Object value = this.session.getAttribute(key);

        if (value == null) {
            value = defaultValue;
        }

        return value;
    }

    /**
     * 세션값을 조회한다. 값이 없을 경우에 대한 기본값을 null로 설정
     * 
     * @param key - 세션 이름
     * @return Object이므로 명시적 형변환 필요함
     */
    public Object getSession(String key) {
        return this.getSession(key, null);
    }

    /**
     * 특정 세션값을 삭제한다.
     * 
     * @param key - 세션 이름
     */
    public void removeSession(String key) {
        this.session.removeAttribute(key);
    }

    /**
     * 현재 사용자에 대한 모든 세션값을 일괄 삭제한다.
     */
    public void removeAllSession() {
        this.session.invalidate();
    }

    /**
     * 업로드된 파일의 리스트를 리턴한다.
     * 
     * @return List<UploadItem>
     */
    public List<UploadItem> getFileList() {
        return this.fileList;
    }

    /**
     * 업로드시에 함께 전달된 파라미터들의 컬렉션을 리턴한다.
     * 
     * @return Map<String, String>
     */
    public Map<String, String> getParamMap() {
        return this.paramMap;
    }

    /**
     * Multipart로 전송된 데이터를 판별하여 파일리스트와 텍스트 파라미터를 분류한다.
     * 
     * @throws Exception
     */
    public void upload() throws Exception {
        /** 1) 업로드 사전 준비하기 */
        // items에 저장 데이터가 분류될 컬렉션들 할당하기
        fileList = new ArrayList<UploadItem>();
        paramMap = new HashMap<String, String>();

        // 업로드가 수행될 폴더의 존재 여부 체크해서 없다면 생성하기
        // --> import java.io.File
        File uploadDirFile = new File(this.uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // 업로드가 수행될 폴더 연결
        // --> import org.apache.commons.fileupload.disk.DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(uploadDirFile);

        /** 2) 업로드 처리하기 */
        // 업로드 수행을 위한 객체 생성
        // --> import org.apache.commons.fileupload.servlet.ServletFileUpload;
        ServletFileUpload upload = new ServletFileUpload(factory);
        // UTF-8 처리 지정
        upload.setHeaderEncoding(this.encType);
        // 최대 파일 크기
        upload.setSizeMax(this.uploadMaxFileSize);
        // 실제 업로드를 수행하여 전송된 데이터에 대한 컬렉션 객체 추출하기
        // 이 안에 파일과 텍스트 정보가 함께 들어 있기 때문에 반복문을 수행하면서 분류 작업을 진행해야 한다.
        // --> import org.apache.commons.fileupload.FileItem
        List<FileItem> items = upload.parseRequest(request);

        /** 3) 업로드 정보 분류하기 */
        // 업로드 된 컬렉션의 데이터 수 만큼 반복하면서 처리한다.
        for (FileItem f : items) {
            if (f.isFormField()) {
                /** 텍스트 형식의 데이터인 경우 --> paramMap에 정보 분류 */
                // <input>태그 의 name 속성
                String key = f.getFieldName();
                // 사용자의 입력값을 UTF-8 형식으로 취득한다.
                String value = f.getString(this.encType);

                // 이미 동일한 키값이 map안에 존재한다면?
                // --> checkbox의 경우 이름이 동일한 요소가 여러개 전송 될 수 있음.
                if (paramMap.containsKey(key)) {
                    // 기존의 값 뒤에 콤마(,)를 추가해서 값을 병합한다.
                    value = paramMap.get(key) + "," + value;
                    paramMap.put(key, value);
                } else {
                    // 그렇지 않다면 키와 값을 신규로 추가한다.
                    paramMap.put(key, value);
                }
            } else {
                /** 파일 형식의 데이터인 경우 --> fileList에 정보 분류 */

                /** 1) 파일의 정보를 추출한다 */
                String fieldName = f.getFieldName(); // <input type='file' />의 name 속성
                String orginName = f.getName(); // 파일의 원본 이름
                String contentType = f.getContentType(); // 파일 형식
                long fileSize = f.getSize(); // 파일 사이즈

                if (fileSize < 1) { // 파일 사이즈가 없다면 조건으로 돌아간다.
                    continue;
                }

                /** 2) 동일한 이름의 파일이 존재하는지 검사한다. */
                // 파일의 원본 이름에서 확장자만 추출
                String ext = orginName.substring(orginName.lastIndexOf("."));

                String fileName = null; // 웹 서버에 저장될 파일이름
                File uploadFile = null; // 저장된 파일 정보를 담기 위한 File객체
                String filePath = null; // 저장된 파일의 경로
                int count = 0; // 중복된 파일 수

                // 일단 무한루프
                while (true) {
                    // 저장될 파일 이름 --> 현재시각 + 카운트값 + 확장자
                    fileName = String.format("%d%d%s", System.currentTimeMillis(), count, ext);

                    // 업로드 파일이 저장될 폴더 + 파일이름으로 파일객체를 생성한다.
                    uploadFile = new File(uploadDirFile, fileName);

                    // 동일한 이름의 파일이 없다면 반복 중단.
                    if (!uploadFile.exists()) {
                        filePath = uploadFile.getAbsolutePath();
                        break;
                    }

                    // if문을 빠져나올 경우 중복된 이름의 파일이 존재한다는 의미이므로 count를 1증가
                    count++;
                } // end while

                /** 3) 업로드 된 파일을 결정된 파일 경로로 저장 */
                f.write(uploadFile); // 파일 저장
                f.delete(); // 파일 객체는 삭제

                // 윈도우에서 처리할 경우 파일 경로상에 존재하는 역슬래시를 모두 슬래시로 변경한다.
                filePath = filePath.replace("\\", "/");

                // 최종적으로 생성된 경로에서 업로드 폴더까지의 경로를 제거한다.
                // ex) D:/jsp/upload/2234234435.jpg --> /2234234435.jpg
                filePath = filePath.replace(this.uploadDir.replace("\\", "/"), "");

                /** 4) 파일 정보 분류 처리 */
                // 생성된 정보를 Beans의 객체로 설정해서 컬렉션에 저장한다.
                // --> 이 정보는 추후 파일의 업로드 내역을 DB에 저장할 때 사용된다.
                UploadItem info = new UploadItem();
                info.setFieldName(fieldName);
                info.setOrginName(orginName);
                info.setFilePath(filePath);
                info.setContentType(contentType);
                info.setFileSize(fileSize);

                fileList.add(info);
            } // end if
        } // end for

        // 취득한 정보를 로그로 기록한다.
        for (UploadItem item : fileList) {
            log.debug(String.format("(f) <-- %s", item.toString()));
        }

        for (String key : paramMap.keySet()) {
            log.debug(String.format("(p) <-- %s = %s", key, paramMap.get(key)));
        }
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴한다.
     * 
     * @param key
     * @param value
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData(int statusCode, String rt, Map<String, Object> data) {
        this.response.setContentType("application/json");

        // HTTP 상태 코드 설정
        this.response.setStatus(statusCode);
        
        // CrossDomain에 의한 접근 허용 (보안에 좋지 않기 때문에 이 옵션을 허용할 경우 인증키 등의 보안장치가 필요함)
        // 여기서는 실습을 위해 허용함
        this.response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        this.response.setHeader("Access-Control-Max-Age", "3600");
        this.response.setHeader("Access-Control-Allow-Headers", "x-requested-with");        
        this.response.setHeader("Access-Control-Allow-Origin", "*");
        
        Calendar c = Calendar.getInstance();
        String pubDate = String.format("%04d-%02d-%02d %02d:%02d:%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rt", rt);
        map.put("pubDate", pubDate);

        // data가 전달되었다면 map에 병합한다.
        if (data != null) {
            map.putAll(data);
        }

        return map;
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴한다.
     * 
     * @param key
     * @param value
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData(Map<String, Object> data) {
        return this.getJsonData(200, "OK", data);
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴한다.
     * 
     * @param rt
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData(int statusCode, String rt) {
        return this.getJsonData(statusCode, rt, null);
    }

    /**
     * JSON 형식으로 결과 메시지를 리턴한다.
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData() {
        return this.getJsonData(200, "OK", null);
    }

    /**
     * JSON 형식으로 에러 메시지를 리턴한다.
     * 
     * @param rt
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonError(String rt) {
        return this.getJsonData(500, rt, null);
    }

    /**
     * JSON 형식으로 잘못된 접근에 대한 경고 메시지를 리턴한다.
     * 
     * @param rt
     * 
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonWarning(String rt) {
        return this.getJsonData(400, rt, null);
    }

    /**
     * 문자열에 포함된 HTML태그와 줄바꿈 문자를 HTML특수문자 형태로 변환
     * 
     * @param content
     * @return String
     */
    public String convertHtmlTag(String content) {
        // 변경 결과를 저장할 객체
        StringBuilder builder = new StringBuilder();

        // 문자열에 포함된 한 글자
        char chrBuff;

        // 글자 수 만큼 반복한다.
        for (int i = 0; i < content.length(); i++) {
            // 한 글자를 추출
            chrBuff = (char) content.charAt(i);

            // 특수문자 형태에 부합할 경우 변환하여 builder에 추가
            // 그렇지 않을 경우 원본 그대로 builder에 추가
            switch (chrBuff) {
            case '<':
                builder.append("&lt;");
                break;
            case '>':
                builder.append("&gt;");
                break;
            case '&':
                builder.append("&amp;");
                break;
            case '\n':
                builder.append("&lt;br/&gt;");
                break;
            default:
                builder.append(chrBuff);
            }
        }

        // 조립된 결과를 문자열로 변환해서 리턴한다.
        return builder.toString();
    }
    
    /**
     * 파라미터로 받은 내용을 가상의 View로 생성후 리턴한다.
     * 브라우저에게 전달할 HTML,CSS,JS 조합을 출력하기 위해 사용한다.
     * @param body - 브라우저에게 전달할 HTML,CSS,JS 조합 문자열
     * @return ModelAndView
     */
    public ModelAndView virtualView(final String body) {
        /** 가상의 View를 익명 클래스 방식으로 생성하여 리턴 */
        // --> import org.springframework.web.servlet.View;
        // --> import org.springframework.web.servlet.view.AbstractView;
        View view = new AbstractView() {
            @Override
            protected void renderMergedOutputModel(Map<String, Object> map, 
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
                PrintWriter out = response.getWriter();
                out.println(body);
                out.flush();
            }
        };

        // 가상의 뷰를 리턴한다.
        return new ModelAndView(view);
    } 
}
