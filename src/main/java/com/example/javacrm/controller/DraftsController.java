package com.example.javacrm.controller;

import com.example.mycnblog.common.AjaxResult;
import com.example.mycnblog.common.SessionUtil;
import com.example.mycnblog.model.DraftsInfo;
import com.example.mycnblog.model.UserInfo;
import com.example.mycnblog.service.impl.DraftsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 草稿箱控制器
 */
@RestController
@RequestMapping("/dra")
public class DraftsController {

    @Autowired
    private DraftsServiceImpl draftsService;

    /**
     * 查询用户的所有草稿
     *
     * @param request
     * @return
     */
    @RequestMapping("/mylist")
    public List<DraftsInfo> myList(HttpServletRequest request) {
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null) {
            return draftsService.getMyList(userInfo.getId());
        }
        return null;
    }

    /**
     * 修改页面，查询文章
     *
     * @param request
     * @param aid
     * @return
     */
    @RequestMapping("/detailbyid")
    public Object getDetailById(HttpServletRequest request, Integer aid) {
        if (aid != null && aid > 0) {
            // 根据文章查询文章的详情
            DraftsInfo draftsInfo = draftsService.getDetil(aid);
            // 文章的归属人验证
            UserInfo userInfo = SessionUtil.getLoginUser(request);
            if (userInfo != null && draftsInfo != null &&
                    userInfo.getId() == draftsInfo.getUid()) { // 文章归属人正确
                return AjaxResult.success(draftsInfo);
            }
        }
        return AjaxResult.fail(-1, "查询失败");
    }

    /**
     * 草稿列表，删除草稿
     *
     * @param request
     * @param aid
     * @return
     */
    @RequestMapping("/delete")
    public int delete(HttpServletRequest request, Integer aid) {
        // 非空校验
        if (aid == null && aid <= 0) {
            // 参数有误
            return 0;
        }
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null) { // 当前已登录
            return draftsService.delete(aid);
        }
        return 0;
    }

    /**
     * 草稿编辑页面，保存草稿
     *
     * @param request
     * @param aid
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/update")
    public int update(HttpServletRequest request, Integer aid, String title, String content) {
        // 非空校验
        if (aid == null || !StringUtils.hasLength(title) || !StringUtils.hasLength(content)) {
            // 参数有误
            return 0;
        }
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null && userInfo.getId() > 0) {
            return draftsService.update(aid, userInfo.getId(), title, content);
        }
        return 0;
    }

    /**
     * 文章编辑页面，保存草稿
     *
     * @param request
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/add")
    public int add(HttpServletRequest request, String title, String content) {
        // 非空校验
        if (!StringUtils.hasLength(title) || !StringUtils.hasLength(content)) {
            // 参数有误
            return 0;
        }
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null && userInfo.getId() > 0) {
            return draftsService.add(userInfo.getId(), title, content);
        }
        return 0;
    }
}
