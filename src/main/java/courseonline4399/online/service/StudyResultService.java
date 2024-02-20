package courseonline4399.online.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import courseonline4399.online.model.*;
import courseonline4399.online.repository.StudyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyResultService implements CRUD<StudyResult,Integer>{

    @Autowired
    StudyResultRepository studyResultRepository;

    @Autowired
    AccountService accountService;
    @Override
    public StudyResult create(StudyResult studyResult) {

        User user = accountService.getCurrentUserInfo();
        studyResult.setUser(user);

        StudyResult studyResult1 = findByUserAndQuiz(user,studyResult.getDetail());

        if(studyResult1 == null){
            return studyResultRepository.save(studyResult);
        }
        return update(studyResult);
    }


    public StudyResult findstatus(StudyResult studyResult) {

        User user = accountService.getCurrentUserInfo();


        StudyResult studyResult1 = findByUserAndQuiz(user,studyResult.getDetail());

        if(studyResult1 == null){
          return  null;
        }
        return studyResult1;
    }

    @Override
    public StudyResult update(StudyResult studyResult) {

        User user = accountService.getCurrentUserInfo();

        StudyResult studyResult1 = findByUserAndQuiz(user,studyResult.getDetail());
        studyResult1.setCorrect(studyResult.getCorrect());
        studyResult1.setWrong(studyResult.getWrong());
        float dung = studyResult1.getCorrect();
        float sai = studyResult1.getWrong();
        float phantram =  (   dung / (dung+sai)    )*100;
        if (phantram>=50){
            studyResult1.setStatus(Boolean.TRUE);
        }else {
            studyResult1.setStatus(Boolean.FALSE);
        }

        return studyResultRepository.save(studyResult1);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<StudyResult> findAll() {
        return studyResultRepository.findAll();
    }

    @Override
    public StudyResult findById(Integer integer) {
        return null;
    }

    public StudyResult findByUserAndQuiz(User user , CourseDetail courseDetail){
        StudyResult studyResult = studyResultRepository.findByUserAndDetail(user.getId() , courseDetail.getId());
        return  studyResult;
    }

    public StudyResult findByUserAndQuiz1(User user , Integer id){
        StudyResult studyResult = studyResultRepository.findByUserAndDetail(user.getId() , id);
        return  studyResult;
    }
    public List<StudyResult> findQuiz(Integer course , Integer user){

        return  studyResultRepository.findCompletedQuizByCourseId(course,user);
    }


}
