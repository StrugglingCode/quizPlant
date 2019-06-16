package com.example.quiz.Model

import org.json.JSONArray
import org.json.JSONObject

class parsePlantUtility {

    fun parsePlantObjectsFromJsonData():List<Plant>?
    //must return List of plant object
    {
        var allPlantObjects:ArrayList<Plant> = ArrayList()
        //created variable which holds all plant objects as in ArrayList
        //ArrayList Implements List Interface

        var downloadingObject = downloadingObject()
        //object that can connect to the internet(downloadingObject() class already created)

        var topLevelJsonData = downloadingObject.downloadJasonDataFrom("https://www.plantplaces.com/perl/mobile/flashcard.p")
        //jason data is being stored in topLevelJasonData
        // data that is retrieved is just raw text

        var topLevelJasonObject:JSONObject = JSONObject(topLevelJsonData)
        //jason data is being converted into readable form(being converted into JasonObject)


        var plantObjectsArray:JSONArray = topLevelJasonObject.getJSONArray("values")
        //Value is key of json api
        //value holds the Array in JSON api
        var index:Int = 0

        while (index<plantObjectsArray.length())
        {
                var plantObject:Plant = Plant()
                var jsonObject = plantObjectsArray.getJSONObject(index)

            /*  genus:String, species:String, cultivar:String,
            common:String, pictureName:String, description:String,difficulty:Int,id:Int=0  */

            with(jsonObject) {
                plantObject.genus = getString("genus")
                plantObject.species = getString("species")
                plantObject.cultivar = getString("cultivar")
                plantObject.common = getString("common")
                plantObject.pictureName = getString("picture_name")
                plantObject.description = getString("description")
                plantObject.difficulty = getInt("difficulty")
                plantObject.id = getInt("id")

            }
            allPlantObjects.add(plantObject)
            index++

        }

        return allPlantObjects


    }
}