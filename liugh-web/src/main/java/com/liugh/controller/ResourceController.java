package com.liugh.controller;

import com.liugh.base.BusinessException;
import com.liugh.base.Constant;
import com.liugh.base.PublicResultConstant;
import com.liugh.config.ResponseHelper;
import com.liugh.config.ResponseModel;
import com.liugh.util.ComUtil;
import com.liugh.util.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author liugh
 * @since on 2018/5/11.
 */
@RestController
@RequestMapping("/resource")
//不加入swagger ui里
@ApiIgnore
public class ResourceController {

    @PostMapping
    public ResponseModel uploadResource(@RequestParam("files")MultipartFile[] multipartFiles) throws Exception {
        List<String> filePaths = new ArrayList<>();
        if(!ComUtil.isEmpty(multipartFiles) && multipartFiles.length != 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                int fileType =  FileUtil.getFileType(multipartFile.getOriginalFilename());
                filePaths.add(
                        FileUtil.saveFile(multipartFile.getInputStream(), fileType, multipartFile.getOriginalFilename(), null)
                );
            }
        }
        return ResponseHelper.buildResponseModel(filePaths);
    }


    @ApiOperation(value="上传图片,返回原图和缩略图", notes="文件MultipartFile类型",produces = "application/from-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件"
                    , required = true, dataType = "MultipartFile",paramType="form")
    })
    @PostMapping("/thumbnail")
    public ResponseModel uploadThumbnail(@RequestParam("imgs")MultipartFile[] multipartFiles) throws Exception {
        List<HashMap> filePaths = new ArrayList<>();
        if(!ComUtil.isEmpty(multipartFiles) && multipartFiles.length != 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                String postFix = multipartFile.getOriginalFilename().split("//.")[multipartFile.getOriginalFilename().split("//.").length-1];
                if(Arrays.asList(Constant.FilePostFix.IMAGES).contains(postFix)){
                    throw new BusinessException("请上传图片");
                }
                HashMap<String,String>  retMap = new HashMap<>();
                String url = FileUtil.saveFile(multipartFile.getInputStream(), 1, multipartFile.getOriginalFilename(), null);
                retMap.put("url",url);
                retMap.put("preUrl",url);
                //大于2m时启动图片压缩
                if(multipartFile.getSize() >= FileUtil.FILE_SIZE){
                    String preUrl = FileUtil.savePreFile(url);
                    retMap.put("preUrl",preUrl);
                }
                filePaths.add(retMap);
            }
        }
        return ResponseHelper.buildResponseModel(filePaths);
    }

    @DeleteMapping
    public ResponseModel deleteResource(@RequestParam("filePaths") List<String> filePaths){
        if(!ComUtil.isEmpty(filePaths) && filePaths.size() !=0){
            for (String item: filePaths) {
                if(!FileUtil.deleteUploadedFile(item)){
                    return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
                }
            }
        }
        return ResponseHelper.buildResponseModel(filePaths);
    }

}
