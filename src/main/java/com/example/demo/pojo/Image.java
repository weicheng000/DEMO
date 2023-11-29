package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
@Data
@AllArgsConstructor
public class Image {
    private String fileName;
    private ClassPathResource file;
}
