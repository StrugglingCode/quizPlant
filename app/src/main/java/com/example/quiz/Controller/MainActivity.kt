package com.example.quiz.Controller

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.quiz.Model.Plant
import com.example.quiz.Model.downloadingObject
import com.example.quiz.Model.parsePlantUtility
import com.example.quiz.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correctIndex:Int=0
    var correctPlant:Plant?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (checkInternetConnection()) {
            val innerClassObject = downloadingPlantTask()
            innerClassObject.execute()
        }

        button1.setOnClickListener()
        {
            rightandqrong(0)
        }

        button2.setOnClickListener()
        {
            rightandqrong(1)
        }

        button3.setOnClickListener()
        {
            rightandqrong(2)
        }

        button4.setOnClickListener()
        {
            rightandqrong(3)
        }
    }


    inner class downloadingPlantTask:AsyncTask<String,Int,List<Plant>>()   //AsyncTask is doing heavy process in the background
    {
        override fun doInBackground(vararg params: String?): List<Plant>?  //vararg kind of array    ?MEANS OPTIONAL
        {
//            val downloadingObject: downloadingObject =
//                downloadingObject()  //created object for downloadingObject class
//            val jsonData = downloadingObject.downloadJasonDataFrom("https://www.plantplaces.com/perl/mobile/flashcard.pl")
//            Log.i("JSON",jsonData)

            val parsePlant = parsePlantUtility()
                return parsePlant.parsePlantObjectsFromJsonData()

        }

        override fun onPostExecute(result: List<Plant>?)

        {
             var noOfPlants = result?.size?:0
            if(noOfPlants>0)
            {
                var Button1:Int = (Math.random()*result!!.size).toInt()     //Math.random generates no b/w 0&1
                var Button2:Int = (Math.random()*result!!.size).toInt()
                var Button3:Int = (Math.random()*result!!.size).toInt()
                var Button4:Int = (Math.random()*result!!.size).toInt()

                var allRandomPlants = ArrayList<Plant>()
                allRandomPlants.add(result.get(Button1))
                allRandomPlants.add(result.get(Button2))
                allRandomPlants.add(result.get(Button3))
                allRandomPlants.add(result.get(Button4))

                button1.text = result.get(Button1).toString()
                button2.text = result.get(Button2).toString()
                button3.text = result.get(Button3).toString()
                button4.text = result.get(Button4).toString()

                correctIndex = (Math.random()*allRandomPlants.size).toInt()
                correctPlant = allRandomPlants.get(correctIndex)


            }
        }
    }

    //check for internetconnection

    private fun checkInternetConnection():Boolean
    {
        val connectivityManager = this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val isConnected = networkInfo!=null && networkInfo.isConnected

        if(isConnected)
        {
            return true
        }
        else
            createAlert()
            return false

    }

    private fun createAlert()
    {
        var alerDialog:AlertDialog = AlertDialog.Builder(this).create()
        alerDialog.setTitle("Network Error")
        alerDialog.setMessage("Check Internet Connection")

        alerDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok",
            { dialog: DialogInterface?, which: Int ->
                startActivity(Intent(Settings.ACTION_SETTINGS))
            })

        alerDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel",{
            dialog: DialogInterface?, which: Int ->
            Toast.makeText(this,"You must be connected to internet",Toast.LENGTH_SHORT).show()

            finish()
        })
        alerDialog.show()
    }

    //specify right and wrong answer

    private fun rightandqrong(guess:Int)
    {
        when(correctIndex)
        {
            0 -> button1.setBackgroundColor(Color.GREEN)
            1 -> button2.setBackgroundColor(Color.GREEN)
            2 -> button3.setBackgroundColor(Color.GREEN)
            3 -> button4.setBackgroundColor(Color.GREEN)

        }


    }

    inner class image: AsyncTask<String,Int,Bitmap?>()
    {
        override fun doInBackground(vararg params: String?): Bitmap?
        {

        return null
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
        }
    }


}

