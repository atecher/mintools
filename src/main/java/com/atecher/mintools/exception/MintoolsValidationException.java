package com.atecher.mintools.exception;

import org.dom4j.DocumentException;

/**
 * Created by hanhongwei on 2016/7/5.
 */
public class MintoolsValidationException extends DocumentException {

    public MintoolsValidationException(String message){
        super(message);
    }
}
