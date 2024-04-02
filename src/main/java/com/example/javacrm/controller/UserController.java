package com.example.javacrm.controller;

import com.example.mycnblog.common.AjaxResult;
import com.example.mycnblog.common.Constant;
import com.example.mycnblog.common.SecurityUtil;
import com.example.mycnblog.common.SessionUtil;
import com.example.mycnblog.model.UserInfo;
import com.example.mycnblog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/reg")
    public Object reg(String username, String password) {
        // 1.非空效验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法的参数请求！");
        }
        // 2.进行添加操作
        int result = userService.add(username, SecurityUtil.encrypt(password));
        if (result == 1) {
            return AjaxResult.success("注册成功！", 1);
        } else {
            return AjaxResult.fail(-1, "数据库添加出错！");
        }
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return 如果用户名和密码都正确，返回1；如果用户名或密码为空/不正确，返回非1
     */
    @RequestMapping("/login")
    public int login(HttpServletRequest request, String username, String password) {
        // 1.非空效验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            // 参数有误
            return 0;
        }
        // 2.进行查找操作
        UserInfo userInfo = userService.getUserByName(username);
        if (userInfo == null || userInfo.getId() <= 0) { // userinfo 无效
            // 用户名错误
            return -1;
        } else {
            // 用户名正确
            boolean result = SecurityUtil.decrypt(password, userInfo.getPassword());
            if (result) {
                // 密码正确
                // 将 userinfo 保存到 session 中
                HttpSession session = request.getSession();
                session.setAttribute(Constant.SESSION_USERINFO_KEY, userInfo);
                return 1;
            }
            // 密码错误
            return -1;
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null &&
                session.getAttribute(Constant.SESSION_USERINFO_KEY) != null) {
            // 移除 session 中当前登录的用户
            session.removeAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return true;
    }

    /**
     * 获取个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/myinfo")
    public UserInfo myInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null &&
                session.getAttribute(Constant.SESSION_USERINFO_KEY) != null) {
            return (UserInfo) session.getAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return null;
    }

    /**
     * 获取作者信息
     *
     * @param uid
     * @return
     */
    @RequestMapping("/myinfobyuid")
    public UserInfo myInfoByUid(Integer uid) {
        if (uid != null && uid > 0) {
            return userService.myInfoByUid(uid);
        }
        return null;
    }

    /**
     * 上传头像
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateavatar")
    public int updateAvatar(HttpServletRequest request, MultipartFile file) throws IOException {
        // 上传照片
        // 获取⽂件后缀名
        String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 获取文件名
        String newFileName = UUID.randomUUID() + fileName;
        // ⽂件保存地址
        String filePath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() +
                "/img/" + newFileName;
        // 保存⽂件
        file.transferTo(new File(filePath));

        // 通过 session 获取当前用户信息
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null) {
            // 当前已登录
            // 更新头像
            return userService.updateAvatar(userInfo.getId(), newFileName);
        }
        return 0;
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "Hi,Blog.";
    }
}
