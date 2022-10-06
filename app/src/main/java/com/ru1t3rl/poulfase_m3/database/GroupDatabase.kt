package com.ru1t3rl.poulfase_m3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ru1t3rl.poulfase_m3.Converters
import com.ru1t3rl.poulfase_m3.dao.GroupDao
import com.ru1t3rl.poulfase_m3.model.Group

@Database(entities = [Group::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GroupDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao

    companion object {
        private const val DATABASE_NAME = "GROUP_DATABASE"
        @Volatile
        private var groupRoomDatabaseInstance: GroupDatabase? = null

        fun getDatabase(context: Context): GroupDatabase? {
            if (groupRoomDatabaseInstance == null) {
                synchronized(GroupDatabase::class.java) {
                    if (groupRoomDatabaseInstance == null) {
                        groupRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GroupDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return groupRoomDatabaseInstance
        }
    }
}