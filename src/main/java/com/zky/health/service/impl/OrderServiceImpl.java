package com.zky.health.service.impl;

import com.zky.health.dao.OrderMapper;
import com.zky.health.entity.Order;
import com.zky.health.service.OrderServcie;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 16:26
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class OrderServiceImpl implements OrderServcie {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public boolean insertExcel(String fileName, MultipartFile file) {
        InputStream is = null;
        Workbook wb = null;
        boolean isExcel2003=true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        try {
            try {
                is = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        Order order ;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            String zddm = "";
            if (row.getCell(0) == null) {
//                return "导入失败(第" + (r + 1) + "列,代码没有填写)";
            } else {
                row.getCell(0).setCellType(CellType.STRING);
                zddm = row.getCell(0).getStringCellValue();
            }
            String zdmc = "";
            if (row.getCell(1) == null) {
//                return "导入失败(第" + (r + 1) + "列,名称没有填写)";
            } else {
                row.getCell(1).setCellType(CellType.STRING);
                zdmc = row.getCell(1).getStringCellValue();
            }
            String itemDesc = "";
            if (row.getCell(2) == null) {
                itemDesc = "";
            } else {
                row.getCell(2).setCellType(CellType.STRING);
                itemDesc = row.getCell(2).getStringCellValue();
            }
            vocabularyDetail = new VocabularyDetail();
            vocabularyDetail.setITEM_DESC(itemDesc);
            vocabularyDetail.setItemCD(zddm);
            vocabularyDetail.setItemName(zdmc);
            vocabularyDetailsList.add(vocabularyDetail);
        }
        return false;
    }

    public List<Order> selectAllOrders(){

        return orderMapper.selectAllOrders();

    }

    /**
     *
     * @param orderid
     * @return >0 成功 0:失败 -1：已经预约
     */
    @Override
    public int affirmOrder(int orderid) {

        Order order = orderMapper.selectByPrimaryKey(orderid);
        if(order == null) return 0;

        if(1 == Integer.parseInt(order.getOrderstatus())) return -1;

        order.setOrderstatus(String.valueOf(1));
        int i = orderMapper.updateByPrimaryKey(order);

        return i;
    }
}
