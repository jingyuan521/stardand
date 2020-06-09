package com.pro.standard.controller;

import com.pro.standard.dao.StardandMapper;
import com.pro.standard.pojo.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.List;

@Controller
public class StardandController {
    @Autowired
    private StardandMapper stardandMapper;
    @RequestMapping("/list")
    public ModelAndView getList(ModelAndView mv){
        List<Standard> standardList = stardandMapper.findAll();
        mv.addObject("standardList",standardList);
        mv.setViewName("show");
        return  mv;
    }



    //跳转增加页面
    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    //增加

    @RequestMapping(value="/doAdd",method= RequestMethod.GET,produces={"application/json;charset=utf-8"})
    public int doAddSandard(@RequestParam String std_num,
                            @RequestParam String zhname,
                            @RequestParam String version,
                            @RequestParam String keys,
                            @RequestParam String release_date,
                            @RequestParam String impl_date,
                            @RequestParam(value = "package_path",required = false) String package_path){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Standard standard = new Standard();
        standard.setStd_num(std_num);
        standard.setZhname(zhname);
        standard.setSversion(version);
        standard.setSkeys(keys);
        try {
            standard.setRelease_date(dateFormat.parse(release_date));
            standard.setImpl_date(dateFormat.parse(impl_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int num = stardandMapper.addStard(standard);
        return num;

    }

    //下载
    @RequestMapping("/down")
    public String down(HttpServletResponse response, HttpSession session){
        String pic = "stardand.txt";
        //设置文件下载的格式，以附件形式打开
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;fileName="+pic);

        //定义流
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        //服务器地址
        String path = session.getServletContext().getRealPath("/files/");
        //创建名字为straddand.txt的文件
        File file = new File(path+File.separator+pic);
        //读取成字节流
        if (file.exists()) {
            try {
                //读取成字节流
                fileInputStream = new FileInputStream(file);
                //写入到缓冲流
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                //创建一个byte[]类型的中转站
                byte[] bytes = new byte[fileInputStream.available()];
                //调用读取方法
                bufferedInputStream.read(bytes);
                ////将服务器端响应的字节流写入到本地文件中
                outputStream=response.getOutputStream();
                //写入
                outputStream.write(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    outputStream.close();
                    bufferedInputStream.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            session.setAttribute("msg","文件不存在");
        }

        return "";

    }

    //删除
    @RequestMapping("doDel")
    public String delStardand(@RequestParam String sid){
        System.out.println(sid);
        long std_id = Integer.parseInt(sid);
        stardandMapper.deleteById(std_id);
        return  "redirect:/list";
    }


}
