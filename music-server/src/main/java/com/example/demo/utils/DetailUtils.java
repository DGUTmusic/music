package com.example.demo.utils;

import com.example.demo.domain.Detail;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DetailUtils {

    public static Detail addList(List list) {
        Detail detail = new Detail();
        if (list.size() != 0) {
            detail.setState(200);
            detail.setMessage("OK");
            detail.getMap().put("list", list);
        } else {
            detail.setState(400);
            detail.setMessage("查询不到相关的数据！");
        }
        return detail;
    }

    public static Detail addObject(Object object) {
        Detail detail = new Detail();
        if (object!=null) {
            detail.setState(200);
            detail.setMessage("OK");
            detail.getMap().put("object", object);
        } else {
            detail.setState(400);
            detail.setMessage("查询不到相关的数据！");
        }
        return detail;
    }
//    public static Detail addPage(Page page) {
//        Detail detail = new Detail();
//        HashMap hashMap=new HashMap();
//        hashMap.put("total",page.getTotal());
//        hashMap.put("startRow",page.getStartRow());
//        hashMap.put("endRow",page.getEndRow());
//        hashMap.put("pageSize",page.getPageSize());
//        hashMap.put("pageNum",page.getPageNum());
//        hashMap.put("pages",page.getPages());
//        if (page.size() != 0) {
//            detail.setState(200);
//            detail.setMessage("OK");
//            detail.getMap().put("list", page);
//            detail.getMap().put("page",hashMap);
//        } else {
//            detail.setState(400);
//            detail.setMessage("查询不到相关的数据！");
//        }
//        return detail;
//    }
}
