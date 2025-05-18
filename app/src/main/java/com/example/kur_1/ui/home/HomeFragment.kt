package com.example.kur_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kur_1.data.AppDatabase
import com.example.kur_1.data.BookRepository
import com.example.kur_1.databinding.FragmentHomeBinding
import com.example.kur_1.ui.BookAdapter
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация базы данных и репозитория
        val dao = AppDatabase.getDatabase(requireContext()).bookDao()
        val repository = BookRepository(dao)
        val factory = HomeViewModelFactory(repository)

        // Инициализация ViewModel
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        // Настройка RecyclerView
        adapter = BookAdapter { book ->
            // Обработка клика по книге
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Подписка на изменения списка книг
        homeViewModel.allBooks.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)  // Исправленная строка
        }
        adapter = BookAdapter { book ->
            val action = HomeFragmentDirections.actionNavigationHomeToBookDetailFragment(book)
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        homeViewModel.allBooks.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}