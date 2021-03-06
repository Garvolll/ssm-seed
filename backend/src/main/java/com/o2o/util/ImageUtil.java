package com.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 13:09
 */
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    
    /** 
    * @Description: 处理缩略图，并返回新生成图片的相对路径
    * @Param: [thumbnail, targetAddr] 
    */ 
    public static String generatorThumbnails(InputStream thumbnail,String filename, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(filename);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current complete addr is:"+PathUtil.getImgBasePath()+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return relativeAddr;
    }

    /**
     * @Description: 获取文件名(日期加随机五位数)
     * @Param: []
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTime = sDateFormat.format(new Date());
        return nowTime + rannum;
    }

    /**
     * @Description: 获取文件拓展名
     * @Param: [fileName]
     */
    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }
    /**
    * @Description: 将Spring内置的CommonsMultipartFile类型的文件转换成File类型
    * @Param: [cFile]
    */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }


    /**
     * storePath是文件的路径还是目录的路径， 如果storePath是文件路径则删除该文件，
     * 如果storePath是目录路径则删除该目录下的所有文件
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(basePath);
            Thumbnails.of(new File(basePath + "\\avatar.jpg")).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.25f)
                    .outputQuality(1f).toFile(basePath + "\\avatarNew.jpg");
        } catch (IOException e) {
        }

    }


}
