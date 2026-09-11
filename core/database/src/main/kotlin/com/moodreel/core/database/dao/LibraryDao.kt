package com.moodreel.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.moodreel.core.database.entity.LibraryItemEntity
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaType
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LibraryDao {
    @Query("SELECT * FROM library_items WHERE status =:status ORDER BY added_at DESC")
    fun observeStatus(status: LibraryStatus): Flow<List<LibraryItemEntity>>

    @Query("SELECT * FROM library_items WHERE media_id = :mediaId AND media_type = :mediaType")
    fun observeItem(mediaId: Int, mediaType: MediaType): Flow<LibraryItemEntity?>

    @Upsert
    fun upsert(item: LibraryItemEntity)

    @Query("DELETE FROM library_items WHERE media_id = :mediaId AND media_type = :mediaType")
    fun delete(mediaId: Int, mediaType: MediaType)
}
