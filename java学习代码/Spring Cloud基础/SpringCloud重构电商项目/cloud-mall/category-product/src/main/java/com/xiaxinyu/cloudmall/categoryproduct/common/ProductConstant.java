package com.xiaxinyu.cloudmall.categoryproduct.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductConstant {

    public static String FILE_UPLOAD_DIR; //静态属性需要使用@Value配合set方法完成值的注入

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
