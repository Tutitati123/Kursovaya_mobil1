package com.example.kur_1.ui.folders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kur_1.data.AppDatabase
import com.example.kur_1.data.FolderRepository
import com.example.kur_1.databinding.FragmentFoldersBinding
import com.example.kur_1.entities.Folder
import com.example.kur_1.viewmodels.FolderViewModel
import com.example.kur_1.viewmodels.FolderViewModelFactory

class FoldersFragment : Fragment() {
    private var _binding: FragmentFoldersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FolderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoldersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val folderDao = AppDatabase.getDatabase(requireContext()).folderDao()
        val repository = FolderRepository(folderDao)
        viewModel = ViewModelProvider(
            this,
            FolderViewModelFactory(repository)
        ).get(FolderViewModel::class.java)

        val adapter = FoldersAdapter { folder: Folder ->

            Toast.makeText(requireContext(), "Выбрана папка: ${folder.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewFolders.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
        viewModel.allFolders.observe(viewLifecycleOwner) { folders: List<Folder>? ->
            folders?.let { nonNullFolders ->
                adapter.submitList(nonNullFolders)
            }
        }

        binding.fabAddFolder.setOnClickListener {
            showAddFolderDialog()
        }
    }

    private fun showAddFolderDialog() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}