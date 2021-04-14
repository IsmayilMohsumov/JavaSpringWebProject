package com.weblogin.demo.repository;

import com.weblogin.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        public User findByEmail(String email);

        @Modifying
        @Transactional
        @Query(value = "delete from auth_user where AUTH_USER.AUTH_USER_ID=?1",nativeQuery = true)
        public void deleteById(Integer id);

        List<User> findByNameLike(String name);

        @Query(value = "select AU.AUTH_USER_ID from auth_user au where AU.EMAIL=?1",nativeQuery = true)
        public Integer findIdByEmail(String email);

        @Modifying
        @Transactional
        @Query(value = "update auth_user a  set A.DESCRIPTION=?1 where A.AUTH_USER_ID=?2",nativeQuery = true)
        public void  updateDesc(String description,Integer userId);

        @Modifying
        @Transactional
        @Query(value = "update auth_user a  set A.FIRST_NAME=?1,A.LAST_NAME=?2,A.EMAIL=?3 where A.AUTH_USER_ID=?4",nativeQuery = true)
        public  void updateUser(String name,String surname,String email,Integer id);

//    @Query(value = "update User set image=?1 where id?2")
//    public updateImage(String url,Integer id);


}
