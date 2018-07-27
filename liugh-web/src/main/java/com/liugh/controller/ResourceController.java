package com.liugh.controller;

import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.util.ComUtil;
import com.liugh.util.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
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
    public PublicResult uploadResource(@RequestParam("files")MultipartFile[] multipartFiles) throws Exception {
        List<String> filePaths = new ArrayList<>();
        if(!ComUtil.isEmpty(multipartFiles) && multipartFiles.length != 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                int fileType =  FileUtil.getFileType(multipartFile.getOriginalFilename());
                filePaths.add(
                        FileUtil.saveFile(multipartFile.getInputStream(), fileType, multipartFile.getOriginalFilename(), null)
                );
            }
        }
        return new PublicResult<List>(PublicResultConstant.SUCCESS, filePaths);
    }

    @DeleteMapping
    public PublicResult deleteResource(@RequestParam("filePaths") List<String> filePaths){
        if(!ComUtil.isEmpty(filePaths) && filePaths.size() !=0){
            for (String item: filePaths) {
                if(!FileUtil.deleteUploadedFile(item)){
                    return new PublicResult<String>(PublicResultConstant.ERROR, null);
                }
            }
        }
        return new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

}
