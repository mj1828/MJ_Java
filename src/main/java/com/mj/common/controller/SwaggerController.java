package com.mj.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by zyc on 2017/6/20.
 */
@RequestMapping("swagger")
@RestController
public class SwaggerController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "初始化信息")
	public String init() {
		return "初始化接口";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value = "获取列表")
	@ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String")
	public String list(String type) {
		return "list";
	}

}