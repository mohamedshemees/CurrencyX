package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.adapter.SpinnerCustomAdapter
import com.example.myapplication.databinding.FragmentCurrencyRatioBinding
import com.example.myapplication.utils.FormatUtils
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


class CurrencyyFragment : Fragment() {

    private val viewModel: CurrencyViewModel by viewModels()
    private var _binding: FragmentCurrencyRatioBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyRatioBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateTv.text = viewModel.uiDate.value
        val baseSpinner: Spinner = binding.baseCurrencySpinner
        val targetSpinner: Spinner = binding.targetCurrencySpinner
        val textfield: TextInputLayout = binding.amountEt
        val textView: TextView = binding.ResultTv
        val spinneradapter = SpinnerCustomAdapter(viewModel.loadCurrencyList())

        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.before(FormatUtils.getYesterdayInMillis())) // Blocks future dates
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setCalendarConstraints(constraintsBuilder.build())
                .build()
        binding.calender.setOnClickListener {
            datePicker.show(parentFragmentManager, "tag")
            datePicker.addOnPositiveButtonClickListener { selection ->
                viewModel.updateDate(selection)
            }
        }
        textfield.editText?.doOnTextChanged { inputText, _, _, _ ->
            lifecycleScope.launch {
                viewModel.updateAmount(inputText.toString())
                viewModel.digitLimit.collect {
                    if (it) {
                        textfield.error = "Amount is too large"
                    } else {
                        textfield.error = null
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.uiDate.collect { date ->
                binding.dateTv.text = date
            }
        }
        lifecycleScope.launch {
            viewModel.result.collect { result ->
                textView.text = result
            }
        }
        spinneradapter.items
        targetSpinner.adapter = spinneradapter
        targetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selecteditem = viewModel.currenciesInfo[p2]
                viewModel.updateTargetCurrency(selecteditem.appreviation)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        baseSpinner.adapter = spinneradapter
        baseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selecteditem = viewModel.currenciesInfo[p2]

                viewModel.updateBaseCurrency(selecteditem.appreviation)
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





