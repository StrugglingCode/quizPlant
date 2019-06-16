package com.example.quiz.Controller

import android.content.DialogInterface
import android.content.Intent
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

class MainActivity : AppCompatActivity() {
private var cameraButton:Button?=null
    private var galleryButton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (checkInternetConnection()) {
            val innerClassObject = downloadingPlantTask()
            innerClassObject.execute()
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
}
