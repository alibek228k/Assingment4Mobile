package kz.alibek.assignment4.ex1.repository

import androidx.lifecycle.LiveData
import kz.alibek.assignment4.ex1.model.User
import kz.alibek.assignment4.ex1.model.UserDao

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }
}