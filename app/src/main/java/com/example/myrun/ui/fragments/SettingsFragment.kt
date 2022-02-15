package com.example.myrun.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myrun.R
import com.example.myrun.other.Constants
import com.example.myrun.other.Constants.KEY_NAME
import com.example.myrun.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_setup.etName
import kotlinx.android.synthetic.main.fragment_setup.etWeight
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPrefs()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if(success) {
                Snackbar.make(view, "Saved changes", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, "Please fill out all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadFieldsFromSharedPrefs() {
        val name = sharedPref.getString(KEY_NAME, "") ?: ""
        val weight = sharedPref.getFloat(KEY_WEIGHT, 80f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if(name.isEmpty() || weight.isEmpty()){
            return false
        }

        sharedPref.edit()
                .putString(Constants.KEY_NAME, name)
                .putFloat(Constants.KEY_WEIGHT, weight.toFloat())
                .apply()

        return true
    }
}