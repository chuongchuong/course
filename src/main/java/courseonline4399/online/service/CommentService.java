package courseonline4399.online.service;

import courseonline4399.online.model.Comment;

import courseonline4399.online.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CommentService implements CRUD<Comment,Integer>{
    @Autowired
    CommentRepository repository;
    @Autowired
    AccountService accountService;

    @Override
    public Comment create(Comment comment) {

        return repository.save(comment);
    }
    public Comment changeStatus(Comment comment){
        return repository.save(comment);
    }
    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById(Integer integer) {
        return null;
    }

    public List<Comment> getCommentsByCourseIdSortedByCreatedDate(int courseId) {
        return repository.findAllByCourseIdAndStatusOrderByCreatedDateAsc(courseId,false);
    }


}
