// EditBookFragment.kt
package com.example.kur_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kur_1.databinding.FragmentEditBookBinding
import com.example.kur_1.entities.Book
import com.example.kur_1.data.BookRepository
import com.example.kur_1.data.AppDatabase

class EditBookFragment : Fragment() {
    private var _binding: FragmentEditBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val args: EditBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = AppDatabase.getDatabase(requireContext()).bookDao()
        val repository = BookRepository(dao)
        val factory = HomeViewModelFactory(repository)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        val book = args.book
        populateFields(book)

        binding.btnSave.setOnClickListener {
            val updatedBook = getUpdatedBook(book)
            homeViewModel.update(updatedBook)
            findNavController().popBackStack()
        }
    }

    private fun populateFields(book: Book) {
        binding.etTitle.setText(book.title)
        binding.etAuthor.setText(book.author)
        binding.etGenre.setText(book.genre)
        binding.etTotalPages.setText(book.totalPages.toString())
        binding.etPagesRead.setText(book.pagesRead.toString())
    }

    private fun getUpdatedBook(originalBook: Book): Book {
        return originalBook.copy(
            title = binding.etTitle.text.toString(),
            author = binding.etAuthor.text.toString(),
            genre = binding.etGenre.text.toString(),
            totalPages = binding.etTotalPages.text.toString().toIntOrNull() ?: 0,
            pagesRead = binding.etPagesRead.text.toString().toIntOrNull() ?: 0
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}