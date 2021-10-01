package com.dove.breed.utils;

import com.dove.breed.entity.CageVideo;
import com.dove.breed.mapper.CagePictureMapper;
import com.dove.breed.mapper.CagePositionMapper;
import com.dove.breed.mapper.CageVideoMapper;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PipedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
@author zcj
@creat 2021-09-27-21:58
*/
@Component
public class Image2Mp4 {

    @Autowired
    private CagePositionMapper cagePositionMapper;
    @Autowired
    private CagePictureMapper cagePictureMapper;
    @Autowired
    private CageVideoMapper cageVideoMapper;

    private int width = 1600;
    private int height = 900;

    //定时器要调用的方法，遍历所有鸽笼，把图片转为视频
    public void updateMp4() throws FrameRecorder.Exception {
        //获取所有鸽笼
        List<Long> cageIds = cagePositionMapper.getAllCageId();
        //遍历所有鸽笼
        for (Long cageId : cageIds) {
            //根据cageId获取该cage前一天的24张图片
            List<String> urls = cagePictureMapper.getYesterdayUrl(cageId);
            Map<Integer, File> imgMap = new HashMap<Integer, File>();
            int num = 0;
            for (String url : urls) {
                File file = new File("/usr/local/go-fastdfs/files" + url.substring(7));
                imgMap.put(num,file);
                num ++;
            }
            File file = new File("/usr/local/go-fastdfs/files/cageVideo/" + UUID.randomUUID() + "mp4");
            createMp4(file,imgMap,width,height,cageId);
        }
    }

    public void test() throws FrameRecorder.Exception {
        //合成的MP4
        String mp4SavePath3 = "/usr/local/go-fastdfs/files/cageVideo/img.mp4";
        //图片地址 这里面放了24张图片
        String img = "/usr/local/go-fastdfs/files/cageVideo/image";
        int width = 1600;
        int height = 900;
        //读取所有图片
        File file = new File(img);
        File[] files = file.listFiles();
        Map<Integer, File> imgMap = new HashMap<Integer, File>();
        int num = 0;
        for (File imgFile : files) {
            imgMap.put(num, imgFile);
            num++;
        }
        File file1 = new File(mp4SavePath3);
//        createMp4(file1, imgMap, width, height);
    }

//    private static void createMp4(String mp4SavePath, Map<Integer, File> imgMap, int width, int height) throws FrameRecorder.Exception {
    protected void createMp4(File file1, Map<Integer, File> imgMap, int width, int height, Long cageId) throws FrameRecorder.Exception {
        //视频宽高最好是按照常见的视频的宽高  16：9  或者 9：16
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(file1, width, height);
        //设置视频编码层模式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //设置视频为25帧每秒
        recorder.setFrameRate(10);
        //设置视频图像数据格式
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setFormat("mp4");
        try {
            recorder.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            //录制一个22秒的视频
            for (int i = 0; i < 10; i++) {
                BufferedImage read = ImageIO.read(imgMap.get(i));
                //一秒是25帧 所以要记录25次
                for (int j = 0; j < 10; j++) {
                    recorder.record(converter.getFrame(read));
                }
            }
            //弄好之后，把路径存进数据库
            String videoPath = "/group1/" + file1.getPath().substring(28);
            CageVideo cageVideo = new CageVideo(cageId, videoPath);
            int insert = cageVideoMapper.insert(cageVideo);
            if (insert == 0){
                System.out.println("toMp4失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后一定要结束并释放资源
            recorder.stop();
            recorder.release();
        }
    }
}