package com.example.authority.common;

import javax.security.sasl.AuthenticationException;

/**
 * @program: hhrm
 * @ClassName CaptchaException
 * @description:
 * @author: glb
 * @create: 2023-02-01 16:10
 * @Version 2.0
 **/
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
