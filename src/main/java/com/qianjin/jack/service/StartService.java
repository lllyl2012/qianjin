package com.qianjin.jack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author volume
 * @date 2019/10/21 11:15
 */
@Component
@Order(1)
public class StartService implements ApplicationRunner {
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //检查文件目录是否存在
        String rootPath = getRootPath() + "/dimensionCode";
        File file = new File(rootPath);
        if(!file.exists())file.mkdirs();
    }
}
