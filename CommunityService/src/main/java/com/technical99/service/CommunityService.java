package com.technical99.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.technical99.dto.AddQues;
import com.technical99.dto.AnsComment;
import com.technical99.dto.AnsVoteReq;
import com.technical99.dto.PostAnswer;
import com.technical99.dto.QuesComment;
import com.technical99.dto.QuesVoteReq;
import com.technical99.entity.AddAnswerComments;
import com.technical99.entity.AddQuesComment;
import com.technical99.entity.Answers;
import com.technical99.entity.Category;
import com.technical99.entity.Language;
import com.technical99.entity.QuestionDetails;
import com.technical99.entity.Questions;
import com.technical99.entity.Tags;
import com.technical99.entity.TrendingIQ;
import com.technical99.entity.TrendingQuestions;
import com.technical99.entity.Users;
import com.technical99.exception.UserActivationException;
import com.technical99.exception.UserRequestParameterException;

public interface CommunityService 
{
	public List<Category> getCategoryList();
	
	public List<Language> getLanguageList();
	
	public Page<Questions> getQuestionList(Integer id, Integer flag, Integer order, Pageable pageable) throws UserRequestParameterException;
	
	public Page<Questions> getQuestionListByTag(Integer tagId, Pageable pageable);
	
	public QuestionDetails getQuestionAndAnswers(Integer quesId);
	
	public Page<Answers> getAllAnswers(Integer quesId, Integer order, Pageable pageable);
	
	public Integer addNewQuestion(String user, AddQues ques);
	
	public Integer postAnswer(String user, PostAnswer ques);
	
	public AddQuesComment addQuesComment(String user, QuesComment comment);
	
	public AddAnswerComments addAnsComment(String user, AnsComment comment);
	
	public Page<TrendingQuestions> getTrendingQuestions(Pageable pageable);
	
	public String joinCommunity(String userName) throws UserActivationException;
	
	public Page<Users> getAllCommunityUsers(Integer order, String user, Pageable pageable)  throws UserRequestParameterException;
	
	public Page<Users> getUserByName(String name, Integer order, Pageable pageable);
	
	public Page<Tags> getAllTags(Integer order, Pageable pageable) throws UserRequestParameterException;
	
	public Page<Tags> getTagByName(String name, Integer order, Pageable pageable);
	
	public Page<TrendingIQ> getTrendingInterviewQuestions(Pageable pageable);
	
	public String submitVote(String user, QuesVoteReq vote); 
	
	public String submitAnswerVote(String user, AnsVoteReq vote);
}
