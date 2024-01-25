package com.vicmoy.firebasewithmvvm.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.vicmoy.firebasewithmvvm.data.model.Note
import com.vicmoy.firebasewithmvvm.util.FireStoreTables
import com.vicmoy.firebasewithmvvm.util.UiState
import java.util.Date

class NoteRepositoryImp(
    val database: FirebaseFirestore
): NoteRepository {

    override fun getNotes(result: (UiState<List<Note>>) -> Unit) {
        database.collection((FireStoreTables.NOTE))
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for (document in it) {
                    val note = document.toObject(Note::class.java)
                    notes.add(note)
                }
                result.invoke(UiState.Success(notes))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }


    }

    override fun addNote(note: Note, result: (UiState<String>) -> Unit) {

        val document = database.collection(FireStoreTables.NOTE).document()
        note.id = document.id
        document
            .set(note)
            .addOnSuccessListener {
                result.invoke(UiState.Success("Note has been created succesfully"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

    override fun updateNote(note: Note, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.NOTE).document(note.id)
        document
            .set(note)
            .addOnSuccessListener {
                result.invoke(UiState.Success("Note has been update succesfully"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

    override fun deleteNote(note: Note, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.NOTE).document(note.id)
        document
            .delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("Note has been deleted succesfully"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

}
