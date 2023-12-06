package com.example.springboottest.util.fdfs;

import com.roncoo.common.MyException;
import com.roncoo.fastdfs.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * @program: hy-cloud
 * @description: fdfs配置
 * @author: loren
 * @Description: TODO
 * @create: 2023-04-06 14:16
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FastDFSConfig {
        private final Environment env;
    @Bean
    public StorageClient storageClient() {
        StorageClient storageClient = null;
        try {
            ClientGlobal.initByProperties("application-"+env.getActiveProfiles()[0]+".yml");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageServer storageServer=trackerClient.getStoreStorage(trackerServer);
            //定义Stroage客户端对象，需要使用这个对象来完成具体的文件上传，下载和删除操作
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException | MyException e) {
           log.error("FastDFSConfig storageClient error :{}",e);
        }
        return storageClient;
    }
}