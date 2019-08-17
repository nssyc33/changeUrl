package exSubUrl.io.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import exSubUrl.io.Service.MakeShortUrlName;

@Controller
public class MakeShortUrlController {
	
	@Autowired
	MakeShortUrlName msun;
	
	@Autowired
    ServletContext context;
	
	/**
	 * URL 조회 및 입력 핸들러
	 * @param md
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exSubUrl/view.do")
	public String view(Model md, HttpServletRequest req) throws Exception{
		ArrayList list = (ArrayList) context.getAttribute("urlData");
		md.addAttribute("list", list);
		md.addAttribute("dupYn", req.getParameter("dupYn"));
		return "insertlist";
	}
	
	/**
	 * URL 입력 핸드러
	 * @param hm
	 * @param req
	 * @param ra
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exSubUrl/insertData.do")
	public String insertData(@RequestParam HashMap hm, HttpServletRequest req, RedirectAttributes ra) throws Exception{
		
		ArrayList list = (ArrayList) context.getAttribute("urlData");
		if(list == null){
			list = new ArrayList();
		}
		if(!msun.duplicateExistsYn(hm, list)){
			HashMap hma = new HashMap();
			String nowTime = new SimpleDateFormat("ss").format(System.currentTimeMillis());
			int nowSec = Integer.parseInt(nowTime);
			String asKey = msun.makeShortUrl(list, nowSec);
			hma.put("oriUrl", hm.get("oriUrl"));
			hma.put("subUrl", "http://localhost:"+req.getServerPort()+"/"+asKey);
			hma.put("subKey", asKey);
			list.add(hma);
			ra.addAttribute("dupYn", "N");
		}else{
			ra.addAttribute("dupYn", "Y");
		}
		context.setAttribute("urlData", list);
		return "redirect:/exSubUrl/view.do";
	}	
	
	/**
	 * 대체된 URL 매핑 핸들러
	 * @param subName
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{subName}")
	public String subName(@PathVariable String subName, HttpServletRequest req) throws Exception{
		if(context.getAttribute("urlData") == null){
			return "";
		}else{
			ArrayList list = (ArrayList) context.getAttribute("urlData");
			String asOriUrl = msun.getOriUrl(subName, list);
			return "redirect:http://"+asOriUrl;
		}
	}	
}
