package com.example.kur_1.ui.append

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kur_1.data.AppDatabase
import com.example.kur_1.entities.Book
import com.example.kur_1.databinding.FragmentAppendBinding

class AppendFragment : Fragment() {

    private var _binding: FragmentAppendBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AppendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val database = AppDatabase.getDatabase(requireContext())
        viewModel = ViewModelProvider(this, AppendViewModelFactory(database))
            .get(AppendViewModel::class.java)

        _binding = FragmentAppendBinding.inflate(inflater, container, false)

        setupSaveButton()

        return binding.root
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val book = Book(
                title = binding.etTitle.text.toString(),
                author = binding.etAuthor.text.toString(),
                pageCount = binding.etPageCount.text.toString().toIntOrNull() ?: 0,
                currentPage = binding.etCurrentPage.text.toString().toIntOrNull() ?: 0,
                genre = binding.etGenre.text.toString(),
                folder = binding.spinnerFolder.selectedItem?.toString() ?: "Default"
            )

            viewModel.insertBook(book)
            // Можно добавить сообщение об успешном сохранении
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}