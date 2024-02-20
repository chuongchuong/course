package courseonline4399.online.rest;

import courseonline4399.online.model.Quiz;
import courseonline4399.online.model.User;
import courseonline4399.online.repository.QuizRepository;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizRest {
    @Autowired
    QuizService quizService ;

    @GetMapping("rest/quiz/findAll")
    public ResponseEntity<List<Quiz>> getAll(){
        List<Quiz> quizs = quizService.findAll();
        return  ResponseEntity.ok(quizs);

    }
    @GetMapping("rest/quiz/{id}")
    public ResponseEntity<List<Quiz>> getByCourseId(@PathVariable("id") Integer id){

        List<Quiz> quizs = quizService.findByCourseDetailId(id);
        return  ResponseEntity.ok(quizs);
    }
    @PostMapping("rest/quiz/creates")
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz){
        Quiz quizz = quizService.create(quiz);
        return  ResponseEntity.ok(quizz);
    }

    @PutMapping("rest/quiz/update")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
        Quiz quizz = quizService.update(quiz);
        return  ResponseEntity.ok(quizz);
    }
    @DeleteMapping("rest/quiz/delete/{id}")
    public void  delete(@PathVariable("id") Integer id){
         quizService.delete(id);
    }
    @GetMapping("rest/quiz/edit/{id}")
    public ResponseEntity<Quiz>  edit(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(quizService.findById(id));

    }
}
