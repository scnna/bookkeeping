package com.bookkeep.controller.baseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 登录Controller
 **/
@RestController
@RequestMapping("code")
public class CodeController {

//    @Autowired
//    SysCodeMapper sysCodeMapper;

//    @RequestMapping("/getMemberType")
//    public ReturnResult getMemberType(HttpSession session) {
//        SysCode code=new SysCode();
//        code.setCodeType("member_type");
//        return ReturnResult.success(sysCodeMapper.selectByCode(code));
//    }
}
