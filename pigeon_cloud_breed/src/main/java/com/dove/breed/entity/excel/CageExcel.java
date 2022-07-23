package com.dove.breed.entity.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2022-03-21-14:45
 */
@Data
public class CageExcel extends BaseRowModel {
    @ExcelProperty(index = 0, value = "鸽笼编号")
     @ExcelIgnore
    String bc_no;
    @ExcelProperty(index = 0,value = "排数")
    Integer row_no;
    @ExcelProperty(index = 1,value = "行数")
    Integer line;
    @ExcelProperty(index = 2,value = "列数")
    Integer column_no;
    @ExcelProperty(index = 3,value = "状态")
    String state;
    @ExcelProperty(index = 4,value = "提示灯")
    String xf;
    @ExcelProperty(index = 5,value = "异常")
    String abnormal;
    @ExcelProperty(index = 6,value = "流程结束")
    String is_end;
    @ExcelProperty(index = 7,value = "时间")
    Date create_time;
}
