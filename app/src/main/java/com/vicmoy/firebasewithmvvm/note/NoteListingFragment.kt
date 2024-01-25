package com.vicmoy.firebasewithmvvm.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vicmoy.firebasewithmvvm.R
import com.vicmoy.firebasewithmvvm.data.model.Note
import com.vicmoy.firebasewithmvvm.databinding.FragmentNoteListingBinding
import com.vicmoy.firebasewithmvvm.util.UiState
import com.vicmoy.firebasewithmvvm.util.hide
import com.vicmoy.firebasewithmvvm.util.show
import com.vicmoy.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {

    private val TAG = "NoteListingFragment"
    private lateinit var binding: FragmentNoteListingBinding
    val viewModel: NoteViewModel by viewModels()
    var position: Int = -1
    val adapter by lazy {
        NoteListingAdapter(
            onItemClicked = {pos, item ->
                findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment, Bundle().apply {
                    putString("type", "view")
                    putParcelable("note", item)
                })
            },
            onEditClicked = {pos, item ->
                findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment, Bundle().apply {
                    putString("type", "edit")
                    putParcelable("note", item)
                })
            },
            onDeleteClicked = {pos, item ->
                    position = pos
                    viewModel.deleteNote(item)

            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment, Bundle().apply {
                    putString("type", "create")
            })
        }

        viewModel.getNotes()
        viewModel.note.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    toast(state.error)
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    adapter.updateList(state.data.toMutableList())

                }
            }
        }

        viewModel.deleteNote.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    toast(state.error)
                }

                is UiState.Success -> {

                    binding.progressBar.hide()
                    toast(state.data)
                    adapter.removeItem(position)

                }
            }
        }

    }

}