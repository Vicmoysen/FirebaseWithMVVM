package com.vicmoy.firebasewithmvvm.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.vicmoy.firebasewithmvvm.R
import com.vicmoy.firebasewithmvvm.databinding.FragmentNoteListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {

    private val TAG = "NoteListingFragment"
    private lateinit var binding: FragmentNoteListingBinding
    val viewModel: NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes()
        viewModel.note.observe(viewLifecycleOwner) {
            it.forEach {
                Log.e(TAG, it.toString())
            }
        }
    }

}