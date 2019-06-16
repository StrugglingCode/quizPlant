package com.example.quiz.Model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class downloadingObject {

@Throws(IOException::class)

fun downloadJasonDataFrom(link:String):String
{
    val sBuilder :StringBuilder= StringBuilder()
    //Constructs a string builder with no characters in it and an initial capacity of 16 characters.
    //effectively converts a given datum to a string and then appends or inserts the characters of that string to the string builder

    val url = URL(link)

    val urlConnection = url.openConnection() as HttpURLConnection   //connection is created

    try {
        val bufferedInputStream:BufferedInputStream = BufferedInputStream(urlConnection.inputStream)
        //creates internal buffer array   //helps us to get data from the internet
        val bufferedReader:BufferedReader = BufferedReader(InputStreamReader(bufferedInputStream))
        //inputStreamReader reads bytes and converts it into chars


        var inputLineString:String?
        //temporary string to hold each line read from bufferReader
        inputLineString = bufferedReader.readLine()

        while(inputLineString!=null)
        {
            sBuilder.append(inputLineString)
            inputLineString=bufferedReader.readLine()
        }
    }
    finally {
        //executed always
        urlConnection.disconnect()

    }

    return sBuilder.toString()


}
//function to download image from Internet
    fun downloadPlantImage(pictureName:String?):Bitmap?
    {
        var bitMap:Bitmap? = null
        val pictureLink:String= plantPlaces+"/photos/$pictureName"
        val pictureUrl = URL(pictureLink)
        val inputStream = pictureUrl.openConnection().getInputStream()

        if(inputStream!=null)
        {
            bitMap = BitmapFactory.decodeStream(inputStream)
        }

return bitMap

    }

    companion object
    {
        val plantPlaces = "www.plantplaces.com"
    }//has access to private members

}