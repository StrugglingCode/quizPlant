package com.example.quiz

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
private var cameraButton:Button?=null
    private var galleryButton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
                cameraButton = findViewById<Button>(R.id.buttonC)
                galleryButton = findViewById(R.id.button9)

        cameraButton?.setOnClickListener(View.OnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,1)
        })

        }
/*Toast.makeText(this,"Override onCreate Method Called",Toast.LENGTH_SHORT).show()

        val myPlant:Plant=Plant("","","","","","",0,0)    //calling constructor


      /*  Plant("Koelreuteria","paniculata","","Golden Rain Tree",
            "Koelreuteria_paniculata_branch.JPG",
            "Branch of Koelreuteria paniculata",3,24)*/

        myPlant.plantName="Wadas Memory Magnolia"
              var a =         myPlant.plantName*/

      /*  var flower = Plant()  //object1
        var Tree = Plant()    // object 2

        var collectionOfPlants:ArrayList<Plant> = ArrayList()

        collectionOfPlants.add(flower)
        Toast.makeText(this,"Flower object added",Toast.LENGTH_SHORT).show()
        collectionOfPlants.add(Tree)
        Toast.makeText(this,"Object 2 added",Toast.LENGTH_SHORT).show()*/






    inner class downloadingPlantTask:AsyncTask<String,Int,List<Plant>>()   //AsyncTask is doing heavy process in the background
    {
        override fun doInBackground(vararg params: String?): List<Plant>?  //vararg kind of array    ?MEANS OPTIONAL
        {
                     return null
        }

        override fun onPostExecute(result: List<Plant>?)

        {

        }
    }
}
