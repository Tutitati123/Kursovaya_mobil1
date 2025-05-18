// BookDetailFragment.kt
package com.example.kur_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kur_1.databinding.FragmentBookDetailBinding
import com.example.kur_1.entities.Book
import com.example.kur_1.data.BookRepository
import com.example.kur_1.data.AppDatabase

class BookDetailFragment : Fragment() {
    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val args: BookDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = AppDatabase.getDatabase(requireContext()).bookDao()
        val repository = BookRepository(dao)
        val factory = HomeViewModelFactory(repository)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        val book = args.book
        displayBookDetails(book)

        binding.btnDelete.setOnClickListener {
            homeViewModel.delete(book)
            findNavController().popBackStack()
        }

        binding.btnEdit.setOnClickListener {
            val action = BookDetailFragmentDirections.actionBookDetailFragmentToEditBookFragment(book)
            findNavController().navigate(action)
        }

        binding.btnSaveReview.setOnClickListener {
            val rating = binding.ratingBar.rating
            val review = binding.etReview.text.toString()
            val updatedBook = book.copy(rating = rating, review = review)
            homeViewModel.update(updatedBook)
            findNavController().popBackStack()
        }
    }

    private fun displayBookDetails(book: Book) {
        binding.tvTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvGenre.text = book.genre
        binding.tvPages.text = "Всего страниц: ${book.totalPages}"
        binding.tvPagesRead.text = "Прочитано: ${book.pagesRead}"

        val percentage = if (book.totalPages > 0) {
            (book.pagesRead.toFloat() / book.totalPages.toFloat() * 100).toInt()
        } else {
            0
        }
        binding.tvPercentage.text = "Прочитано: $percentage%"

        binding.ratingBar.rating = book.rating
        binding.etReview.setText(book.review)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}