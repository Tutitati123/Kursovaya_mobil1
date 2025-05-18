package com.example.kur_1.ui.folders

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.kur_1.R

class AddFolderDialog : DialogFragment() {
    private var onFolderCreated: ((String) -> Unit)? = null

    fun setOnFolderCreatedListener(listener: (String) -> Unit) {
        onFolderCreated = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val editText = EditText(requireContext()).apply {
            hint = "Название папки"
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Создать папку")
            .setView(editText)
            .setPositiveButton("Создать") { _, _ ->
                val name = editText.text.toString().trim()
                if (name.isNotEmpty()) {
                    onFolderCreated?.invoke(name)
                }
            }
            .setNegativeButton("Отмена", null)
            .create()
    }
}