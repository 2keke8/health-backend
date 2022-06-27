package com.zky.health.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zky.health.dao.OrderMapper;
import com.zky.health.entity.Order;
import com.zky.health.service.OrderServcie;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public File downloadModel() {
        File file;
        // TODO: 2022/6/25 下载模板文件
        return null;
    }

    @Override
    public boolean insertExcel(String fileName, MultipartFile file) {
        InputStream is = null;
        Workbook wb = null;
        boolean isExcel2003=true;
        String dataFormat ="";
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

//        遍历每一行
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {

            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            //遍历行中每一个单元格
            Order order = new Order();

            String member_id = "";
            if (row.getCell(0) == null) {
                member_id="";
            } else {
                row.getCell(0).setCellType(CellType.STRING);
                member_id = row.getCell(0).getStringCellValue();
            }
            String  orderDate = "";
            CellType cellType = row.getCell(1).getCellType();
            if (HSSFDateUtil.isCellDateFormatted(row.getCell(1))) {
                // 获取日期类型的单元格的值

                Date d = row.getCell(1).getDateCellValue();
                // 进行格式转换
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                orderDate = formatter.format(d);
            }
            String orderType = "";
            if (row.getCell(2) == null) {
                orderType = "";
            } else {
                row.getCell(2).setCellType(CellType.STRING);
                orderType = row.getCell(2).getStringCellValue();
            }
            String orderStatus = "";
            if (row.getCell(3) == null) {
                orderStatus = "";
            } else {
                row.getCell(3).setCellType(CellType.STRING);
                orderStatus = row.getCell(3).getStringCellValue();
            }
            String setmeal_id = "";
            if (row.getCell(4) == null) {
                setmeal_id = "";
            } else {
                row.getCell(4).setCellType(CellType.STRING);
                setmeal_id = row.getCell(4).getStringCellValue();
            }
            order.setOrderstatus(orderStatus);
//            字符串解析日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                order.setOrderdate(simpleDateFormat.parse(orderDate));
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
            order.setOrdertype(orderType);
            order.setMemberId(Integer.parseInt(member_id));
            order.setSetmealId(Integer.parseInt(setmeal_id));
//            添加到数据库里
            Integer num = orderMapper.insertSelective(order);
            if(num==0){
                return false;
            }
        }

        return true;
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

    @Override
    public Order selectByPrimaryKey(Integer integer) {

        return orderMapper.selectByPrimaryKey(integer);

    }
}
