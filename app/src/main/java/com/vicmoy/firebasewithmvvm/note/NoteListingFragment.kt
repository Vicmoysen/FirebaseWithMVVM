package com.vicmoy.firebasewithmvvm.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicmoy.firebasewithmvvm.R
import com.vicmoy.firebasewithmvvm.databinding.FragmentNoteListingBinding


class NoteListingFragment : Fragment() {

    private val TAG = "NoteListingFragment"
    private lateinit var binding: FragmentNoteListingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}