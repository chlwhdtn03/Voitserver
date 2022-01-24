package com.chlwhdtn.voit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/debate")
public class DebateREST {

	List<String> debates = new ArrayList<>(); // Debate ID
	
	FileManager fm = new FileManager();
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createDebate(@RequestParam("title") String title, HttpServletRequest req) {
		System.out.println(req.getRemoteAddr() + " -> createDebate(" + title + ")");
		Debate debate = new Debate();
		debate.setId(generateRandomID()); 
		debate.setTitle(title);
		
		debates.add(title);
		
		fm.saveDebate(debate);
		
		return new ResponseMessage(true, "성공적으로 등록되었습니다.", debate).toString();
	}

	private String generateRandomID() {
		String str;
		while(true) {
			String prefix = "";
			switch(new Random().nextInt(3)) {
			case 0: prefix = "가"; break;
			case 1: prefix = "나"; break;
			case 2: prefix = "다"; break;
				
			}
			str = prefix + (int)(new Random().nextDouble() * 10000);
			if(!fm.hasDebate(str))
				break;
		}
		return str;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getDebate(@RequestParam("id") String id, HttpServletRequest req) {
		System.out.println(req.getRemoteAddr() + " -> getDebate(" + id + ")");
		
		Debate result = fm.loadDebate(id);
		
		if(result == null)
			return new ResponseMessage(false, "존재하지 않는 토론입니다").toString();
		else
			return new ResponseMessage(true, "성공적으로 불러왔습니다.", result).toString();
		
	}
	
	@RequestMapping(value = "/gets", method = RequestMethod.GET)
	public String getDebates(@RequestParam("page") int page, HttpServletRequest req) {
		System.out.println(req.getRemoteAddr() + " -> getDebates(" + page + ")");
		
		Debate[] result = fm.loadDebates(page);
		
		if(result == null)
			return new ResponseMessage(false, "토론이 없습니다.").toString();
		else
			return new ResponseMessage(true, "성공적으로 불러왔습니다.", result).toString();
		
	}


}
