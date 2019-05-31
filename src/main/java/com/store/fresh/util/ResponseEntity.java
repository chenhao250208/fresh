package com.store.fresh.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntity extends HashMap<String,Object> {
    private static final long serialVersionUID =1L;

    private static final String CODE = "code";

    private static final String MSG = "msg";

    public static ResponseEntity error(int code,String msg){

        ResponseEntity r =  new ResponseEntity();
        r.put(CODE,code);
        r.put(MSG,msg);
        return r;
    }


    public static ResponseEntity error(){
        ResponseEntity r = new ResponseEntity();
        r.put(ResultEnum.UNKNOWN_ERROR);
        return r;
    }

    public static ResponseEntity ok(Map<String,Object> map){
        ResponseEntity r = new ResponseEntity();
        r.putAll(map);

        return r;
    }

    public static ResponseEntity ok(){
        ResponseEntity r = new ResponseEntity();
        r.put(ResultEnum.SUCCESS);
        return r;
    }

    public static ResponseEntity ok(String msg){
        ResponseEntity r = new ResponseEntity();
        r.put(CODE,200);
        r.put(MSG,msg);
        return r;
    }

    public ResponseEntity put(String key,Object value){

        super.put(key,value);
        return  this;
    }

    public ResponseEntity put(ResultEnum e){
        this.put(CODE,e.getCode());
        this.put(MSG,e.getMsg());
        return this;
    }

}
