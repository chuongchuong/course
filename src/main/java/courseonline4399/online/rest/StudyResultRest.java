package courseonline4399.online.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import courseonline4399.online.model.*;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.MailerService;
import courseonline4399.online.service.StudyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudyResultRest {
    @Autowired
    StudyResultService studyResultService;
    @Autowired
    private MailerService mailer;
    @Autowired
    AccountService accountService;
    @GetMapping("rest/studyresult/findAll")
    public ResponseEntity<List<StudyResult>> getAll(){
        List<StudyResult> studyResults = studyResultService.findAll();
        return  ResponseEntity.ok(studyResults);
    }
    @PostMapping("rest/studyresult/sendmail")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> requestBody) {
        try {
            String dynamicString = requestBody.get("dynamicString");
            mailer.send(new MailInfo(accountService.getCurrentUserInfo().getEmail(), "Hoàn thành khóa học" ,
                     dynamicString ));


            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error sending email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("rest/studyresult/find/{id}")
    public ResponseEntity<StudyResult> getAl(@PathVariable("id") Integer id){
        User user = accountService.getCurrentUserInfo();
        StudyResult studyResult = studyResultService.findByUserAndQuiz1(user,id);
        return ResponseEntity.ok(studyResult);
    }
    @GetMapping("rest/studyresult/getDoneQuiz/{courseid}")
    public ResponseEntity<List<StudyResult>> getDontQuiz(@PathVariable("courseid") Integer id){
        User user = accountService.getCurrentUserInfo();
        List<StudyResult> studyResult = studyResultService.findQuiz(id,user.getId());
        return ResponseEntity.ok(studyResult);
    }
    @PostMapping("rest/studyresult/creates/{id}")
    public ResponseEntity<StudyResult> create(@RequestBody JsonNode studyResults,@PathVariable("id") Integer id){
        ObjectMapper mapper = new ObjectMapper();
        StudyResult study = mapper.convertValue(studyResults, StudyResult.class);
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setId(id);
         study.setDetail(courseDetail);
         // check du > 50 moi set status = true va nguoc lai
        float dung = study.getCorrect();
        float sai = study.getWrong();
        float phantram =  (   dung / (dung+sai)    )*100;
        if (phantram>=50){
            study.setStatus(Boolean.TRUE);
        }else {
            study.setStatus(Boolean.FALSE);
        }
        StudyResult studyResults1 = studyResultService.create(study);
        return  ResponseEntity.ok(studyResults1);
    }

    @PutMapping("rest/studyresult/update")
    public ResponseEntity<StudyResult> update(@RequestBody StudyResult studyResults){
        StudyResult studyResults1 = studyResultService.update(studyResults);
        return  ResponseEntity.ok(studyResults1);
    }

    @DeleteMapping("rest/studyresult/delete/{id}")
    public void  delete(@PathVariable("id") Integer id){
        studyResultService.delete(id);
    }
    @GetMapping("rest/studyresult/edit/{id}")
    public ResponseEntity<StudyResult>  edit(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(studyResultService.findById(id));

    }
}
