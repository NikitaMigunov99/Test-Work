package com.example.testwork.data

import android.content.Context
import com.example.testwork.R
import java.util.*

class DataSource(context: Context) {

    private var carryover= false
    private val colorWite: Int
    private val colorGray: Int

    init {
        colorWite=context.resources.getColor(R.color.colorWhite)
        colorGray=context.resources.getColor(R.color.colorGray)
    }

    fun getFibonacciNumber(startPosition: Int, endPosition: Int): MutableList<Number> {
        val numbers= mutableListOf<Number>()
        var start= startPosition
        var a: Long
        var b: Long

        if( start==0){
            numbers.add(getNumber(0,0))
            start+=1
        }

        if( start==1){
            numbers.add(getNumber(1,1))
            start+=1
        }

        if (start>1)
            for (i in start..endPosition){
                var k=i
                a=0
                b=1
                while (k!=0){
                    a += b
                    b=a-b
                    k -= 1
                }
                numbers.add(getNumber(i,a))
            }

        return numbers
    }

    var j=0

    fun getPrimeNumber(startPosition: Int, endPosition: Int): MutableList<Number> {
        val numbers= mutableListOf<Number>()
        val prime = BooleanArray(endPosition + 1-startPosition)
        Arrays.fill(prime, true)

        var low = startPosition
        val high = endPosition

        while (low < high) {
            var flag = false
            for (i in 2..low / 2) {

                if (low % i == 0) {
                    flag = true
                    break
                }
            }
            if (!flag && low != 0 && low != 1){
                numbers.add(getNumber(j,low.toLong()))
                j++
            }
            ++low
        }

        return numbers
    }


    private fun getNumber(position: Int, a: Long): Number{
        if(position==0)
            return Number(a, colorWite)

        else if(position==1){
            carryover=true
            return Number(a, colorGray)
        }

        else if(carryover && position%2==0)
            return Number(a, colorGray)

        else if(carryover && position%2!=0){
            carryover= false
            return Number(a, colorWite)
        }

        else if(!carryover && position%2==0)
            return Number(a, colorWite)

        else {
            carryover=true
            return Number(a, colorGray)
        }
    }

}