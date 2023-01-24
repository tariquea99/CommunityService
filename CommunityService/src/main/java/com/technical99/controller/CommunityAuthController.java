package com.technical99.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technical99.dto.AddQues;
import com.technical99.dto.AnsComment;
import com.technical99.dto.AnsVoteReq;
import com.technical99.dto.PostAnswer;
import com.technical99.dto.QuesComment;
import com.technical99.dto.QuesVoteReq;
import com.technical99.entity.AddAnswerComments;
import com.technical99.entity.AddQuesComment;
import com.technical99.exception.UserException;
import com.technical99.response.ResponseHandler;
import com.technical99.service.CommunityService;



@RestController
@RequestMapping("/auth-community")
public class CommunityAuthController
{
	@Autowired
    private CommunityService communityService;
	
	@PostMapping("/add-question")
	public ResponseEntity<?> addQuestionAndAnswers(HttpServletRequest req, @RequestBody AddQues ques)
	{
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			Integer quesId = communityService.addNewQuestion(user, ques);
			if(quesId != null)
				return ResponseHandler.generateResp(quesId, "Question successfully submitted.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Question submission failed!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@PostMapping("/post-answer")
	public ResponseEntity<?> postAnswers(HttpServletRequest req, @RequestBody PostAnswer ans)
	{
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			Integer ansId = communityService.postAnswer(user, ans);
			if(ansId != null)
				return ResponseHandler.generateResp(ansId, "Answer successfully submitted.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Answer submission failed!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@PostMapping("/add-ques-comment")
	public ResponseEntity<?> addQuesComment(HttpServletRequest req, @RequestBody QuesComment comment)
	{
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			AddQuesComment res = communityService.addQuesComment(user, comment);
			if(res != null)
				return ResponseHandler.generateResp(res, "Question's comment successfully submitted.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Question's comment submission failed!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@PostMapping("/add-ans-comment")
	public ResponseEntity<?> addAnsComment(HttpServletRequest req, @RequestBody AnsComment comment)
	{
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			AddAnswerComments res = communityService.addAnsComment(user, comment);
			if(res != null)
				return ResponseHandler.generateResp(res, "Question's comment successfully submitted.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Question's comment submission failed!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/join-community")
	public ResponseEntity<?> joinCommunity(HttpServletRequest req)
	{
		try {
			String res = communityService.joinCommunity(req.getHeader("userName")); 
			if(res != null)
				return ResponseHandler.generateMsgResp(res, HttpStatus.OK);
			else
				return ResponseHandler.generateMsgResp(res, HttpStatus.FORBIDDEN);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@PostMapping("/votes")
	public ResponseEntity<?> submitVote(HttpServletRequest req, @RequestBody QuesVoteReq vote)
	{
		String res = null;
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			res = communityService.submitVote(user, vote); 
			if(res != null)
				return ResponseHandler.generateMsgResp(res, HttpStatus.OK);
			else
				return ResponseHandler.generateMsgResp(res, HttpStatus.FORBIDDEN);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@PostMapping("/ans-votes")
	public ResponseEntity<?> submitAnswerVote(HttpServletRequest req, @RequestBody AnsVoteReq vote)
	{
		String res = null;
		try {
			String user = req.getHeader("userName");
			if(user == null) {
				throw new UserException("Session Expired...");
			}
			res = communityService.submitAnswerVote(user, vote); 
			if(res != null)
				return ResponseHandler.generateMsgResp(res, HttpStatus.OK);
			else
				return ResponseHandler.generateMsgResp(res, HttpStatus.FORBIDDEN);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
}
