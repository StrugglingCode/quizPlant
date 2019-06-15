package com.example.quiz

class Plant(genus:String, species:String, cultivar:String,
            common:String, pictureName:String, description:String,difficulty:Int,id:Int=0) //primary Constructor


{

    constructor():this("","","","","","",0,0) //this refers to primary constructor
private var _plantName:String?=null      //instance Variable

    var plantName:String?               //another variable of same name as instance variable

    get() = _plantName

    set(value)
    {
        _plantName = value
    }
}