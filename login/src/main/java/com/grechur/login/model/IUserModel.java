package com.grechur.login.model;

import java.util.Map;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: IUserModel
 * @Description: model层
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:55
 */
public interface IUserModel {
    void login(String name, String pwd);
    void register(Map<String,Object> map);
}
