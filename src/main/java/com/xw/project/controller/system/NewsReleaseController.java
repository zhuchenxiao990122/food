package com.xw.project.controller.system;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;
import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StrUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.FileBase64Dto;
import com.xw.project.entity.NewsRelease;
import com.xw.project.service.system.NewsReleaseService;
import com.xw.project.vo.NewsReleaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 新闻发布-保存到本地 前端控制器
 * </p>
 *
 * @author weiLiang
 * @since 2020-6-30
 */
@RestController
@RequestMapping("/newsRelease")
@Api(
	value = "NewsReleaseController",
	description = "新闻发布-保存本地"
)
public class NewsReleaseController extends BaseController {
   @Autowired
//   @Qualifier("newsReleaseService")
   private NewsReleaseService newsReleaseService;

	@PostMapping(value={"/upload"})
    @ApiOperation(
        value = "上传文件置本地",
        notes = "上传文件置本地"
    )
	public RestResponse<List<String>> upload(@RequestParam("file") MultipartFile[] file) {
		List<String> list = newsReleaseService.saveFiles(file);
		return ResultGenerator.genSuccessResult(list);
	}


	@PostMapping(value = {"/uploadBase64"})
    @ApiOperation(
        value = "上传 Base64 文件置本地",
        notes = "上传 Base64 文件置本地"
    )
	public RestResponse<List<String>> uploadBase64(@RequestParam("file") List<FileBase64Dto> file) {
		if (file == null || file.isEmpty()) {
			return ResultGenerator.genFailResult(DocConstant.upload_file_empty);
		}
		for (FileBase64Dto dto : file) {
			if (StrUtil.isBlank(dto.getBase64())) {
				return ResultGenerator.genFailResult(DocConstant.upload_file_empty);
			}
		}

		List<String> ids = newsReleaseService.saveBase64Files(file);
		return ResultGenerator.genSuccessResult(ids);
	}


	@PostMapping(value={"/addNews"})
    @ApiOperation(
        value = "添加新闻",
        notes = "添加新闻"
    )
	public RestResponse<Integer> addNews(@RequestBody NewsRelease newsRelease) {
		Integer res = newsReleaseService.addNews(newsRelease);
		return ResultGenerator.genSuccessResult(res);
	}

	@GetMapping(value={"/listFiles"})
    @ApiOperation(
        value = "加载所有上传的文件",
        notes = "初始化：加载所有上传的文件"
    )
	public RestResponse<List<NewsReleaseVo>> listFiles() {
		return ResultGenerator.genSuccessResult(newsReleaseService.listFiles());
	}

	@GetMapping(value={"/{id}"})
    @ApiOperation(
        value = "下载文件",
        notes = "根据 ID 下载文件"
    )
	public Object take(@PathVariable("id") String id, HttpServletResponse response) {
		if (StrUtil.isBlank(id)) {
			return ResultGenerator.genFailResult(DocConstant.download_file_emptyId);
		}
		return ResultGenerator.genSuccessResult(newsReleaseService.take(id, response));
	}

}
