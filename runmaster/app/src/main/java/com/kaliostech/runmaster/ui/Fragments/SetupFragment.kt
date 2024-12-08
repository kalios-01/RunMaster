package com.kaliostech.runmaster.ui.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kaliostech.runmaster.R
import com.kaliostech.runmaster.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.kaliostech.runmaster.other.Constants.KEY_NAME
import com.kaliostech.runmaster.other.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment :Fragment(R.layout.fragment_setup){

    private lateinit var etName: EditText
    private lateinit var etWeight: EditText
    @Inject
    lateinit var sharedPref: SharedPreferences
    @set:Inject
    var isFirstAppOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvContinue = view.findViewById<View>(R.id.tvContinue)
        etName = view.findViewById(R.id.etName)
        etWeight = view.findViewById(R.id.etWeight)
        if(!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

        tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if(success) {
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if(name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        //val toolbarText = "Let's go, $name!"
        //requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}