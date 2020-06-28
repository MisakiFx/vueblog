package com.misaki.util;

import com.misaki.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
public class ShiroUtils {
    public static AccountProfile getProfile(){
        return (com.misaki.shiro.AccountProfile)SecurityUtils.getSubject().getPrincipal();
    }
}

