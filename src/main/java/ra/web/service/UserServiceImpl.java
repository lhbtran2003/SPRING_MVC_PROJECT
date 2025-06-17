package ra.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.UserDaoImpl;
import ra.web.entity.User;
import ra.web.dto.UserRequest;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDaoImpl userDao;
    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(int id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    public void add(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        userDao.save(user);
    }
    @Transactional
    public void update(User request) {
        User user = userDao.findById(request.getId()).orElse(null);
       if (user != null) {
            if (request.getUsername() !=null) {
                user.setUsername(request.getUsername());
            }
            if (request.getPassword() !=null ) {
                user.setPassword(request.getPassword());
            }
            if (request.getEmail() !=null) {
                user.setEmail(request.getEmail());
            }
            if (request.getFullName() !=null) {
                user.setFullName(request.getFullName());
            }
            if (request.getPhoneNumber() !=null) {
                user.setPhoneNumber(request.getPhoneNumber());
            }
            userDao.save(user);
        }
    }
    @Transactional
    public void delete(int id) {
        User user = userDao.findById(id).orElse(null);
        if (user != null) {
            userDao.delete(user);
        }
    }

}
