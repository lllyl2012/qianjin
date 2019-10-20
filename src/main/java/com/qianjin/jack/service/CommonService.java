package com.qianjin.jack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommonService {
    @Value("${img-path}")
    public String root;

    @Value("${img-path-linux}")
    public String root2;

    public String getRootPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return root;
        }else{
            return root2;
        }
    }

    public String getFileName(String unid){
        LocalDate localDate = LocalDate.now();
        String fileName = "/"+localDate.format(DateTimeFormatter.ISO_DATE)+unid;
        return fileName.replaceAll("-","");
    }

    public String parseFileName(String photo) {
        String year = photo.substring(0,4);
        String month = photo.substring(4,6);
        String day = photo.substring(6,8);
        return "/dimensionCode"+File.separator+photo+".jpg";
    }
}
