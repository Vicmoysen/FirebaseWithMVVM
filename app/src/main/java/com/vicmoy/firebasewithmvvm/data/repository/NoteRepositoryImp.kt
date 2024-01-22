package com.vicmoy.firebasewithmvvm.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.vicmoy.firebasewithmvvm.data.model.Note
import java.util.Date

class NoteRepositoryImp(
    val database: FirebaseFirestore
): NoteRepository {

    override fun getNotes(): List<Note> {
        return arrayListOf(
            Note(
                id="gdsdsgf",
                text = "sdfafad",
                date = Date()
            ),
            Note(
                id="gdsdsgf",
                text = "sdfafad",
                date = Date()
            ),
            Note(
                id="gdsdsgf",
                text = "sdfafad",
                date = Date()
            )
        )
    }

}
