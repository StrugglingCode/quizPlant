package com.example.quiz.Model

class Plant(var genus:String, var species:String, var cultivar:String,
           var  common:String, var pictureName:String, var description:String,var difficulty:Int,var id:Int=0) //primary Constructor


{
    constructor():this("","","","","","",0,0)    //this refers to primary constructor
private var _plantName:String?=null             //instance Variable

    var plantName:String?                      //another variable of same name as instance variable

    get() = _plantName

    set(value)
    {
        _plantName = value
    }
}