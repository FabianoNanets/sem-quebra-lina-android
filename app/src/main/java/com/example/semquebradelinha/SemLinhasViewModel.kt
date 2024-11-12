package com.example.semquebradelinha

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SemLinhasViewModel : ViewModel() {

    var areaText = MutableLiveData("")
    var selectWord = MutableLiveData("")

    fun removeBreakLine() {
        val text = areaText.value ?: return
        val newText = text.replace("\n", " ")
        areaText.value = newText
    }

    fun searchWord(context: Context) {
        if (selectWord.value.orEmpty().isEmpty()) {
            Toast.makeText(context, "Por favor, escreva uma palavra", Toast.LENGTH_SHORT).show()
            return
        }
        val text = areaText.value ?: return
        areaText.value = highlightWord(text, selectWord.value.orEmpty())
    }

    private fun highlightWord(text: String, word: String): String {
        return text.split(" ").joinToString(" ") {
            if (it.equals(word, ignoreCase = true)) '"'+ it.uppercase() + '"' else it
        }
    }


    fun cleanText() {
        areaText.value = ""
        selectWord.value = ""
    }

    fun copyText(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("copied_text", areaText.value)
        clipboard.setPrimaryClip(clip)
    }
}