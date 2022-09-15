package com.seconds.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUtils {
    // 敏感词
    public static String[] sensitiveWords = {"尼玛", "站长", "国家领导人", "操"};


    /**
     * 判断用户名称是否为空，长度是否大于15，以及是否包含敏感词
     * @param username
     * @return RegisterResult
     */
    public static RegisterResult checkUsername(String username){
        // 判断用户名是否为null, 防止空指针异常
        if(null == username || username.length() == 0) return new RegisterResult(false, "用户名不允许为空！");

        // 判断用户名长度是否超过15
        if(username.length() > 15) return new RegisterResult(false, "用户名称长度不允许超过15！");

        // 判断用户名是否包含敏感词, 构建字典树
        Trie root = new Trie();
        for(int i = 0; i < sensitiveWords.length; i++){
            // 将所有敏感词词汇插入并记录
            root.insert(sensitiveWords[i]);
        }

        int i = 0;
        while(i < username.length()){
            int j = i;
            // 若username子串前缀不为敏感词前缀，一定不是敏感词，若子串前缀为敏感词前缀，则继续检测是否为敏感词，若是，则返回false和信息，否则继续检测，直至跳出循环。
            while(j < username.length() && root.startsWith(username.substring(i, j + 1))){
                if(root.search(username.substring(i, j + 1))){
                    return new RegisterResult(false, "用户名含有敏感词汇！");
                }
                j++;
            }
            i++;
        }
        return new RegisterResult(true);
    }
    /**
     * 判断用密码是否为空，长度是否在6~16之间，以及是否包含两种以上字符，且只包含数字及英文大小写
     * @param password
     * @return RegisterResult
     */
    public static RegisterResult checkPassword(String password){
        // 判断密码是否为null, 防止空指针异常
        if(null == password || password.length() == 0) return new RegisterResult(false, "密码不允许为空！");

        // 判断密码长度是否在6~16之间
        if(password.length() < 6 || password.length() > 16) return new RegisterResult(false, "密码长度必须为6~16位！");

        // 判断是否包含两种以上字符，且只包含数字及英文大小写
        int typenum = 0;
        boolean capital = false;
        boolean small = false;
        boolean digital = false;
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if(c >= 'a' && c <= 'z'){
                small = true;
            }else if(c >= 'A' && c <= 'Z'){
                capital = true;
            }else if(c >= '0' && c <= '9'){
                digital = true;
            }else{
                return new RegisterResult(false, "密码只能为大写字母，小写字母或数字！");
            }
        }
        if(capital) ++typenum;
        if(small) ++typenum;
        if(digital) ++ typenum;
        if(typenum < 2) return new RegisterResult(false, "密码必须由至少两种字符组成！");


        return new RegisterResult(true);
    }


    /**
     * 判断输入的邮箱格式是否正确
     * @param email 输入的邮箱地址
     * @return RegisterResult
     */
    public static RegisterResult checkMail(String email) {
        // 判断email是否为null, 防止空指针异常
        if(null == email || email.length() == 0) return new RegisterResult(false, "邮箱不允许为空！");

        boolean flag = false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        if(m.matches())
            return new RegisterResult(true);
        else
            return new RegisterResult(false, "邮箱格式有误!");
    }

}
