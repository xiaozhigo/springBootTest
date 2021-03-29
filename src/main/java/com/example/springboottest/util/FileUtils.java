package com.example.springboottest.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

/**
 * @Auther: 张帅
 * @Email：zhang_297@163.COM
 * @Date: 2019/10/23 10:56
 * @Description:
 */
@Slf4j
public class FileUtils {

    /**
     * multipartFile转file
     *
     * @param multipartFile
     * @return
     */
    public static File getFile(MultipartFile multipartFile) {
        File file = null;
        try {
            file = multipartFileToFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("上传文件失败！");
        }
        return file;
    }


    private static File multipartFileToFile(@RequestParam MultipartFile file)  {

        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (ins != null) {
                    try {
                        ins.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        return toFile;

    }



    private static void inputStreamToFile(InputStream input, File file) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = input.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("输出流关闭失败：", e);
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("输入流关闭失败：", e);
                }
            }
        }
    }


    /**
     * base64字符串转file
     *
     * @param base64
     * @return
     */
    public static File base64ToFile(String base64) {
        if (base64 == null || "".equals(base64)) {
            return null;
        }
        byte[] buff = Base64.getDecoder().decode(base64);
        File file = null;
        FileOutputStream fout = null;
        try {
            file = File.createTempFile("tmp", null);
            fout = new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


    public static  String fileToBase64(MultipartFile multipartFile) {
        File file = FileUtils.getFile(multipartFile);
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes=new byte[(int)file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }



    /**
     * @return String
     * @throws FileNotFoundException
     * @description 将文件转base64字符串
     */
    public static String fileToBase64(String classpath) throws FileNotFoundException {
        File file1 = org.springframework.util.ResourceUtils.getFile(classpath);
        String path = file1.getPath();
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }







}
