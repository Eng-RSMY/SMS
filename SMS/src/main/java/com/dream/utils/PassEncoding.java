/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dream.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The PassEncoding class
 *
 * @author Dileep
 * @version 1.0
 * Date 20/05/2019.
 */
public class PassEncoding {

    private static PassEncoding passEncoding = new PassEncoding();
    public BCryptPasswordEncoder passwordEncoder;

    public static PassEncoding getInstance() {
        if (passEncoding != null)
            return passEncoding;
        return new PassEncoding();
    }

    private PassEncoding() {
        passwordEncoder = new BCryptPasswordEncoder();
    }


}
