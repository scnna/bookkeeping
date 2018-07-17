package com.bookkeep.controller.BusinessController;

import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.ProdCome;
import com.bookkeep.mapper.ProdComeMapper;
import com.bookkeep.service.ProdComeService;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品进厂信息Controller
 **/
@RestController
@RequestMapping("prodCome")
public class ProdComeController extends BaseController {

    @Autowired
    ProdComeMapper comeMapper;
    @Autowired
    ProdComeService comeService;

    @PostMapping("/insert")
    public ReturnResult insert(ProdCome prodCome, HttpSession session) {
        try {
            return comeService.insert(prodCome, session);
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    @GetMapping("getList")
    public ReturnResult getList(HttpSession session) {
        return comeService.getList(session);
    }

    @GetMapping("delete")
    public ReturnResult delete(int id) {
        try {
            return comeService.delete(id);
        } catch (Exception e) {
            return ReturnResult.error("删除失败" + e);
        }

    }

    @GetMapping("/pagination")
    public ReturnResult getMemberPage() {
        return comeService.getMemberPage();
    }
}
