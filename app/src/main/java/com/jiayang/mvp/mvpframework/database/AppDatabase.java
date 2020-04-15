package com.jiayang.mvp.mvpframework.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jiayang.mvp.mvpframework.database.daos.UserDao;
import com.jiayang.mvp.mvpframework.database.entities.User;


//@TypeConverters({Converters.class})
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

	public abstract UserDao userDao();


}
