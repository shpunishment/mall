package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallFileServerMapper;
import com.shpun.mall.common.model.MallFileServer;
import com.shpun.mall.common.service.MallFileServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:40
 */
@Service("mallFileServerService")
public class MallFileServerServiceImpl implements MallFileServerService {

    @Autowired
    private MallFileServerMapper fileServerMapper;

    @Override
    public void deleteByPrimaryKey(Integer fileId) {
        fileServerMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public void insertSelective(MallFileServer record) {
        record.setCreateTime(new Date());
        fileServerMapper.insertSelective(record);
    }

    @RedisCache
    @Override
    public MallFileServer selectByPrimaryKey(Integer fileId) {
        return fileServerMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallFileServer record) {
        fileServerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer saveLocalFile(String path) {
        File file = new File(path);

        byte[] fileBytes = null;
        try(
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                ){
            int len;
            byte[] buffer = new byte[1024];
            while ((len = bufferedInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0, len);
            }
            fileBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MallException(MallError.MallErrorEnum.ADD_FILE_ERROR.format(file.getName()));
        }

        MallFileServer fileServer = new MallFileServer();
        fileServer.setCreateId(Const.ADMIN_ID);
        fileServer.setFileName(file.getName());
        fileServer.setFile(fileBytes);
        this.insertSelective(fileServer);
        return fileServer.getFileId();
    }

    @Override
    public void getImgFile(Integer fileId, HttpServletResponse response) {
        MallFileServer file = this.selectByPrimaryKey(fileId);

        if (file == null) {
            throw new MallException(MallError.MallErrorEnum.FILE_NOT_FOUND.format(fileId));
        }

        response.setContentType("image/png");
        byte[] fileBytes = file.getFile();
        try(
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
                ServletOutputStream servletOutputStream = response.getOutputStream();
        ){
            int len;
            byte[] buffer = new byte[1024];
            while ((len = byteArrayInputStream.read(buffer)) > 0){
                servletOutputStream.write(buffer,0, len);
            }
        } catch (IOException e){
            throw new MallException(MallError.MallErrorEnum.GET_FILE_ERROR);
        }
    }

    @Override
    public Integer saveUploadFile(MultipartFile file) {
        MallFileServer fileServer = new MallFileServer();
        fileServer.setFileName(file.getOriginalFilename());

        try {
            fileServer.setFile(file.getBytes());
        } catch (IOException e) {
            throw new MallException(MallError.MallErrorEnum.UPLOAD_FILE_ERROR);
        }

        this.insertSelective(fileServer);
        return fileServer.getFileId();
    }
}
