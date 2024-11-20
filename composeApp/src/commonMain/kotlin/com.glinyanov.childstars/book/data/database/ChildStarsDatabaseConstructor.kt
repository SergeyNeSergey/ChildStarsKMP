package com.glinyanov.childstars.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ChildStarsDatabaseConstructor: RoomDatabaseConstructor<ChildStarsDatabase> {
    override fun initialize(): ChildStarsDatabase
}