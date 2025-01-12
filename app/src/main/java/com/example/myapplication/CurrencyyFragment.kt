package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.adapter.SpinnerCustomAdapter
import com.example.myapplication.databinding.FragmentFirstBinding
import com.google.android.material.datepicker.MaterialDatePicker

import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class CurrencyyFragment : Fragment() {

    private val viewModel: CurrencyFragmentViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    var basecurrency = "AUD"
    var wantdcurrency = "AUD"

    @SuppressLint("SuspiciousIndentation", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel.getCurrencyData()

        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseSpinner: Spinner = binding.baseCurrencySpinner
        val wantedSpinner: Spinner = binding.wantedCurrencySpinner
        val textfield: EditText = binding.wantedCurrencyEditText
        val textView: TextView = binding.ResultTv
        val currenciesInfo = viewModel.currenciesInfo
        val spinneradapter = context?.let { SpinnerCustomAdapter(it, currenciesInfo) }
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.calender.setOnClickListener {
            datePicker.show(parentFragmentManager, "tag")
            datePicker.addOnPositiveButtonClickListener { selection ->
                if (selection != null) {
                    val selectedDate =  Instant.ofEpochMilli(selection)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                    println("Selected date: $selectedDate")

                    viewModel.updateDate(selectedDate.toString())
                    lifecycleScope.launch {
                        viewModel.showResult(
                            basecurrency,
                            wantdcurrency,
                            textfield.text.toString().toDouble(),
                            viewModel.date.value)
                    }
                } else {
                    println("No date selected")
                }
            }

        }


        textfield.addTextChangedListener { editable ->
            editable?.let {
                if (it.isNotEmpty()) {
                    viewModel.updateInput(it.toString())
                    lifecycleScope.launch {
                        if (it.isNotEmpty()) {
                            viewModel.showResult(
                                basecurrency,
                                wantdcurrency,
                                it.toString().toDouble(),
                                viewModel.date.value
                            )
                        }
                }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.result.collect {
                textView.text = it.toString()
            }
        }
        lifecycleScope.launch {
            viewModel.input.collect {
        }
        }
        baseSpinner.adapter = spinneradapter
        baseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selecteditem = viewModel.currenciesInfo[p2]
                basecurrency = selecteditem.appreviation
                lifecycleScope.launch {
                    viewModel.showResult(
                        basecurrency,
                        wantdcurrency,
                        viewModel.input.value.toDouble(),
                        viewModel.date.value

                    )
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        wantedSpinner.adapter = spinneradapter
        wantedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selecteditem = viewModel.currenciesInfo[p2]
                wantdcurrency = selecteditem.appreviation
                lifecycleScope.launch {
                    viewModel.showResult(
                        basecurrency,
                        wantdcurrency,
                        viewModel.input.value.toDouble(),
                        viewModel.date.value

                    )
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}





