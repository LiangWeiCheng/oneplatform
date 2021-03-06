package com.oneplatform.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesuite.common.util.BeanUtils;
import com.jeesuite.mybatis.plugin.pagination.PageParams;
import com.jeesuite.springweb.model.WrapperResponse;
import com.oneplatform.base.LoginContext;
import com.oneplatform.base.model.PageQueryParam;
import com.oneplatform.base.model.PageResult;
import com.oneplatform.user.dao.entity.UserInfoEntity;
import com.oneplatform.user.dto.param.UsersParam;
import com.oneplatform.user.service.UsersService;

import io.swagger.annotations.ApiOperation;

/**
 * generated by www.jeesuite.com
 */
@Controller
@RequestMapping("/users")
public class UsersController {

private @Autowired UsersService usersService;
	
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public @ResponseBody PageResult<UserInfoEntity> pageQueryUserss(@ModelAttribute PageQueryParam param) {
		PageResult<UserInfoEntity> page = usersService.pageQuery(new PageParams(param.getPageNo(), param.getPageSize()), param.getConditions());
		return page;
	}
	
	@ApiOperation(value = "按id查询")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody WrapperResponse<UserInfoEntity> getById(@PathVariable("id") int id) {
		UserInfoEntity entity = usersService.findUsersById(id);
		return new WrapperResponse<>(entity);
	}
	
	@ApiOperation(value = "新增")
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody WrapperResponse<String> addUsers(@RequestBody UsersParam param) {
		UserInfoEntity entity = BeanUtils.copy(param, UserInfoEntity.class);
		LoginContext.getLoginSession();
		usersService.addUsers(entity);
		
		return new WrapperResponse<>();
	}
	
	@ApiOperation(value = "更新")
	@RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody WrapperResponse<String> updateUsers(@RequestBody UsersParam param) {
		UserInfoEntity entity = BeanUtils.copy(param, UserInfoEntity.class);
		LoginContext.getLoginSession();
		usersService.updateUsers(entity);
		
		return new WrapperResponse<>();
	}
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public @ResponseBody WrapperResponse<String> deleteUsers(@PathVariable("id") int id) {
		usersService.deleteUsers(id);
		return new WrapperResponse<>();
	}
	
	
}
