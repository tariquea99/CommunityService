package com.technical99.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.technical99.dto.AddQues;
import com.technical99.dto.AnsComment;
import com.technical99.dto.AnsVoteReq;
import com.technical99.dto.PostAnswer;
import com.technical99.dto.QuesComment;
import com.technical99.dto.QuesVoteReq;
import com.technical99.entity.AddAnswerComments;
import com.technical99.entity.AddQuesComment;
import com.technical99.entity.AddQuesTags;
import com.technical99.entity.AddQuestion;
import com.technical99.entity.AnsVote;
import com.technical99.entity.Answers;
import com.technical99.entity.Category;
import com.technical99.entity.JoinCommunity;
import com.technical99.entity.Language;
import com.technical99.entity.PostNewAnswer;
import com.technical99.entity.QuesVote;
import com.technical99.entity.QuestionDetails;
import com.technical99.entity.Questions;
import com.technical99.entity.Tags;
import com.technical99.entity.TrendingIQ;
import com.technical99.entity.TrendingQuestions;
import com.technical99.entity.Users;
import com.technical99.enums.Community;
import com.technical99.enums.IQStatus;
import com.technical99.enums.QuestionStatus;
import com.technical99.exception.UserActivationException;
import com.technical99.exception.UserRequestParameterException;
import com.technical99.repository.AddAnsCommentRepository;
import com.technical99.repository.AddQuesCommentRepository;
import com.technical99.repository.AddQuesTagsRepository;
import com.technical99.repository.AddQuestionRepository;
import com.technical99.repository.AnsVoteRepository;
import com.technical99.repository.AnswersRepository;
import com.technical99.repository.CategoryRepository;
import com.technical99.repository.CommunityUsersRepository;
import com.technical99.repository.JoinCommunityRepository;
import com.technical99.repository.LanguageRepository;
import com.technical99.repository.PostAnswerRepository;
import com.technical99.repository.QuesAnswersRepository;
import com.technical99.repository.QuesVoteRepository;
import com.technical99.repository.QuestionsRepository;
import com.technical99.repository.TagsRepository;
import com.technical99.repository.TrendingIQRepository;
import com.technical99.repository.TrendingQuesRepository;
import com.technical99.repository.UserLoginRepository;
import com.technical99.repository.UserRegRepository;
import com.technical99.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService
{
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
    private LanguageRepository languageRepository;
	@Autowired
    private QuestionsRepository questionsRepository;
	@Autowired
    private AnswersRepository answersRepository;
	@Autowired
    private QuesAnswersRepository quesAnswersRepository;
	@Autowired
    private AddQuestionRepository addQuestionRepository;
	@Autowired
    private  PostAnswerRepository postAnswerRepository;
	@Autowired
    private AddQuesCommentRepository addQuesCommentRepository;
	@Autowired
    private AddAnsCommentRepository addAnsCommentRepository;
	@Autowired
    private AddQuesTagsRepository addQuesTagsRepository;
	@Autowired
    private JoinCommunityRepository joinCommunityRepository;
	@Autowired
    private CommunityUsersRepository communityUsersRepository;
	@Autowired
    private TrendingQuesRepository trendingQuesRepository;
	@Autowired
    private TagsRepository tagsRepository;
	@Autowired
	private	UserLoginRepository userLoginRepository;
	@Autowired
	private TrendingIQRepository trendingIQRepository;
	@Autowired
	private UserRegRepository userRegRepository;
	@Autowired
	private QuesVoteRepository quesVoteRepository;
	@Autowired
	private AnsVoteRepository ansVoteRepository;
	
	@Override
	public List<Category> getCategoryList() 
	{
		return categoryRepository.findByStatus(Community.ON);
	}

	@Override
	public List<Language> getLanguageList() 
	{
		return languageRepository.findDistinctByQuestionIsNotNull();
	}
	
	@Override
	public Page<Questions> getQuestionList(Integer id, Integer flag, Integer order, Pageable pageable) throws UserRequestParameterException
	{
		Page<Questions> ques = null;
		LocalDateTime endDate = LocalDateTime.now();
		LocalDateTime startDate = LocalDateTime.now().minusDays(30);
		if(id < 0 || id == null || flag < 0|| flag == null)
			throw new UserRequestParameterException("User request parameter invalid!");
		if(order == 1) {
			if(flag == 3)
				ques = questionsRepository.findByStatusOrderByPublishDateDesc(QuestionStatus.Published, pageable);
			else if(flag == 0)
				ques = questionsRepository.findByCategoryIdAndStatusOrderByPublishDateDesc(id, QuestionStatus.Published, pageable);
			else if(flag == 1)
				ques = questionsRepository.findByLangIdAndStatusOrderByPublishDateDesc(id, QuestionStatus.Published, pageable);
			
		}
		else if(order == 2)
		{
			if(flag == 3)
				ques = questionsRepository.findByStatusOrderByUpVotesDesc(QuestionStatus.Published, pageable);
			else if(flag == 0)
				ques = questionsRepository.findByCategoryIdAndStatusOrderByUpVotesDesc(id, QuestionStatus.Published, pageable);
			else if(flag == 1)
				ques = questionsRepository.findByLangIdAndStatusOrderByUpVotesDesc(id, QuestionStatus.Published, pageable);
		}
		else if(order == 3)
		{
			if(flag == 3)
				ques = questionsRepository.findQuestionsByPublishDateBetween(startDate, endDate, pageable);
			else if(flag == 0)
				ques = questionsRepository.findQuestionsByCategoryIdAndPublishDateBetween(id, startDate, endDate, pageable);
			else if(flag == 1)
				ques = questionsRepository.findQuestionsByLangIdAndPublishDateBetween(id, startDate, endDate, pageable);
		}
		return ques;
	}
	
	@Override
	public Page<Questions> getQuestionListByTag(Integer tagId, Pageable pageable) 
	{
		return questionsRepository.findByTagsTagIdAndStatusOrderByPublishDateDesc(tagId, QuestionStatus.Published, pageable); 
	}

	@Override
	public QuestionDetails getQuestionAndAnswers(Integer quesId) 
	{
		return quesAnswersRepository.findByQuestionId(quesId);
	}
	
	@Override
	public Page<Answers> getAllAnswers(Integer quesId, Integer order, Pageable pageable)
	{
		Page<Answers> answers = null;
		if(order == 1)
			answers = answersRepository.findByQuestionId(quesId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "upVotes")));
		else if(order == 2)
			answers = answersRepository.findByQuestionId(quesId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "answerDate")));
		else if(order == 3)
			answers = answersRepository.findByQuestionId(quesId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "ownerAccept")));
		return answers;
	}

	@Override
	public Integer addNewQuestion(String user, AddQues ques) 
	{
		AddQuestion data = new AddQuestion();
		data.setCategoryId(ques.getCategoryId());
		data.setLangId(ques.getLangId());
		data.setTitle(ques.getTitle());
		data.setQuestion(ques.getQuestion());
		data.setStatus(QuestionStatus.Submitted);
		data.setUserId(userLoginRepository.getUserIdByUserName(user));
		data.setSubmittedDate(LocalDateTime.now()); 
		
		AddQuestion res = addQuestionRepository.save(data);
		
		if(!CollectionUtils.isEmpty(ques.getTagIds()))
		{
			List<Integer> tags = ques.getTagIds();
			for(Integer i: tags) {
				AddQuesTags obj = new AddQuesTags();
				obj.setQuestionId(res.getQuestionId()); 
				obj.setTagId(i); 
				AddQuesTags resp = addQuesTagsRepository.save(obj);
			}
		}
		return res.getQuestionId();
	}
	
	@Override
	public Integer postAnswer(String user, PostAnswer ans) 
	{
		PostNewAnswer data = new PostNewAnswer();
		data.setQuestionId(ans.getQuestionId());
		data.setAnswer(ans.getAnswer());
		data.setAnswerDate(LocalDateTime.now());
		data.setStatus(QuestionStatus.Submitted); 
		data.setUserId(userLoginRepository.getUserIdByUserName(user));
		
		PostNewAnswer resp = postAnswerRepository.save(data);
		return resp.getAnswerId();
	}
	
	@Override
	public String joinCommunity(String userName) throws UserActivationException
	{
		Integer userId = userLoginRepository.getUserIdByUserName(userName);
		JoinCommunity data = new JoinCommunity();
		data.setUserId(userId);
		Community status = joinCommunityRepository.findCommunityByUserId(userId);
		if(status == Community.ON)
			return "You are already joined community.";
		data.setCommunityJoinDate(LocalDateTime.now());
		data.setCommunity(Community.ON); 
		JoinCommunity res = joinCommunityRepository.save(data);
		if(res.getCommunityJoinDate() == null)
			throw new UserActivationException("User Community Activation Exception");
		return "Community Activated.";
	}
	
	@Override
	public Page<Users> getUserByName(String name, Integer order, Pageable pageable)
	{
		Page<Users> users = null;
		if(order == 1)
			users = communityUsersRepository.findByNameContaining(name, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "reputation")));
		else if(order == 2)
			users = communityUsersRepository.findByNameContaining(name, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastPostedDate")));
		else if(order == 3)
			users = communityUsersRepository.findByNameContaining(name, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "regDate")));
		return users;
	}
	
	@Override
	public Page<Users> getAllCommunityUsers(Integer order, String user, Pageable pageable) throws UserRequestParameterException
	{
		Page<Users> users = null;
		if(order <=0 || order > 3)
			throw new UserRequestParameterException("Invalid order parameter!");
		if(user.equals("com"))
		{
			if(order == 1)
				users = communityUsersRepository.findByCommunity(Community.ON, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "reputation")));
			else if(order == 2)
				users =  communityUsersRepository.findByCommunity(Community.ON, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastPostedDate")));
			else if(order == 3)
				users =  communityUsersRepository.findByCommunity(Community.ON, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "regDate")));
		}
		if(user.equals("all"))
		{
			if(order == 1)
				users = communityUsersRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "reputation")));
			else if(order == 2)
				users =  communityUsersRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastPostedDate")));
			else if(order == 3)
				users =  communityUsersRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "regDate")));
		}
		List<Users> list = users.getContent();
		for(Users i: list) {
			Integer qc = questionsRepository.countByUserId(i.getUserId());
			Integer ac = answersRepository.countByUserId(i.getUserId());
			i.setNoq(qc);
			i.setNos(ac);
		} 
		return users;
	}
	
	@Override
	public Page<Tags> getAllTags(Integer order, Pageable pageable) throws UserRequestParameterException
	{
		Page<Tags> tags = null;
		if(order <=0 || order > 3)
			throw new UserRequestParameterException("Invalid order parameter!");
		else if(order == 1)
			tags = tagsRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "quesCount")));
		else if(order == 2)
			tags =  tagsRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "title")));
		else if(order == 3)
			tags =  tagsRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate")));
		return tags; 
	}

	@Override
	public Page<Tags> getTagByName(String title, Integer order, Pageable pageable)
	{
		Page<Tags> tags = null;
		if(order == 1)
			tags = tagsRepository.findByTitleContaining(title, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "quesCount")));
		else if(order == 2)
			tags = tagsRepository.findByTitleContaining(title, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "title")));
		else if(order == 3)
			tags = tagsRepository.findByTitleContaining(title, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate")));
		return tags;
	}
	
	@Override
	public AddQuesComment addQuesComment(String user, QuesComment req) 
	{
		AddQuesComment data = new AddQuesComment();
		data.setQuestionId(req.getQuestionId());
		data.setComment(req.getComment());
		data.setStatus(QuestionStatus.Published);
		data.setCreatedDate(LocalDateTime.now());
		data.setUserId(userLoginRepository.getUserIdByUserName(user));
		data.setUserName(userRegRepository.getUserNameByUserId(data.getUserId()));
		return addQuesCommentRepository.save(data);
	}
	
	@Override
	public AddAnswerComments addAnsComment(String user, AnsComment req) 
	{
		AddAnswerComments data = new AddAnswerComments();
		data.setAnswerId(req.getAnswerId());
		data.setComment(req.getComment());
		data.setStatus(QuestionStatus.Published);
		data.setCreatedDate(LocalDateTime.now());
		data.setUserId(userLoginRepository.getUserIdByUserName(user));
		data.setUserName(userRegRepository.getUserNameByUserId(data.getUserId()));
		return addAnsCommentRepository.save(data);
	}
	
	@Override
	public Page<TrendingQuestions> getTrendingQuestions(Pageable pageable)
	{
		int downVotes = 0;
		LocalDateTime endDate = LocalDateTime.now();
		LocalDateTime startDate = LocalDateTime.now().minusDays(30);
		Page<TrendingQuestions> questions = trendingQuesRepository.findByDownVotesLessThanEqualAndLastActivityBetween(downVotes, startDate, endDate, pageable); 

		List<TrendingQuestions> quesList = questions.getContent();
		for(TrendingQuestions i: quesList) { 
			i.setOwnerAccept(answersRepository.getOwnerAcceptanceFlag(i.getQuestionId(),1));
		}
		return questions;
	}
	
	@Override
	public Page<TrendingIQ> getTrendingInterviewQuestions(Pageable pageable) 
	{
		return trendingIQRepository.findByStatusOrderByVoteDesc(IQStatus.Live, pageable);
	}

	@Override
	public String submitVote(String user, QuesVoteReq req)
	{
		Integer userId = userLoginRepository.getUserIdByUserName(user);
		QuesVote data = new QuesVote();
		boolean b = quesVoteRepository.isAlreadyVoted(userId, req.getQuesId());
		if(b == false) {
			data.setQuestionId(req.getQuesId());
			data.setVote(req.getVote()); 
			data.setUserId(userId); 
			QuesVote res = quesVoteRepository.save(data);
			if(res.getVote() == 1)
				return "Thanks for UpVote.";
			else
				return "Thanks for DownVote";
		}
		else {
			return "Already Voted!";
		}
		
	}
	
	@Override
	public String submitAnswerVote(String user, AnsVoteReq req)
	{
		Integer userId = userLoginRepository.getUserIdByUserName(user);
		AnsVote data = new AnsVote();
		boolean b = ansVoteRepository.isAlreadyVoted(userId, req.getQuesId(), req.getAnsId());
		if(b == false) {
			data.setQuestionId(req.getQuesId());
			data.setAnswerId(req.getAnsId());
			data.setVote(req.getVote()); 
			data.setUserId(userId); 
			AnsVote res = ansVoteRepository.save(data);
			if(res.getVote() == 1)
				return "Thanks for UpVote.";
			else
				return "Thanks for DownVote";
		}
		else {
			return "Already Voted!";
		}
		
	}
}
