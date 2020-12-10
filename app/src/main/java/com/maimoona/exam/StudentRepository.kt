package com.maimoona.exam

import android.content.Context
import androidx.room.Room
import database.StudentDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "student-database"

class StudentRepository private constructor(context: Context) {

    private val database : StudentDatabase = Room.databaseBuilder(
        context.applicationContext,
        StudentDatabase::class.java,
        DATABASE_NAME
    ).allowMainThreadQueries().build()

    private val studentDao = database.studentDao()
    fun getStudents(): List<Students> = studentDao.getStudents()
    fun getStudent(id: UUID): Students? = studentDao.getStudent(id)

    private val executor = Executors.newSingleThreadExecutor()

    fun addStudent(student: Students) {
        executor.execute {
            studentDao.addStudent(student)
        }
    }
    fun updateStudent(student: Students) {
        executor.execute {
            studentDao.updateStudent(student)
        }
    }

    companion object {
        private var INSTANCE: StudentRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository(context)
            }
        }
        fun get(): StudentRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
