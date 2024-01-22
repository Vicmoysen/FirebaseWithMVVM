package com.vicmoy.firebasewithmvvm.data.repository

import com.vicmoy.firebasewithmvvm.data.model.Note

interface NoteRepository {
    fun getNotes(): List<Note>
}