package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallFileServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:36
 */
public interface MallFileServerService {

    void deleteByPrimaryKey(Integer fileId);

    void insertSelective(MallFileServer record);

    MallFileServer selectByPrimaryKey(Integer fileId);

    void updateByPrimaryKeySelective(MallFileServer record);

    Integer saveLocalFile(String path);

    void getImgFile(Integer fileId, HttpServletResponse response);

    Integer saveUploadFile(MultipartFile file);

}
