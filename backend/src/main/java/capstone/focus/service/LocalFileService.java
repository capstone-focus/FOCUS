package capstone.focus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.MalformedURLException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocalFileService {

    @Value("${book.folder.path}")
    private String bookFolderPath;

    private final String fileExtension = ".jpg";

    public UrlResource getBookImage(String title) {
        String imageFilePath = bookFolderPath + title + "\\" + title + fileExtension;

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
}
