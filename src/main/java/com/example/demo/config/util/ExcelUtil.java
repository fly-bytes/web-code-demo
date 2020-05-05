package com.example.demo.config.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.SyncReadListener;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static void write(HttpServletResponse response, String fileName, String sheetName, Class clazz, List data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(data);
    }

    public static List<?> read(MultipartFile file, Class clazz) throws IOException {
        SyncReadListener readListener = new SyncReadListener();
        EasyExcel.read(file.getInputStream(), clazz, readListener).sheet().doRead();
        return readListener.getList();
    }
}
