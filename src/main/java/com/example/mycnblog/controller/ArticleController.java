package com.example.mycnblog.controller;

import com.example.mycnblog.common.AjaxResult;
import com.example.mycnblog.common.SessionUtil;
import com.example.mycnblog.model.ArticleInfo;
import com.example.mycnblog.model.UserInfo;
import com.example.mycnblog.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章控制器
 */
@RestController
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    /**
     * 查询用户的所有文章
     *
     * @param request
     * @return
     */
    @RequestMapping("/mylist")
    public List<ArticleInfo> myList(HttpServletRequest request) {
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null) {
            return articleService.getMyList(userInfo.getId());
        }
        return null;
    }

    /**
     * 分页——查询所有文章
     *
     * @param PageIndex
     * @param PageSize
     * @return
     */
    @RequestMapping("/list")
    public List<ArticleInfo> getList(Integer PageIndex, Integer PageSize) {
        if (PageIndex == null || PageSize == null || PageIndex <= 0 || PageSize <= 0) {
            return null;
        }
        // 分页公式，计算偏移量
        int Offset = (PageIndex - 1) * PageSize;
        return articleService.getList(Offset, PageSize);
    }

    /**
     * 跳转文章列表尾页——获取尾页
     *
     * @param PageSize
     * @return
     */
    @RequestMapping("/totalpage")
    public Integer getTotalPage(Integer PageSize) {
        if (PageSize != null && PageSize > 0) {
            // 参数有效
            // 文章总数
            int TotalCount = articleService.getTotalCount();
            // 总页数
            int TotalPage = (int) Math.ceil(TotalCount * 1.0 / PageSize);
            return TotalPage;
        }
        return null;
    }

    /**
     * 获取文章的详情信息
     *
     * @param aid
     * @return
     */
    @RequestMapping("/detail")
    public Object getDetil(Integer aid) {
        if (aid != null && aid > 0) {
            return AjaxResult.success(articleService.getDetil(aid));
        }
        return AjaxResult.fail(-1, "查询失败");
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
            ArticleInfo articleInfo = articleService.getDetil(aid);
            // 文章的归属人验证
            UserInfo userInfo = SessionUtil.getLoginUser(request);
            if (userInfo != null && articleInfo != null &&
                    userInfo.getId() == articleInfo.getUid()) { // 文章归属人正确
                return AjaxResult.success(articleInfo);
            }
        }
        return AjaxResult.fail(-1, "查询失败");
    }

    /**
     * 修改页面，修改文章
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
            return articleService.update(aid, userInfo.getId(), title, content);
        }
        return 0;
    }

    /**
     * 博客列表，删除文章
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
            return articleService.delete(aid);
        }
        return 0;
    }

    /**
     * 博客编辑，发布文章
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
            return articleService.add(userInfo.getId(), title, content);
        }
        return 0;
    }
}
