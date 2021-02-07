package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class customerException {

    public static CommonResult globalException1(BlockException exception) {
        return new CommonResult(4444,"全局处理异常被调用,metyhod is globalException--------1");
    }

    public static CommonResult globalException2(BlockException exception) {
        return new CommonResult(4444,"全局处理异常被调用,metyhod is globalException--------2");
    }
}
