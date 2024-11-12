package com.example.semquebradelinha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.semquebradelinha.databinding.FragmentSemLinhasBinding

class SemLinhasFragment : Fragment() {

    private var _binding: FragmentSemLinhasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SemLinhasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSemLinhasBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.areaText.doOnTextChanged { text, _, _, _ ->
            viewModel.areaText.value = text.toString()
        }

        binding.wordText.doOnTextChanged { text, _, _, _ ->
            viewModel.selectWord.value = text.toString()
        }

        binding.removeBreakLine.setOnClickListener {
            viewModel.removeBreakLine()
        }

        binding.searchWord.setOnClickListener {
            viewModel.searchWord(requireContext())
        }

        binding.cleanText.setOnClickListener {
            viewModel.cleanText()
        }

        binding.copyText.setOnClickListener {
            viewModel.copyText(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}