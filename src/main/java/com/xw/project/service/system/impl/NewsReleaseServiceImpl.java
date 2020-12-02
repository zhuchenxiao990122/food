package com.xw.project.service.system.impl;

import com.itextpdf.text.pdf.codec.Base64;
import com.xw.common.util.FileUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.FileBase64Dto;
import com.xw.project.entity.NewsRelease;
import com.xw.project.mapper.NewsReleaseMapper;
import com.xw.project.service.system.NewsReleaseService;
import com.xw.project.vo.NewsReleaseVo;
import com.xw.system.entity.SysFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weiLiang
 * @Date: 2020/6/30
 * @Description: 新闻发布-保存本地 服务实现类
 */
@Service
@Slf4j
public class NewsReleaseServiceImpl implements NewsReleaseService {
    @Autowired
    private NewsReleaseMapper newsReleaseMapper;
    @Value("${app.constant.file-save-path}")
    private String BASE_PATH;

    @Override
    public List<String> saveFiles(MultipartFile[] fileList) {
        List<String> ids = new ArrayList<>(fileList.length);
        for (MultipartFile file : fileList) {
            //获取字段信息，保存到数据库
            SysFileInfo sysfileInfo = new SysFileInfo();
            String id = UUIDGenerator.getUUID();
            sysfileInfo.setRefId(id);
            sysfileInfo.setFileName(id + file.getOriginalFilename());
            sysfileInfo.setFileType(file.getContentType());
            sysfileInfo.setFileSize(String.valueOf(file.getSize()));
            sysfileInfo.setFilePath(BASE_PATH + sysfileInfo.getFileName());
            sysfileInfo.setUpdateDate(new Date());
            newsReleaseMapper.insert(sysfileInfo);

            //保存文件到本地
            OutputStream out = null;
            try {
                File f = new File(sysfileInfo.getFilePath());
                File baseFilePath = new File(BASE_PATH);
                // 如果目录不存在创建目录
                if(!baseFilePath.exists()){
                    baseFilePath.mkdirs();
                }
                // 如果文件不存在，创建文件
                if (!f.exists()) {
                    f.createNewFile();
                }
                out = new FileOutputStream(f);
                byte[] ss = file.getBytes();
                for(int i=0; i<ss.length; i++){
                    out.write(ss[i]);
                }
            } catch (IOException e) {
                log.error("文件保存失败", e);
                return new ArrayList<>();
            }finally {
                try{
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            ids.add(String.valueOf(id));
        }
        return ids;
    }

    @Override
    public List<String> saveBase64Files(List<FileBase64Dto> file){
        List<String> ids = new ArrayList<>(file.size());

		for (FileBase64Dto dto : file) {
		    //获取字段信息，保存到数据库
			SysFileInfo sysfileInfo = new SysFileInfo();
			String id = UUIDGenerator.getUUID();
			sysfileInfo.setRefId(id);
            sysfileInfo.setFileName(id + dto.getFileName());
            sysfileInfo.setFileSize(String.valueOf(dto.getBase64().getBytes().length));
            sysfileInfo.setFilePath(BASE_PATH + sysfileInfo.getFileName());
            sysfileInfo.setUpdateDate(new Date());
            newsReleaseMapper.insert(sysfileInfo);

            //保存文件到本地
            BASE64Decoder decoder = new BASE64Decoder();
            OutputStream out = null;
            try {
                File f = new File(sysfileInfo.getFilePath());
                if (!f.exists()) {
                    f.createNewFile();
                }
                byte[] b = decoder.decodeBuffer(dto.getBase64());
                out = new FileOutputStream(f);
                out.write(b);
                out.flush();
            } catch (IOException e) {
                log.error("文件保存失败", e);
                return new ArrayList<>();
            }finally {
                try{
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
			ids.add(String.valueOf(id));
		}
		return ids;
    }

    @Override
    public Integer addNews(NewsRelease newsRelease){
        String[] ids = newsRelease.getFilesId();
        String str = "";
        String id = UUIDGenerator.getUUID();
        for(int i=0; i<ids.length; i++){
            str += ids[i]+";";
        }
        Integer i = newsReleaseMapper.addNews(id, newsRelease, str);
        return  i;
    }

    @Override
    public List<NewsReleaseVo> listFiles(){
        return newsReleaseMapper.listFiles();
    }

    @Override
    public Object take(String id, HttpServletResponse response){
        NewsReleaseVo sysFileInfo = newsReleaseMapper.selectNewsById(id);
		if (sysFileInfo.getFilePath() == null) {
			return DocConstant.download_file_empty;
		}
        InputStream inStream = null;
		OutputStream outputStream = null;
		try {
		    inStream = new FileInputStream(sysFileInfo.getFilePath());
			outputStream = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			long t1 = System.currentTimeMillis();
			while((length = inStream.read(b)) != -1){
			    outputStream.write(b,0,length);
            }
			long t2 = System.currentTimeMillis();
			System.err.println("耗时:" + (t2 - t1));
			inStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			log.error("文件响应失败", e);
			return DocConstant.download_file_error;
		}
		return null;
    }


    //上面方法封装出来的函数
    private File getFile(SysFileInfo fileInfo) {
		if (fileInfo == null) {
			return null;
		}
		File file = new File(BASE_PATH + fileInfo.getFilePath());
		return file.exists() ? file : null;
	}
}
