package com.example.springboottest.util.fdfs;

import cn.hutool.core.lang.UUID;
import com.roncoo.common.MyException;
import com.roncoo.fastdfs.StorageClient;
import com.roncoo.fastdfs.StorageClient1;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: hy-cloud
 * @description: fdfs工具类
 * @author: loren
 * @Description: TODO
 * @create: 2023-04-06 17:14
 **/
@Slf4j
@Component
public class FastDFSClientUtil {

    @Resource
    private  StorageClient storageClient;

    @Value("${fastdfs.nginx.host}")
    private String serverAddr;
    @Value("${fastdfs.nginx.temp_dir}")
    private String tempDir;
    @Value("${fastdfs.defautt_group}")
    private String groupName;


    /**
     * 路径分隔符
     */
    public static final String SEPARATOR = "/";
    /**
     * Point
     */
    public static final String POINT = ".";
    /**
     * 文件类型
     */
    public static final Map<String, String> EXT_MAPS = new HashMap<>();

    /**
     * 文件名称
     */
    private static final String FILENAME = "filename";
    /**
     * 文件最大的大小
     */
    private int maxFileSize = 500 * 1024 * 1024;

    public FastDFSClientUtil(){
        initExt();
    }

    private void initExt(){
        //图片
        EXT_MAPS.put("png", "image/png");
        EXT_MAPS.put("gif", "image/gif");
        EXT_MAPS.put("bmp", "image/bmp");
        EXT_MAPS.put("ico", "image/x-ico");
        EXT_MAPS.put("jpeg", "image/jpeg");
        EXT_MAPS.put("jpg", "image/jpeg");
        //压缩文件
        EXT_MAPS.put("zip", "application/zip");
        EXT_MAPS.put("rar", "application/x-rar");
        //word,excel,ppt
        EXT_MAPS.put("pdf", "application/pdf");
        EXT_MAPS.put("ppt", "application/vnd.ms-powerpoint");
        EXT_MAPS.put("xls", "application/vnd.ms-excel");
        EXT_MAPS.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        EXT_MAPS.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        EXT_MAPS.put("doc", "application/msword");
        EXT_MAPS.put("doc", "application/wps-office.doc");
        EXT_MAPS.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        EXT_MAPS.put("txt", "text/plain");
        //视频、音频
        EXT_MAPS.put("mp4", "video/mp4");
        EXT_MAPS.put("flv", "video/x-flv");
    }

    /***
     * 获取文件最大值
     * @return
     */
    public int getMaxFileSize(){
        return maxFileSize ;
    }

    /***
     * 设置文件的最大值
     * @param value
     */
    public void setMaxFileSize(int value){
        this.maxFileSize = value ;
    }

    /***
     * 获取FastDFS文件的名称，如：M00/00/00/rBBNW1sJCvCAHgH7AAVGh5jPYok707.jpg
     * @param fileid fileId 包含组名和文件名,如：group1/M00/00/00/rBBNW1sJCvCAHgH7AAVGh5jPYok707.jpg
     * @return
     */
    public static String getFilename(String fileid){

        String[] results = new String[2];
        StorageClient1.split_file_id(fileid,results);
        return results[1] ;
    }

    /***
     * 获取文件名称的后缀
     * @param fileName
     * @return
     */
    public static String getFIleNameSuffix(String fileName){
        String suffix = null ;
        if (fileName!=null&&!"".equals(fileName)){
            if (fileName.contains(SEPARATOR)){
                fileName = fileName.substring(fileName.lastIndexOf(SEPARATOR) + 1);
            }
            if (fileName.contains(POINT)){
                suffix = fileName.substring(fileName.lastIndexOf(POINT) + 1);
            } else {
                if (log.isErrorEnabled()) {
                    log.error("文件后缀获取失败！");
                }
            }
        }

        return suffix ;
    }

    /***
     * 转换路径中的 '\' 为 '/' ,并把文件后缀转为小写
     * @param path
     * @return
     */
    public static String toLocal(String path) {
        if (path!=null&&!"".equals(path)) {
            path = path.replaceAll("\\\\", SEPARATOR);

            if (path.contains(POINT)) {
                String pre = path.substring(0, path.lastIndexOf(POINT) + 1);
                String suffix = path.substring(path.lastIndexOf(POINT) + 1).toLowerCase();
                path = pre + suffix;
            }
        }
        return path;
    }

    /**
     * 上传文件
     * @param file 文件
     * @return 返回文件全路径
     */
    public String uploadFile(MultipartFile file, String fileName) throws IOException, MyException{
        if (file.isEmpty()){
            log.error("uploadFile file is null");
            return "";
        }
        String oldName = "";
        if (asserbol(fileName) ) {
            oldName = file.getOriginalFilename();
        }else {
            oldName = UUID.fastUUID() + "." + fileName;
        }
        if (!asserbol(oldName) ) {
            String ext = getFIleNameSuffix(oldName);
            if (!EXT_MAPS.containsKey(ext)){
                log.error("uploadFile is error: oldName:{}",oldName);
                throw new MyException("不支持此类文件上传type"+oldName);
            }
            String[] parts = storageClient.upload_file(file.getBytes(), ext, null);
            String path = parts != null ? parts[0] + "/" + parts[1] : null;
            return  serverAddr + "/"+ path;


        }
        return null;
    }

    /**
     * 删除文件  删除需要 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @param fileUrl http://xxx/group1/M00/00/00/rBEQIl8ROTKAIm_JAAAM9WnKN_I424.png
     * @return -1失败,0成功 ,2找不到文件
     */
    public boolean deleteFile(String fileUrl)  {
        try {
            if (asserbol(fileUrl)){
                return false;
            }
            int group = fileUrl.indexOf(groupName);
            //获取到文件名
            String fileName2 = fileUrl.substring(group);
            String groupName = fileName2.substring(0, fileName2.indexOf("/"));
            String pathName = fileName2.substring(fileName2.indexOf("/")+1);
            int i = storageClient.delete_file(groupName, pathName);
            log.info("deleteFile fileUrl:{}, result:{}",fileUrl,i);
            if (i == 0){
                return true;
            }
        } catch (Exception e) {
            log.error("deleteFile group:{},fileUrl:{} is error:{}",fileUrl,groupName,e);
        }
        return false;
    }

    /**
     * 文件下载
     * @param fullPath 文件路径，例如：/group1/path=M00/00/00/test.png
     * @param filename 下载的文件命名 test.png 如果为空则会uuid
     * @return
     */
    public void downloadFile(String fullPath, String filename, HttpServletResponse response) throws IOException, MyException {
        if (asserbol(fullPath)){
            log.info("downloadFile fullPath null");
            return;
        }
        if (asserbol(filename)){
            String ext = getFIleNameSuffix(filename);
            filename = UUID.fastUUID() + "." + ext;
        }
        int group = fullPath.indexOf(groupName);
        if (group < 0){
            log.info("group less than 0");
            return;
        }
        //获取到文件名
        String path = fullPath.substring(group,fullPath.length());
        String downName = getFilename(path);
        // 获取文件
        byte[] bytes = storageClient.download_file(groupName,downName);
        response.reset();
        response.setContentType("applicatoin/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }

    public static boolean asserbol(String str){
        if (StringUtils.isBlank(str)){
            // 示范 抛出自定义异常
            return true;
        }
        return false;
    }
}