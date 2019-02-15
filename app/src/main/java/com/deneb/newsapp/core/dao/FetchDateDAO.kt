package com.deneb.newsapp.core.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.deneb.newsapp.features.news.FetchEntity

@Dao
interface FetchDateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFetchDate(fetchEntity: FetchEntity)

    @Query("SELECT * FROM FetchEntity WHERE id == :fetchId")
    fun getFetchDataById(fetchId: Int): FetchEntity
}