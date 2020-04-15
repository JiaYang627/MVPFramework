package com.jiayang.mvp.mvpframework.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jiayang.mvp.mvpframework.database.entities.User;

import java.util.List;


@Dao
public interface UserDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(User... users);

	@Query("SELECT * from user")
    List<User> loadUser();

    @Delete
    int delete(User user);

    @Delete
    int deleteAll(List<User> users);
}
