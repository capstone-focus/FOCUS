package capstone.focus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocalFileService {

    @Value("${book.folder.path}")
    private String bookFolderPath;

    private final String imageFileExtension = ".jpg";
    private final String textFileExtension = ".txt";

    public UrlResource getBookImage(String title) {
        String imageFilePath = bookFolderPath + title + "\\" + title + imageFileExtension;
        log.info("imageFilePath: {}", imageFilePath);

        try {
            File imageFile = new File(imageFilePath);
            UrlResource imageResource = new UrlResource(imageFile.toURI());
            if (imageResource.exists() && imageResource.isReadable()) {
                return imageResource;
            }
        } catch (MalformedURLException e) {
            // TODO 예외 처리
            e.printStackTrace();
        }

        return null;
    }

    public String getChapterDescription(String title, int chapterSeq) {
        String filePath = bookFolderPath + title + "\\Chapter_" + chapterSeq + textFileExtension;
        log.info("filePath: {}", filePath);

        try {
            File file = new File(filePath);
            byte[] fileBytes = FileCopyUtils.copyToByteArray(file);
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);
            log.info("fileContent: {}", fileContent);
            return fileContent;
        } catch (IOException e) {
            // TODO 예외 처리
            e.printStackTrace();
        }

        return "";
    }
}
