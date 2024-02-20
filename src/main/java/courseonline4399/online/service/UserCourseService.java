package courseonline4399.online.service;

import courseonline4399.online.model.UserCourse;
import courseonline4399.online.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCourseService implements CRUD<UserCourse, Integer> {
    @Autowired
    UserCourseRepository userCourseRepository;

    @Override
    public UserCourse create(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    public UserCourse update(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    public void delete(Integer integer) {

    }
    public UserCourse findByCourseId(Integer id,Integer userid){
        return  userCourseRepository.findByCourseId(id,userid);
    }

    @Override
    public List<UserCourse> findAll() {
        return userCourseRepository.findAll();
    }

    @Override
    public UserCourse findById(Integer integer) {
        return null;
    }
    public Page<UserCourse> findUserCoursesByUserId(int userId,Pageable page) {
        return userCourseRepository.findByOwner_Id(userId, page);
    }
    public List<UserCourse> findUserCoursesByUserId2(int userId) {
        return userCourseRepository.findByOwner_Id(userId);
    }
    public Page<UserCourse> findByOwnerIdAndUserCoursenameContainingIgnoreCase(int userId, String coursename, Pageable page) {
        return userCourseRepository.findByOwnerIdAndUserCoursenameContainingIgnoreCase(userId, coursename,page);
    }

    public Page<UserCourse> findByOwnerIdAndUserCoursenameAndCategoryId(int userId, String coursename, int categoryId, Pageable page) {
        return userCourseRepository.findByOwnerIdAndUserCoursenameContainingIgnoreCaseAndCategory_Id(userId, coursename, categoryId,page);
    }
}
