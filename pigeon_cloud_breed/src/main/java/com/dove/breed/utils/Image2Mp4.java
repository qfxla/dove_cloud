package com.dove.breed.utils;

import com.dove.breed.entity.CageVideo;
import com.dove.breed.mapper.CagePictureMapper;
import com.dove.breed.mapper.CagePositionMapper;
import com.dove.breed.mapper.CageVideoMapper;
import com.dove.breed.service.CageService;
import com.dove.breed.service.CageVideoService;
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
    @Autowired
    private CageVideoService cageVideoService;

    private int width = 1600;
    private int height = 900;

    //定时器要调用的方法，遍历所有鸽笼，把图片转为视频
    public void updateMp4() throws FrameRecorder.Exception {
        //获取所有鸽笼
            List<String> cageIds = cagePositionMapper.getAllCageId(); //实际上是bc_no
            //遍历所有鸽笼
            for (String cageId : cageIds) {
                //根据cageId获取该cage前一天的24张图片
                List<String> urls = cagePictureMapper.getYesterdayUrl(cageId);
                if (urls.size() != 0){
                Map<Integer, File> imgMap = new HashMap<Integer, File>();
                int num = 0;
                for (String url : urls) {
                    System.out.println("/usr/local/go-fastdfs/files" + url.substring(7));
                    File file = new File("/usr/local/go-fastdfs/files" + url.substring(7));

                    imgMap.put(num,file);
                    num ++;
                }
                File file = new File("/usr/local/go-fastdfs/files/cageVideo/" + UUID.randomUUID() + ".mp4");
                System.out.println(num);
                createMp4(file,imgMap,width,height,cageId);
            }
        }
    }

    protected void createMp4(File file1, Map<Integer, File> imgMap, int width, int height, String cageId) throws FrameRecorder.Exception {
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
            for (int i = 0; i < imgMap.size(); i++) {
                BufferedImage read = ImageIO.read(imgMap.get(i));
                //一秒是25帧 所以要记录25次
                for (int j = 0; j < 10; j++) {
                    recorder.record(converter.getFrame(read));
                }
            }
            System.out.println(cageId);
            //弄好之后，把路径存进数据库
            String videoPath = "/group1/" + file1.getPath().substring(28);
            System.out.println("存入数据库的路径为：" + videoPath);
            CageVideo cageVideo = new CageVideo(cageId, videoPath);
            boolean save = cageVideoService.save(cageVideo);
            if (save){
                System.out.println("toMp4成功！");
            }else{
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
