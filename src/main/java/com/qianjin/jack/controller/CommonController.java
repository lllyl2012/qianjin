package com.qianjin.jack.controller;

import com.qianjin.jack.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ResourceLoader resourceUtils;

    @Autowired
    private CommonService commonService;

    @GetMapping("/photo/{path}")
    public ResponseEntity<InputStreamResource> photo(@PathVariable String path, HttpServletResponse response) throws IOException {
        Resource resource=  resourceUtils.getResource("file:"+ Paths.get(commonService.getRootPath(),commonService.parseFileName(path)));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("a") + "\"");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(new InputStreamResource(resource.getInputStream()));
    }
}
