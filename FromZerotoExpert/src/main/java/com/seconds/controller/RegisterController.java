package com.seconds.controller;

import com.seconds.dao.UserDao;
import com.seconds.entity.DisallowWord;
import com.seconds.entity.User;
import com.seconds.service.DisallowWordService;
import com.seconds.service.Register;
import com.seconds.utils.RegisterResult;
import com.seconds.utils.RegisterUtils;
import com.seconds.utils.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/FromZerotoExpert")
public class RegisterController {
    @Autowired
    private Register register;

    @Autowired
    private DisallowWordService disallowWordService;

    private Trie words;

    @PostConstruct
    public void init(){
        Trie root = new Trie();
        List<DisallowWord> disallowWords = disallowWordService.selectAll();
        for(int i = 0; i < disallowWords.size(); i++){
            root.insert(disallowWords.get(i).getValue());
        }
        words = root;
    }


    @RequestMapping("/register")
    public String register(){
        return "register.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }


    @PostMapping("/checkUsername")
    @ResponseBody
    public RegisterResult checkUsername(String username){
        // 检测用户名长度是否符合
        RegisterResult registerResult = RegisterUtils.checkUsername(username);
        if(!registerResult.getQualified()) return registerResult;

        // 检测用户名是否含有敏感词
        registerResult = RegisterUtils.checkDisalowWord(username, words);
        if(!registerResult.getQualified()) return registerResult;

        // 检测用户名是否重复
        User user = register.selectByUsername(username);
        if(null != user) return new RegisterResult(false, "该用户名已存在！");
        return registerResult;
    }

    @PostMapping("/checkPassword")
    @ResponseBody
    public RegisterResult checkPassword(String password){
        return RegisterUtils.checkPassword(password);
    }

    @PostMapping("/checkEmail")
    @ResponseBody
    public RegisterResult checkEmail(String email){
        return RegisterUtils.checkMail(email);
    }

    @PostMapping("/checkAll")
    @ResponseBody
    public RegisterResult checkAll(User user){
//        System.out.println("user = " + user);
        RegisterResult registerResult = RegisterUtils.checkUsername(user.getUsername());
        if(!registerResult.getQualified()) return registerResult;

        registerResult = RegisterUtils.checkDisalowWord(user.getUsername(), words);
        if(!registerResult.getQualified()) return registerResult;

        registerResult = RegisterUtils.checkPassword(user.getPassword());
        if(!registerResult.getQualified()) return registerResult;

        registerResult = RegisterUtils.checkMail(user.getEmail());
        if(!registerResult.getQualified()) return registerResult;

        User user2 = register.selectByUsername(user.getUsername());
        if(null != user2) return new RegisterResult(false, "该用户名已存在！");

        return new RegisterResult(true);
    }

    @PostMapping("/save")
    public String save(User user){
        user.setSalt(user.getUsername());
        register.regist(user);
        return "register_success.html";
    }


}
