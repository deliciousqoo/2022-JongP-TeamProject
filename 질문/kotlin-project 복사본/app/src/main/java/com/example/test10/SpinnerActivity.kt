package com.example.test10

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.test10.R

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search)


        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        val spinner5 = findViewById<Spinner>(R.id.spinner5)


        val arrayAdapter1 = ArrayAdapter.createFromResource(
            this, R.array.spinner_main, android.R.layout.simple_spinner_dropdown_item
        )

        spinner1.adapter = arrayAdapter1
        spinner2.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinnerEmpty, android.R.layout.simple_spinner_dropdown_item
        )
        spinner3.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinnerEmpty, android.R.layout.simple_spinner_dropdown_item
        )
        spinner4.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinnerEmpty, android.R.layout.simple_spinner_dropdown_item
        )
        spinner5.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinnerEmpty, android.R.layout.simple_spinner_dropdown_item
        )

        spinner1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, position: Int, p3: Long
                ) {
                    var arrayAdapter2 = ArrayAdapter.createFromResource(
                        this@SpinnerActivity,
                        R.array.spinnerEmpty,
                        android.R.layout.simple_spinner_dropdown_item
                    )
                    when (position) {
                        1 -> {
                            arrayAdapter2 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_manufacturing,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        2 -> {
                            arrayAdapter2 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_agriculture_forestry_fishing,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        3 -> {
                            arrayAdapter2 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_mine,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        4 -> {
                            arrayAdapter2 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        else -> {}
                    }
                    spinner2.adapter = arrayAdapter2
                }


                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }
            }

        spinner2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, position: Int, p3: Long
                ) {
                    var arrayAdapter3 = ArrayAdapter.createFromResource(
                        this@SpinnerActivity,
                        R.array.spinnerEmpty,
                        android.R.layout.simple_spinner_dropdown_item
                    )
                    when (position) {
                        0 -> {
                            arrayAdapter3 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        1 -> {
                            arrayAdapter3 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        2 -> {
                            arrayAdapter3 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_agriculture_forestry_fishing,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        3 -> {
                            arrayAdapter3 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_mine,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        4 -> {
                            arrayAdapter3 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        else -> {}
                    }
                    spinner3.adapter = arrayAdapter3
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }

            }

        spinner3.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, position: Int, p3: Long
                ) {
                    var arrayAdapter4 = ArrayAdapter.createFromResource(
                        this@SpinnerActivity,
                        R.array.spinnerEmpty,
                        android.R.layout.simple_spinner_dropdown_item
                    )
                    when (position) {
                        0 -> {
                            arrayAdapter4 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        1 -> {
                            arrayAdapter4 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        2 -> {
                            arrayAdapter4 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_agriculture_forestry_fishing,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        3 -> {
                            arrayAdapter4 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_mine,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        4 -> {
                            arrayAdapter4 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        else -> {}
                    }
                    spinner4.adapter = arrayAdapter4
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }

            }
        spinner4.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?, position: Int, p3: Long
                ) {
                    var arrayAdapter5 = ArrayAdapter.createFromResource(
                        this@SpinnerActivity,
                        R.array.spinnerEmpty,
                        android.R.layout.simple_spinner_dropdown_item
                    )
                    when (position) {
                        0 -> {
                            arrayAdapter5 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        1 -> {
                            arrayAdapter5 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        2 -> {
                            arrayAdapter5 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_agriculture_forestry_fishing,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        3 -> {
                            arrayAdapter5 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_mine,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        4 -> {
                            arrayAdapter5 = ArrayAdapter.createFromResource(
                                this@SpinnerActivity,
                                R.array.spinner_business,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                        else -> {}
                    }
                    spinner5.adapter = arrayAdapter5
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }

            }
    }
}