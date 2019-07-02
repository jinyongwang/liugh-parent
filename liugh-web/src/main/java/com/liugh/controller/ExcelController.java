package com.liugh.controller;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.sheet.wrapper.NoTemplateSheetWrapper;
import com.github.crab2died.sheet.wrapper.NormalSheetWrapper;
import com.liugh.annotation.Pass;
import com.liugh.base.PublicResultConstant;
import com.liugh.config.ResponseHelper;
import com.liugh.config.ResponseModel;
import com.liugh.model.Student1;
import com.liugh.model.Student2;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by liugh on 2018/11/10.
 * 注意！！excel 模板文件中的        定义符	               描述	           优先级(大到小)
                                 $appoint_line_style	当前行样式	           3
                                $single_line_style	   单行样式	               2
                                $double_line_style	   双行样式	               2
                                $default_style	       默认样式	               1
                                $data_index	          数据插入的起始位置	   -
                                $serial_number    	  插入序号标记             -
 *
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {


    @Pass
    @GetMapping(value = "/testObject2Excel")
    public ResponseModel testObject2Excel() throws Exception{
        String tempPath = "/excelTemplate/normal_template.xlsx";
        List<Student1> list = new ArrayList<>();
        list.add(new Student1("1010001", "盖伦", "六年级三班"));
        list.add(new Student1("1010002", "古尔丹", "一年级三班"));
        list.add(new Student1("1010003", "蒙多(被开除了)", "六年级一班"));
        list.add(new Student1("1010004", "萝卜特", "三年级二班"));
        list.add(new Student1("1010005", "奥拉基", "三年级二班"));
        list.add(new Student1("1010006", "得嘞", "四年级二班"));
        list.add(new Student1("1010007", "瓜娃子", "五年级一班"));
        list.add(new Student1("1010008", "战三", "二年级一班"));
        list.add(new Student1("1010009", "李四", "一年级一班"));
        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        FileOutputStream os = new FileOutputStream(new File("E://A.xlsx"));
        ExcelUtils.getInstance().exportObjects2Excel(tempPath, list, data, Student1.class, false, os);
        os.close();
        // 不基于模板导出Excel
        ExcelUtils.getInstance().exportObjects2Excel(list, Student1.class, true, null, true, "E://B.xlsx");
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }

    // 基于模板、注解的多sheet导出
    @Pass
    @GetMapping(value = "/testObject2BatchSheet")
    public ResponseModel testObject2BatchSheet() throws Exception{

        List<NormalSheetWrapper> sheets = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Student1> list = new ArrayList<>();
            list.add(new Student1("1010001", "盖伦", "六年级三班"));
            list.add(new Student1("1010002", "古尔丹", "一年级三班"));
            list.add(new Student1("1010003", "蒙多(被开除了)", "六年级一班"));
            list.add(new Student1("1010004", "萝卜特", "三年级二班"));
            list.add(new Student1("1010005", "奥拉基", "三年级二班"));
            list.add(new Student1("1010006", "得嘞", "四年级二班"));
            list.add(new Student1("1010007", "瓜娃子", "五年级一班"));
            list.add(new Student1("1010008", "战三", "二年级一班"));
            list.add(new Student1("1010009", "李四", "一年级一班"));
            Map<String, String> data = new HashMap<>();
            data.put("title", "战争学院花名册");
            data.put("info", "学校统一花名册");
            sheets.add(new NormalSheetWrapper(i, list, data, Student1.class, false));
        }

        String tempPath = "/excelTemplate/normal_batch_sheet_template.xlsx";
        FileOutputStream os = new FileOutputStream(new File("E://JK.xlsx"));
        // 基于模板导出Excel
        ExcelUtils.getInstance().normalSheet2Excel(sheets, tempPath, "E://AA.xlsx");
        ExcelUtils.getInstance().normalSheet2Excel(sheets, tempPath, os);
        os.close();
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }


    @GetMapping(value = "/testMap2Excel")
    public ResponseModel testMap2Excel() throws Exception{
        Map<String, List<?>> classes = new HashMap<>();

        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");

        classes.put("class_one", Arrays.asList(
                new Student1("1010009", "李四", "一年级一班"),
                new Student1("1010002", "古尔丹", "一年级三班")
        ));
        classes.put("class_two", Collections.singletonList(
                new Student1("1010008", "战三", "二年级一班")
        ));
        classes.put("class_three", Arrays.asList(
                new Student1("1010004", "萝卜特", "三年级二班"),
                new Student1("1010005", "奥拉基", "三年级二班")
        ));
        classes.put("class_four", Collections.singletonList(
                new Student1("1010006", "得嘞", "四年级二班")
        ));
        classes.put("class_six", Arrays.asList(
                new Student1("1010001", "盖伦", "六年级三班"),
                new Student1("1010003", "蒙多", "六年级一班")
        ));

        ExcelUtils.getInstance().exportMap2Excel("/excelTemplate/map_template.xlsx",
                0, classes, data, Student1.class, false, "E://C.xlsx");
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }
    @GetMapping(value = "/testList2Excel")
    public ResponseModel testList2Excel() throws Exception{
        List<List<String>> list2 = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> _list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                _list.add(i + " -- " + j);
            }
            list2.add(_list);
            header.add(i + "---栏");
        }
        ExcelUtils.getInstance().exportObjects2Excel(list2, header, "E://D.xlsx");


        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }

    //多sheet无模板、基于注解的导出
    @GetMapping(value = "/testBatchNoTemplate2Excel")
    public ResponseModel testBatchNoTemplate2Excel() throws Exception{
        List<NoTemplateSheetWrapper> sheets = new ArrayList<>();

        for (int s = 0; s < 3; s++) {
            List<Student2> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                list.add(new Student2(10000L + i, "学生" + i, new Date(), 201, false));
            }
            sheets.add(new NoTemplateSheetWrapper(list, Student2.class, true, "sheet_" + s));
        }
        ExcelUtils.getInstance().noTemplateSheet2Excel(sheets, "E://EE.xlsx");

        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }
    @GetMapping(value = "/excel2Object")
    public ResponseModel excel2Object() throws Exception{
        String path = "/excelTemplate/students_01.xlsx";

        System.out.println("读取全部：");
        List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class);
        for (Student1 stu : students) {
            System.out.println(stu);
        }
        System.out.println("读取指定行数：");
        students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 3, 0);
        for (Student1 stu : students) {
            System.out.println(stu);
        }
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }


    @GetMapping(value = "/excel2Object2")
    public ResponseModel excel2Object2() throws Exception{
        String path = "/excelTemplate/students_02.xlsx";
        // 1)
        // 不基于注解,将Excel内容读至List<List<String>>对象内
        List<List<String>> lists = ExcelUtils.getInstance().readExcel2List(path, 1, 2, 0);
        System.out.println("读取Excel至String数组：");
        for (List<String> list : lists) {
            System.out.println(list);
        }

        // 2)
        // 基于注解,将Excel内容读至List<Student2>对象内
        // 验证读取转换函数Student2ExpelConverter
        // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
        List<Student2> students = ExcelUtils.getInstance().readExcel2Objects(path, Student2.class, 0, 0);
        System.out.println("读取Excel至对象数组(支持类型转换)：");
        for (Student2 st : students) {
            System.out.println(st);
        }
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }

}
