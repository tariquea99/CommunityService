package com.technical99.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technical99.entity.Answers;
import com.technical99.entity.Category;
import com.technical99.entity.Language;
import com.technical99.entity.QuestionDetails;
import com.technical99.entity.Questions;
import com.technical99.entity.Tags;
import com.technical99.entity.TrendingIQ;
import com.technical99.entity.TrendingQuestions;
import com.technical99.entity.Users;
import com.technical99.response.ResponseHandler;
import com.technical99.service.CommunityService;



@RestController
@RequestMapping("/community")
public class CommunityController
{
	@Autowired
    private CommunityService communityService;
	
	@GetMapping("/categories")
	public ResponseEntity<?> getCategoryList()
	{
		try {
			List<Category> categories = communityService.getCategoryList();
			if(categories != null)
				return ResponseHandler.generateResp(categories, "Categories Found.", HttpStatus.OK, categories.size());
			else
				return ResponseHandler.generateResp(null, "Categories Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/languages")
	public ResponseEntity<?> getLanguageList()
	{
		try {
			List<Language> categories = communityService.getLanguageList();
			if(categories != null)
				return ResponseHandler.generateResp(categories, "Languages Found.", HttpStatus.OK, categories.size());
			else
				return ResponseHandler.generateResp(null, "Languages Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/ques-list")
	public ResponseEntity<?> getQuestionList(@RequestParam("order") Integer order, @RequestParam("flag") Integer flag, 
											 @RequestParam("id") Integer id, Pageable pageable)
	{
		try {
			Page<Questions> ques = communityService.getQuestionList(id, flag, order, pageable);
			if(ques != null)
				return ResponseHandler.generateResp(ques, "Questions Found.", HttpStatus.OK, ques.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "Questions Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/ques-list-by-tag")
	public ResponseEntity<?> getQuestionListByTag(@RequestParam("tagId") Integer tagId, Pageable pageable)
	{
		try {
			Page<Questions> ques = communityService.getQuestionListByTag(tagId, pageable);
			if(ques != null)
				return ResponseHandler.generateResp(ques, "Questions ByTag Found.", HttpStatus.OK, ques.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "Questions ByTag Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/ques-details/{quesId}")
	public ResponseEntity<?> getQuestionAndAnswers(@PathVariable Integer quesId)
	{
		try {
			QuestionDetails ques = communityService.getQuestionAndAnswers(quesId);
			if(ques != null)
				return ResponseHandler.generateResp(ques, "Question Details Found.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Question Details Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/all-answers")
	public ResponseEntity<?> getAllAnswers(@RequestParam("quesId") Integer quesId, @RequestParam("order") Integer order, Pageable pageable)
	{
		try {
			Page<Answers> answers = communityService.getAllAnswers(quesId, order, pageable);
			if(answers != null)
				return ResponseHandler.generateResp(answers, "Answers Found.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Answers Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/trending-questions")
	public ResponseEntity<?> getTrendingQuestions(Pageable pageable)
	{
		try {
			Page<TrendingQuestions> ques = communityService.getTrendingQuestions(pageable);
			if(ques != null)
				return ResponseHandler.generateResp(ques, "Trending Questions Found.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Trending Questions Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/trending-iq")
	public ResponseEntity<?> getTrendingInterviewQuestions(Pageable pageable)
	{
		try {
			Page<TrendingIQ> ques = communityService.getTrendingInterviewQuestions(pageable);
			if(ques != null)
				return ResponseHandler.generateResp(ques, "Trending Questions Found.", HttpStatus.OK, 1);
			else
				return ResponseHandler.generateResp(null, "Trending Questions Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllCommunityUsers(@RequestParam("order") Integer order, @RequestParam("user") String user, Pageable pageable)
	{
		try {
			Page<Users> users = communityService.getAllCommunityUsers(order, user, pageable);
			if(users != null)
				return ResponseHandler.generateResp(users, "Community Users Found.", HttpStatus.OK, users.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "Community Users Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	/*
	 * User Filter By Name
	 */
	@GetMapping("/users/filter")
	public ResponseEntity<?> getUserByName(@RequestParam("name") String name, @RequestParam("order") Integer order, Pageable pageable)
	{
		try {
			Page<Users> users = communityService.getUserByName(name, order, pageable);
			if(users != null)
				return ResponseHandler.generateResp(users, "Filtered Users.", HttpStatus.OK, users.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "No Users Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/tags")
	public ResponseEntity<?> getAllTags(@RequestParam("order") Integer order, Pageable pageable)
	{
		try {
			Page<Tags> tags = communityService.getAllTags(order, pageable);
			if(tags != null)
				return ResponseHandler.generateResp(tags, "Tags Found.", HttpStatus.OK, tags.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "Tags Not Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
	
	@GetMapping("/tags/filter")
	public ResponseEntity<?> getTagByName(@RequestParam("name") String name, @RequestParam("order") Integer order, Pageable pageable)
	{
		try {
			Page<Tags> tags = communityService.getTagByName(name, order, pageable);
			if(tags != null)
				return ResponseHandler.generateResp(tags, "Tags Found.", HttpStatus.OK, tags.getNumberOfElements());
			else
				return ResponseHandler.generateResp(null, "No Tags Found!", HttpStatus.FORBIDDEN, 0);
		}
		catch(Exception e) {
			return ResponseHandler.exceptionResp(e.getClass(), e.getMessage(), HttpStatus.MULTI_STATUS);
        }
	}
}
