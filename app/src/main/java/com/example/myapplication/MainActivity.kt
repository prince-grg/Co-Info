package com.example.myapplication

import android.app.DatePickerDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    var url: String =
        "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=335803&date=31-03-2021"
    private var datee: String = "a"
    var pinn: String = "b"
    lateinit var progressdailog: ProgressDialog
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        progressdailog = ProgressDialog(this)

        progressdailog.setTitle("Fetching data..")
        progressdailog.setMessage("Please wait ")

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyler_view)
        recyclerView.layoutManager = linearLayoutManager

        binding.selDate.setOnClickListener {


            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    datee =
                        dayOfMonth.toString() + "-" + "${monthOfYear + 1}" + "-" + year.toString()
                    binding.selDate.text = datee


                },
                year,
                month,
                day
            )

            dpd.show()
            //  requestapi()

        }
        binding.getBtn.setOnClickListener {


            pinn = binding.enterPin.text.toString()
            if (pinn == "b" || datee == "a") {
                Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show()
            } else {
                progressdailog.show()
                requestapi()
            }


        }


    }

    private fun requestapi() {
        Log.d("myApp", "ff")
        val gh = SetuService.setuInterface.getlist(pinn, datee)
        gh.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                Log.d("myApp", "e")
                var mydata: MyData? = response.body()
                //      Log.d("myApp",mydata.toString())
                if (mydata != null) {
                    Log.d("myApp", "not null" + mydata.sessions.size)
                    Log.d("myApp", mydata.toString())
                    adapter = MyAdapter(applicationContext, mydata.sessions)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "No data Found", Toast.LENGTH_SHORT).show()
                }



                progressdailog.dismiss()
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.d("myApp", "something went Wrong", t)
                progressdailog.dismiss()
                Toast.makeText(this@MainActivity, "Something went wrong !", Toast.LENGTH_SHORT)
                    .show()
            }


        }
        )


    }


}
