package com.github.jartisan.springbootdemo.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jartisan.parent.base.utils.ZipFileUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
/***
 * @author wjl
 * @date: 2016年2月26日 上午11:39:24
 */
public class FileTest {
	private static Logger log = LoggerFactory.getLogger(FileTest.class);
	String fileName = "e:" + File.separator + "test"+ File.separator+ DateFormatUtils.format(new Date(), "yyyyMMdd");
	String filePath = "e:" + File.separator + "test";
	String zipfileName = "e:" + File.separator + "test" + File.separator+ DateFormatUtils.format(new Date(), "yyyyMMdd") + ".zip";

	@Test
	public void fileWrite() throws IOException {
		final int testCount = 10;
		final int testAppendCount = 3;
		for(int i=0;i<testCount;i++){
			log.info("开始生成第 {} 文件",i);
			File dest = new File(fileName+ i + ".txt");
			if (!dest.exists()) {
				log.info("该文件不存在,创建文件{}",dest);
				Files.createParentDirs(dest);
				dest.createNewFile();
				log.info("创建完成");
			}
			for (int j = 0; j < testAppendCount; j++) {
				log.info("开始追加第 {} 文件内容",j);
				String hamletQuoteStart = "13785641151" + "\r\n";
				Files.append(hamletQuoteStart, dest, Charsets.UTF_8);
			}
		}
	}

	 @Test
	 public void testCompressFiles2Zip() {
			log.info("开始压缩,目录:{}",filePath);
	        //存放待压缩文件的目录
	        File srcFile = new File(filePath);
	        //压缩后的zip文件路径
	        String zipFilePath =zipfileName;
	        log.info("压缩文件名为{}",zipfileName);
	        File descFile = new File(zipFilePath);
	        if(descFile.exists()){
	        	log.info("目标压缩文件已存在",zipfileName);
	        	log.info("删除开始");
	        	descFile.delete();
	        	log.info("删除完毕");
	        }
	        if(srcFile.exists()) {
	            File[] files = srcFile.listFiles();
	            log.info("压缩开始");
	            ZipFileUtil.compressFiles2Zip(files, zipFilePath);
	            log.info("压缩结束");
	        }
	    }
	     
    @Test
    public void testDecompressZip()  {
        //压缩包所在路径
        String zipFilePath = "d:/test2/test.zip";
        //解压后的文件存放目录
        String saveFileDir = "d:/test2/";
        //调用解压方法
        ZipFileUtil.decompressZip(zipFilePath, saveFileDir);
    }
}
