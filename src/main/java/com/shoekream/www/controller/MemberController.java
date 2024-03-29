package com.shoekream.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoekream.www.domain.memberVO.HistoryDTO;
import com.shoekream.www.domain.memberVO.MemberVO;
import com.shoekream.www.service.memberService.MemberService;
import com.shoekream.www.service.mypageService.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService msv;
	
	@Autowired
	private MypageService mpsv;
	
	@GetMapping("/buyHistoryDetail")
	public void buyHistoryDetail()	{
		log.info(">>> MemberController > buyHistoryDetail - GET");
	}
	
	@GetMapping("/mypage")
	public void mypage(@RequestParam("email") String email, Model model) {
		log.info(">>> MemberController > MyPage - GET");
		
		model.addAttribute("memberVO", msv.getDetail(email));
		model.addAttribute("buyDTO", mpsv.selectItemBuyHistory(email));
		model.addAttribute("selDTO", mpsv.selectItemSelHistory(email));
	}
	
	@GetMapping("/buying")
	public void buying(@RequestParam("email") String email, Model model) {
		log.info(">>> MemberController > Buying - GET");
		model.addAttribute("buyDTO", mpsv.selectItemBuyHistory(email));
	}
	
	@GetMapping("/selling")
	public void selling(@RequestParam("email") String email, Model model) {
		log.info(">>> MemberController > Selling - GET");
		model.addAttribute("selDTO", mpsv.selectItemSelHistory(email));
	}
	
	@GetMapping("/historydetail")
	public void historydetail(@RequestParam("itemNo") int itemNo, Model model) {
		log.info(">>> MemberController > HistoryDetail - POST");
		model.addAttribute("mypageVO", mpsv.selectHistoryDetail(itemNo));
	}

	@GetMapping("/register")
	public void register() {
		log.info(">>> MemberController > Register - GET");
	}

	@PostMapping(value = "/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody MemberVO memberVO) {
		return msv.register(memberVO) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/list")
	public void list(Model model) {
		log.info(">>> MemberController > List - GET");
		List<MemberVO> list = msv.getList();
		model.addAttribute("list", list);
	}

	@GetMapping("/detail")
	public void detail(Model model, @RequestParam("email") String email) {
		log.info(">>> MemberController > Detail - GET");
		MemberVO memberVO = msv.getDetail(email);
		model.addAttribute("memberVO", memberVO);
	}

	@GetMapping("/modify")
	public void modify(Model model, @RequestParam("email") String email) {
		log.info(">>> MemberController > Modify - GET");
		MemberVO memberVO = msv.getDetail(email);
		model.addAttribute("memberVO", memberVO);
	}

	@PostMapping("/modify")
	public String modify(MemberVO memberVO) {
		int isUp = msv.modify(memberVO);
		log.info(">>> MemberController > Modify - POST : {}", isUp > 0 ? "OK" : "FAIL");
		return "redirect:/member/detail?email=" + memberVO.getEmail();
	}

	@PostMapping("/remove")
	public String remove(HttpSession ses, @RequestParam("email") String email) {
		int isUp = msv.remove(email);
		log.info(">>> MemberController > Remove - POST : {}", isUp > 0 ? "OK" : "FAIL");
		ses.removeAttribute("ses");
		ses.invalidate();
		return "redirect:/";
	}

	@GetMapping("/login")
	public void login() {
		log.info(">>> MemberController > Login - GET");
	}

	@PostMapping(value = "/login", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> login(@RequestBody MemberVO memberVO, HttpSession ses) {
		if (ses.getAttribute("ses") != null) {
			ses.removeAttribute("ses");
		}

		MemberVO loginSession = msv.login((memberVO)); // 정희도가 변경 추가 수정 함
		if (loginSession != null) {
			ses.setAttribute("ses", loginSession);
			return new ResponseEntity<String>("1", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("0", HttpStatus.OK);
	}

	@GetMapping("/logout")
	public String logout(HttpSession ses, RedirectAttributes rttr) {
		ses.removeAttribute("ses");
		ses.invalidate();
		rttr.addFlashAttribute("isLogout", 1);
		return "redirect:/";
	}

	@ResponseBody
	@PostMapping(value = "/dupleCheck", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String dupleCheck(@RequestBody HashMap<String, String> map) {
		int isExist = msv.dupleCheck(map.get("email"));
		return isExist > 0 ? "1" : "0";
	}
}