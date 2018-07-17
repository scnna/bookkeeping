package com.bookkeep.controller.BusinessController;

import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.MemberProd;
import com.bookkeep.mapper.MemberProdMapper;
import com.bookkeep.mapper.ProdMapper;
import com.bookkeep.service.WordRecordService;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品进厂信息Controller
 **/
@RestController
@RequestMapping("workRecord")
public class WordRecordController extends BaseController {

    @Autowired
    MemberProdMapper memberProdMapper;
    @Autowired
    ProdMapper prodMapper;
    @Autowired
    WordRecordService wordRecordService;

    @PostMapping("/insert")
    public ReturnResult insert(MemberProd memberProd, @RequestParam(name = "memberList") String memberList, HttpSession session) {
        try {
            return wordRecordService.insert(memberProd, memberList, session);
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    @GetMapping("getList")
    public ReturnResult getList(HttpSession session) {
        return wordRecordService.getList(session);
    }

    public String delete(int id) {
        try {
            wordRecordService.delete(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    @GetMapping("/pagination")
    public ReturnResult getMemberPage(String prodId) {
        return wordRecordService.getMemberPage(prodId);
    }
}
