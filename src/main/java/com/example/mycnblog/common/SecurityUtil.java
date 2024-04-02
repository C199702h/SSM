package com.example.mycnblog.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 加盐加密类
 */
public class SecurityUtil {

    /**
     * 加盐加密
     *
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        // 每次生成内容不同的，但长度固定的 32 位盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 最终密码 = （md5）（盐值 + 原始密码）
        String finalPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        return salt + finalPassword;
    }

    /**
     * 密码验证
     *
     * @param password      待验证密码
     * @param finalPassword 数据库中加盐的密码
     * @return
     */
    public static boolean decrypt(String password, String finalPassword) {
        // 非空效验
        if (!StringUtils.hasLength(password) || !StringUtils.hasLength(finalPassword)) {
            return false;
        }
        // 最终密码不正确
        if (finalPassword.length() != 64) {
            return false;
        }
        // 获取盐值
        String salt = finalPassword.substring(0, 32);
        // 加盐密码 = （md5）（盐值 + 待确定密码）
        String securityPassword =
                DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // （盐值 + 加盐密码） 与 （最终密码） 进行比较
        return (salt + securityPassword).equals(finalPassword);
    }

    public static void main(String[] args) {
        String password = "123";
//        System.out.println(SecurityUtil.encrypt(password));
//        System.out.println(SecurityUtil.encrypt(password));
//        System.out.println(SecurityUtil.encrypt(password));

        System.out.println(SecurityUtil.decrypt(password,"7575817701bb453f8992a18dded9f7c49e8271ce06444e172abc38a3464f37b4"));
        System.out.println(SecurityUtil.decrypt(password,"4cc41efe52cc4875b27e12e9336d6726dba203737c42a92f9f81a376cf054a0e"));
        System.out.println(SecurityUtil.decrypt(password,"69f0a6c324eb4b23b2907ce6f0436ae4eff9831cbef315c04c73fecf47f7e742"));
    }


    /**
     * 7575817701bb453f8992a18dded9f7c49e8271ce06444e172abc38a3464f37b4
     * 4cc41efe52cc4875b27e12e9336d6726dba203737c42a92f9f81a376cf054a0e
     * 69f0a6c324eb4b23b2907ce6f0436ae4eff9831cbef315c04c73fecf47f7e741
     */
}
