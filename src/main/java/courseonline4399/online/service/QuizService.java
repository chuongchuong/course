package courseonline4399.online.service;

import courseonline4399.online.model.Category;
import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.model.Quiz;
import courseonline4399.online.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService implements CRUD<Quiz,Integer>{
    @Autowired
    QuizRepository quizRepository;
    @Autowired CourseDetailService courseDetailService;

    @Override
    public Quiz create(Quiz quiz) {

        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Quiz quiz) {
        Quiz quiz1 = quizRepository.findById(quiz.getId()).get();
        if (quiz1 != null) {
            quiz1.setQuestion(quiz.getQuestion());
            quiz1.setAnswer_a(quiz.getAnswer_a());
            quiz1.setAnswer_b(quiz.getAnswer_b());
            quiz1.setAnswer_c(quiz.getAnswer_c());
            quiz1.setAnswer_d(quiz.getAnswer_d());
            quiz1.setAnswer(quiz.getAnswer());
            CourseDetail courseDetail = courseDetailService.findById(quiz.getCoursedetail().getId());
            quiz1.setCoursedetail(courseDetail);

           return quizRepository.save(quiz1);

        }
        return null;
    }

    public List<Quiz> findByCourseDetailId(Integer integer) {

        return  quizRepository.findByCourseDetailId(integer);
    }
    @Override
    public void delete(Integer integer) {
         quizRepository.deleteById(integer);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findById(Integer integer) {
        return quizRepository.findById(integer).get();
    }
}
