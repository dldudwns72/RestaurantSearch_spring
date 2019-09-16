package study.spring.springhelper.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

@Slf4j
public class DownloadHelper {
    /** 업로드 된 결과물이 저장될 폴더 */
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    /**
     * 지정된 경로의 파일을 읽어들인다. 그 내용을 응답객체(response)를 사용해서 출력한다.
     *
     * @param filePath  - 서버상의 파일 경로
     * @param originName - 원본 파일 이름
     * @throws IOException
     */
    public void download(String filePath, String originName) throws Exception {
        log.debug(String.format("[Download] filePath: %s", filePath));
        log.debug(String.format("[Download] originName: %s", originName));
        
        /** JSP 내장객체를 담고 있는 Spring의 객체를 통해서 내장객체 획득하기 */
        // --> import
        // org.springframework.web.context.request.RequestContextHolder;
        // --> import
        // org.springframework.web.context.request.ServletRequestAttributes;
        ServletRequestAttributes requestAttr 
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        // JSP 내장객체 참조하기 --> getInstance()에 전달된 객체를 받는다.
        HttpServletResponse response = requestAttr.getResponse();

        /** 파일의 존재여부를 확인하고 파일의 정보 추출하기 */
        // --> import java.io.File;
        File f = new File(this.uploadDir, filePath);
        if (!f.exists()) {
            log.error("[Download] FileNotFoundException");
            // --> import java.io.FileNotFoundException;
            throw new FileNotFoundException(f.getAbsolutePath());
        }

        // 파일의 크기 추출하기
        long size = f.length();
        // 서버에 보관되어 있는 파일의 이름 추출하기
        String name = f.getName();

        // 원본 파일명이 전달되지 않은 경우 서버상의 파일이름으로 대체
        if (originName == null) {
            originName = name;
        }

        // 파일형식 얻기 (업로드 정보에서 추출했던 contentType과 같은 값)
        // --> import javax.activation.MimetypesFileTypeMap;
        MimetypesFileTypeMap typeMap = new MimetypesFileTypeMap();
        String fileType = typeMap.getContentType(f);

        /** 브라우저에게 이 메서드를 호출하는 페이지의 형식을 일반 파일로 인식시키기 위한 처리 */
        // 다른 데이터와 섞이지 않도록 응답객체(response)를 리셋한다.
        response.reset();

        // 파일형식 정보 설정
        response.setHeader("Content-Type", fileType + "; charset=UTF-8");

        // 파일의 이름 설정 (인코딩 필요함)
        // --> import java.net.URLEncoder;
        String encFileName = URLEncoder.encode(originName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + encFileName + ";");

        // 파일의 용량 설정
        response.setContentLength((int) size);

        /** 스트림을 통한 파일의 바이너리 읽기 */
        // 파일 읽기와 출력을 위한 스트림을 생성한다.
        // --> import java.io.InputStream;
        InputStream is = new FileInputStream(f);

        // is는 한번에 내용을 읽어야 하지만 BufferedInputStream은 조금씩 나누어 읽어들일 수 있다.
        // 대용량 파일 처리를 위해서는 이 클래스를 통해 데이터를 조금씩 나누어 처리해야 한다.
        // --> import java.io.BufferedInputStream;
        BufferedInputStream bis = new BufferedInputStream(is);

        // BufferedInputStream을 통해 읽어들인 데이터를 출력하기 위해 사용되는 클래스
        // --> import java.io.BufferedOutputStream;
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

        // 업로드 된 파일의 용량에 상관없이 1kbyte 크기의 배열 공간을 생성한다.
        byte[] buffer = new byte[1024];

        // 버퍼링이 수행되는 동안 읽어들인 데이터의 크기를 체크하기 위한 변수
        int length = 0;

        // bis.read() 메서드는 파라미터로 전달된 배열 길이만큼 데이터를 담고
        // 자신이 읽어들인 용량을 리턴한다.
        // 만약 읽어들인 내용이 없다면 -1을 리턴한다.
        // ex) 2000byte를 읽어야 할 경우
        // 첫 번째 수행시 버퍼링이 => 1024 ==> buffer배열은 0부터 1024칸이 채워져 있음
        // 두 번째 수행시 버퍼링이 => 976 ==> buffer 배열은 0부터 976칸만 채워져 있음.
        // 세 번째 수행시 버퍼링이 => -1 (종료)
        // 그러므로 이 값이 -1이 아닐때 까지 반복한다면
        // 파일을 1024바이트씩 나누어서 여러번 읽어들인다.
        // --> 버퍼링 처리
        while ((length = bis.read(buffer)) != -1) {
            // buffer의 내용을 0번째 위치부터 읽어들인 크기만큼까지만
            // 버퍼링을 위한 스트림(bos)에 옮겨 담는다.
            bos.write(buffer, 0, length);
        }

        // 옮겨담은 내용을 웹 브라우저에 전송한다.
        bos.flush();

        // 모든 스트림들을 닫는다.
        bos.close();
        bis.close();
        is.close();
    }

    /**
     * 원본파일의 경로와 함께 이미지의 가로,세로 크기가 전달될 경우 지정된 크기로 썸네일 이미지를 생성하고 생성된 썸네일을 출력시킨다.
     * 
     * @param filePath - 원본 이미지 경로
     * @param width    - 가로 크기
     * @param height   - 세로 크기
     * @throws IOException
     */
    public void download(String filePath, int width, int height, boolean crop) throws Exception {

        // 썸네일을 생성하고 경로를 리턴받는다.
        String thumbPath = this.createThumbnail(filePath, width, height, crop);

        // 썸네일을 출력한다.
        // --> 이 메서드를 호출하기 위해서 try~catch가 요구되지만,
        // 현재 메서드 역시 throws를 명시했기 때문에
        // 예외처리가 현재 메서드를 호출하는 곳으로 이관된다.
        this.download(thumbPath, null);
    }

    /**
     * 리사이즈 된 썸네일 이미지를 생성한다.
     * 
     * @param loadFile - 원본 파일의 경로
     * @param width    - 최대 이미지 가로 크기
     * @param height   - 최대 이미지 세로 크기
     * @param crop     - 이미지 크롭 사용 여부
     * @return 생성된 이미지의 절대 경로
     * @throws IOException
     */
    public String createThumbnail(String path, int width, int height, boolean crop) throws Exception {
        log.debug(String.format("[Thumbnail] path: %s", path));
        log.debug(String.format("[Thumbnail] width: %s", width));
        log.debug(String.format("[Thumbnail] height: %s", height));
        log.debug(String.format("[Thumbnail] crop: %s", crop));

        // 생성된 썸네일 이미지의 경로
        String saveFile = null;

        // 원본 파일명에서 저장될 파일 경로를 생성한다.
        File loadFile = new File(this.uploadDir, path);
        String dirPath = loadFile.getParent();
        String fileName = loadFile.getName();

        // 원본 파일이름에서 이름과 확장자를 분리한다.
        int p = fileName.lastIndexOf(".");
        String name = fileName.substring(0, p);
        String ext = fileName.substring(p + 1);

        // 원본이름에 요청된 사이즈를 덧붙여서 생성될 파일명을 구성한다.
        // ex) myphoto.jpg --> myphoto_resize_320x240.jpg
        String prefix = "_resize_";
        if (crop) {
            prefix = "_crop_";
        }

        String thumbName = name + prefix + width + "x" + height + "." + ext;
        File f = new File(dirPath, thumbName);

        // 절대경로 추출
        saveFile = f.getAbsolutePath();

        // 해당 경로에 이미지가 없는 경우만 수행
        if (!f.exists()) {
            // 원본 이미지 가져오기
            // --> import net.coobird.thumbnailator.Thumbnails;
            // --> import net.coobird.thumbnailator.Thumbnails.Builder;
            Builder<File> builder = Thumbnails.of(loadFile);
            // 이미지 크롭 여부
            if (crop == true) {
                builder.crop(Positions.CENTER);
            }
            // 축소할 사이즈 지정
            builder.size(width, height);
            // 세로로 촬영된 사진을 회전시킴
            builder.useExifOrientation(true);
            // 파일의 확장명 지정
            builder.outputFormat(ext);
            // 저장할 파일이름 지정
            builder.toFile(saveFile);
        }

        // 최종적으로 생성된 경로에서 업로드 폴더까지의 경로를 제거한다.
        saveFile = saveFile.replace("\\", "/").replace(this.uploadDir, "");

        log.debug(String.format("[Thumbnail] saveFile: %s", saveFile));

        return saveFile;
    }
}
