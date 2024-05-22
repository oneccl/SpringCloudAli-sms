package com.cc.smscommon.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/2/13
 * Time: 13:43
 * Description:
 */

// 文件、图片上传处理器

// 前端页面
// enctype="multipart/form-data：表示有文件上传的表单
/*
<form action="upload.do" method="post" enctype="multipart/form-data">
    <p>用户名：<input type="text" name="username" placeholder="请输入用户名"></p>
    <p>上传文件：<input type="file" name="filename"></p>
    <p><input type="submit" value="提交"><input type="reset" value="重置"></p>
</form>
*/
public class FileHandler {

    // common-fileUpload三大核心类

    // 1、DiskFileItemFactory: 磁盘文件对象
    // 作用：磁盘文件工厂类，可设置缓冲区大小以及临时保存位置
    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置一个缓冲区，当接收数据大于缓冲区时，将他放到临时文件中
        factory.setSizeThreshold(1024);  // 缓冲区大小1M，默认10240（10KB）
        factory.setRepository(file); // 默认保存到临时目录
        return factory;
    }

    // 2、ServletFileUpload: 文件上传对象
    // 作用：文件上传类，监听上传进度, 处理乱码等
    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 监听上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long curLen, long allLen, int i) {
                System.out.println("文件总大小："+allLen+", 已上传："+curLen+", 进度："+(curLen/allLen)*100+"%");
            }
        });
        upload.setHeaderEncoding("UTF-8");  // 处理乱码问题
        upload.setFileSizeMax(10*1024*1024);  // 单个上传的文件的大小限制：最大10M
        upload.setSizeMax(100*1024*1024);  // 上传的总文件的大小限制：最大100M
        return upload;
    }

    // 3、FileItem：表单数据对象
    // 作用：解析前端请求，封装成FileItem对象，FileItem中保存了表单数据项的所有信息
    public static List<FileItem> parseRequest(ServletFileUpload upload, HttpServletRequest request) throws FileUploadException {
        return upload.parseRequest(request);
    }

    // 分类处理
    // 普通数据项(普通表单)：isFormField()=true；getFieldName()：标签中name属性；getString()：name对应值
    // 文件数据项(带文件表单)：isFormField()=false, getName()：文件路径
    public static void fiHandler(List<FileItem> fileItems,HttpServletRequest request) throws Exception {
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()){
                handlerFormField(fileItem,request);
            }else {
                handlerFileField(fileItem,request);
            }
        }
    }

    // 处理普通数据项(普通表单)
    public static void handlerFormField(FileItem fi,HttpServletRequest request) throws Exception {
        String fieldName = fi.getFieldName();  // name属性
        String value = fi.getString("UTF-8");  // value值
        System.out.println(fieldName+":"+value);
    }

    // 文件数据项(带文件表单): 处理文件
    public static void handlerFileField(FileItem fi,HttpServletRequest request) throws Exception {
        // 获取文件路径
        String filePath = fi.getName();
        // 1、文件处理，非法过滤
        if (!illegalFilter(fi, request, filePath)) {
            return;
        }
        // 2、生成文件存储位置
        File realPathDir = realPathGenerator(request, filePath);
        // 3、文件上传保存
        // 将文件保存到服务器该路径下: ../files/fileName.uuid/文件
        fi.write(realPathDir);
        // 删除临时文件
        fi.delete();
        request.setAttribute("file",realPathDir);
    }

    // 1、文件处理，非法过滤
    private static Boolean illegalFilter(FileItem fi, HttpServletRequest request, String filePath) {
        // 文件路径是否合法
        if (filePath ==null || filePath.trim().equals("")){
            request.setAttribute("msg","文件不合法!");
            return false;
        }
        // 控制文件大小
        if (fi.getSize()>10*1024*1024){
            request.setAttribute("msg","文件超过10M啦!");
            return false;
        }
        // 控制只能上传图片（可选）
        if (!fi.getContentType().startsWith("image")){
            request.setAttribute("msg","文件格式不匹配!");
            return false;
        }
        // 文件后缀
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        // 控制文件后缀
        if (!suffix.equalsIgnoreCase("jpg") || !suffix.equalsIgnoreCase("png")){
            request.setAttribute("msg","文件类型不匹配!");
            return false;
        }
        return true;
    }

    // 2、生成文件存储位置
    private static File realPathGenerator(HttpServletRequest request, String filePath) {
        // 随机生成一个UUID
        String uuid = UUID.randomUUID().toString();
        // 获取当前项目下的files目录的绝对位置: ../files
        String basePath = request.getSession().getServletContext().getRealPath("files");
        // 获取文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 给文件创建一个名为通用码的文件夹: ../files/fileName.uuid
        File realPathDir = new File(basePath,fileName+"."+uuid);
        if (!realPathDir.exists()){
            realPathDir.mkdir();
        }
        return realPathDir;
    }

}
