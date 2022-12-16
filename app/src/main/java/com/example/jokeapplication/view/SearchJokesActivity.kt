package com.example.jokeapplication.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.jokeapplication.R
import com.example.jokeapplication.model.JokesRequest
import com.example.jokeapplication.model.Util.KEY_JOKE_REQUEST
import com.example.jokeapplication.model.Util.categoryStrArray
import java.util.*


class SearchJokesActivity : AppCompatActivity() {
    private var rbCustom: RadioButton? = null
    private var rbAny: RadioButton? = null
    private var checkboxSingle: CheckBox? = null
    private var checkboxTwopart: CheckBox? = null
    private var selectedCatergories: MutableList<String>? = null
    private var spinnerAmount: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
    }

    private fun init() {
        rbAny = findViewById(R.id.RBAny)
        rbAny?.isChecked = true
        rbCustom = findViewById(R.id.RBCustom)
        checkboxSingle = findViewById(R.id.checkboxSingle)
        checkboxTwopart = findViewById(R.id.checkboxTwopart)
        spinnerAmount = findViewById(R.id.spinnerAmount)
        setCustomCategoryDialog()
        setSearchButtonClickListener()
        setSpinnerAdapter()
    }

    private fun setSpinnerAdapter() {
        val arrayAmounts: MutableList<Int> = ArrayList()
        for (i in 1..10) {
            arrayAmounts.add(i)
        }
        val amountSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayAmounts)
        amountSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAmount?.adapter = amountSpinnerAdapter
    }

    private fun setCustomCategoryDialog() {
        val checkedItems = BooleanArray(categoryStrArray.size)
        selectedCatergories = ArrayList<String>(categoryStrArray.size)

        rbAny?.setOnClickListener{
            selectedCatergories?.clear()
        }

        rbCustom?.setOnClickListener {
            val builder = AlertDialog.Builder(this@SearchJokesActivity)
            builder.setTitle("Choose Category")
            builder.setMultiChoiceItems(categoryStrArray, checkedItems) { _: DialogInterface?, which: Int, isChecked: Boolean ->
                checkedItems[which] = isChecked
            }
            builder.setCancelable(false)
            builder.setPositiveButton("Done") { _: DialogInterface?, _: Int ->
                selectedCatergories?.clear()
                for ((i, isChecked) in checkedItems.withIndex()) {
                    if (isChecked) selectedCatergories?.add(categoryStrArray[i])
                }
                if (selectedCatergories?.size==0) {
                    rbAny?.isChecked = true
                    Arrays.fill(checkedItems, false)
                }
            }
            builder.setNegativeButton("CANCEL") { _: DialogInterface?, _: Int ->
                if (selectedCatergories?.size ==0) {
                    rbAny?.isChecked = true
                    Arrays.fill(checkedItems, false)
                } else {
                    for (i in categoryStrArray.indices) {
                        if (!(selectedCatergories?.contains(categoryStrArray[i]))!! && checkedItems[i]) {
                            checkedItems[i] = false
                        }
                        if (selectedCatergories?.contains(categoryStrArray[i])!! && !checkedItems[i]) {
                            checkedItems[i] = true
                        }
                    }
                }
            }
            builder.setNeutralButton("CLEAR ALL") { _: DialogInterface?, _: Int ->
                Arrays.fill(checkedItems, false)
                selectedCatergories?.clear()
                rbAny?.isChecked = true
            }
            builder.create()
            val customCategoryDialog = builder.create()
            customCategoryDialog.show()
        }
    }

    private fun setSearchButtonClickListener() {
        val searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener { v: View? ->
            val intent = Intent(this@SearchJokesActivity, JokesListActivity::class.java)
            intent.putExtra(KEY_JOKE_REQUEST, getJokeRequestData())
            startActivity(intent)
        }
    }

    private fun getJokeRequestData(): JokesRequest {
        val jokesRequest = JokesRequest()

        if (selectedCatergories != null && selectedCatergories?.size!! > 0) {
            jokesRequest.category = java.lang.String.join(",", selectedCatergories)
        }
        if (!(checkboxSingle!!.isChecked && checkboxTwopart!!.isChecked)) {
            if (checkboxSingle!!.isChecked)
                jokesRequest.jokeType = resources.getString(R.string.single)
            if (checkboxTwopart!!.isChecked)
                jokesRequest.jokeType = resources.getString(R.string.twopart)
        }
        if (spinnerAmount?.selectedItem != null
            && spinnerAmount?.selectedItem.toString().toInt() > 1)
                jokesRequest.amount = spinnerAmount?.selectedItem.toString()
        return jokesRequest
    }


}