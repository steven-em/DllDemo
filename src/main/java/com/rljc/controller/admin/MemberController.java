package com.rljc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rljc.module.Member;
import com.rljc.service.MemberService;

@Controller
@RequestMapping(value="/admin/member")
public class MemberController extends BaseController<Member> {

	@Autowired
	private MemberService memberService;
	
	@Override
	protected void initService() {
		this.baseService = memberService;
	}
	
	@RequestMapping("/index")
	public String index(Member member){
		request.setAttribute("member", member);
		return "admin/memberList";
	}
}
