package com.example.javacrm.controller;

import com.example.mycnblog.common.SessionUtil;
import com.example.mycnblog.model.MyIntroduceInfo;
import com.example.mycnblog.model.UserInfo;
import com.example.mycnblog.service.impl.MyIntroduceServiceImpl;
import com.example.mycnblog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户简介控制器
 */
@RestController
@RequestMapping("myintro")
public class MyIntroduceController {

    @Autowired
    private MyIntroduceServiceImpl myIntroduceService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取用户简介
     *
     * @param request
     * @return
     */
    @RequestMapping("/findintro")
    public MyIntroduceInfo myIntroByUid(HttpServletRequest request) {
        // 获取 session 中的用户信息
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null && userInfo.getId() > 0) {
            return myIntroduceService.myIntroByUid(userInfo.getId());
        }
        return null;
    }

    /**
     * 修改用户简介
     *
     * @param request
     * @param username
     * @param sex
     * @param briefinfo
     * @param address
     * @param birthday
     * @param csdnsite
     * @param githubsite
     * @return
     */
    @RequestMapping("/update")
    public int update(HttpServletRequest request, String username, String sex, String briefinfo,
                      String address, String birthday, String csdnsite, String githubsite) {
        // 获取 session 中的用户信息
        UserInfo userInfo = SessionUtil.getLoginUser(request);

        // 数据处理
        if (!StringUtils.hasLength(username)) {
            // 用户名不能为空
            return -1;
        }

        if (!StringUtils.hasLength(birthday)) {
            // 生日在数据库中是 date 类型，如果是""，存不进去
            birthday = null;
        }

        if (!StringUtils.hasLength(briefinfo)) {
            // briefinfo 是非空的，在 mapper 中处理过，如果 briefinfo==null，默认为 '作者很懒，什么简介都没有~'
            briefinfo = null;
        }

        // 当前是否登录
        if (userInfo != null && userInfo.getId() > 0) {
            // 当前用户是否已有简介
            MyIntroduceInfo myIntroduceInfo = myIntroduceService.myIntroByUid(userInfo.getId());
            if (myIntroduceInfo == null) {
                // 当前用户没有简介，就新增简介
                return myIntroduceService.add(sex, briefinfo, address, birthday, csdnsite, githubsite, userInfo.getId());
            } else {
                // 当前用户已有简介，就修改简介
                return myIntroduceService.update(username, sex, briefinfo, address, birthday, csdnsite, githubsite, userInfo.getId());
            }
        }
        return 0;
    }
}
