package com.bookkeep.controller.BusinessController;

import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.ProdOut;
import com.bookkeep.mapper.MemberProdMapper;
import com.bookkeep.mapper.ProdMapper;
import com.bookkeep.service.ProdOutService;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品交货信息Controller
 **/
@RestController
@RequestMapping("prodOut")
public class ProdOutController extends BaseController {

    @Autowired
    MemberProdMapper memberProdMapper;
    @Autowired
    ProdMapper prodMapper;
    @Autowired
    ProdOutService outService;

    @PostMapping("/insert")
    public ReturnResult insert(ProdOut prodOut,HttpSession session) {
        try {
            return outService.insert(prodOut, session);
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    @GetMapping("getList")
    public ReturnResult getList(HttpSession session) {
        return outService.getList(session);
    }

    public String delete(int id) {
        try {
            outService.delete(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    @GetMapping("/pagination")
    public ReturnResult getMemberPage(String prodId) {
        return outService.getProdOutPage(prodId);
    }
}
