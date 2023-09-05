package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.service.QuizService;
@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuizDTO addQuiz(QuizDTO quizDTO) {
        Quiz quiz = convertToEntity(quizDTO);
        quizRepository.save(quiz);
        return convertToDTO(quiz);
    }

    @Override
    public QuizDTO updateQuiz(Integer quizId, QuizDTO quizDTO) throws NotFoundException {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            quiz.setQuizName(quizDTO.getQuizName());
            quiz.setQuizDescription(quizDTO.getQuizDescription());
            quiz.setTimeInMinutes(quizDTO.getTimeInMinutes());
            quiz.setCategory(categoryRepository.findById(quizDTO.getCategory().getCategoryId()).orElse(null));
            quizRepository.save(quiz);
            return convertToDTO(quiz);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteQuiz(Integer quizId) throws NotFoundException {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            quizRepository.deleteById(quizId);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public QuizDTO getQuizById(Integer quizId) throws NotFoundException {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            return convertToDTO(quiz);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private QuizDTO convertToDTO(Quiz quiz) {
        return modelMapper.map(quiz, QuizDTO.class);
    }

    private Quiz convertToEntity(QuizDTO quizDTO) {
        return modelMapper.map(quizDTO, Quiz.class);
    }
}
