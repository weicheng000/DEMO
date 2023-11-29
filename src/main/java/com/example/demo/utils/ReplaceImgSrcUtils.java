package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceImgSrcUtils {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageInfo {
        public String modifiedHtml;
        public ClassPathResource[] imageFiles;
    }

    public static ImageInfo replaceScr(String html) {
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher matcher = pattern.matcher(html);
        StringBuffer modifiedHtml = new StringBuffer();
        List<ClassPathResource> imageFiles = new ArrayList<>();
        int count = 1;

        while (matcher.find()) {
            String src = matcher.group(1);

            // 提取擴展名
            Pattern extensionPattern = Pattern.compile("data:image/([a-zA-Z]+);base64");
            Matcher extensionMatcher = extensionPattern.matcher(src);
            String extension = null;

            if (extensionMatcher.find()) {
                extension = extensionMatcher.group(1);
            } else {
                extension = "png";
            }

            String filename = "image" + count + "." + extension;

            // 提取base64數據
            String base64Data = src.split(",")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

            // 其餘程式碼保持不變
            try {
                // 獲取 temp/目錄的路徑

                Path tempDir = Paths.get("D:/develope/demo/src/main/resources/images/temp/");

                // 將base64數據寫入文件
                Path filePath = tempDir.resolve(filename);
                Files.write(filePath, decodedBytes);

                // 將ClassPathResource添加到列表中
                ClassPathResource imageResource = new ClassPathResource("images/temp/" + filename);
                imageFiles.add(imageResource);

                // 將src替換為cid:image1, cid:image2, ...
                matcher.appendReplacement(modifiedHtml, "<img src='cid:image" + count + "'>");
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            matcher.appendTail(modifiedHtml);
        }
        return new ImageInfo(modifiedHtml.toString(), imageFiles.toArray(new ClassPathResource[0]));
    }
}
