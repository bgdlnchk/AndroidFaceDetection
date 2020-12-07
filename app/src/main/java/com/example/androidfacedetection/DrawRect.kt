package com.example.androidfacedetection

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.google.mlkit.vision.face.Face

class DrawRect(context: Context, var faceObject: MutableList<Face>) : View(context) {

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val pen = Paint()
        for (item in faceObject){
            pen.color = Color.RED
            pen.strokeWidth = 8F
            pen.style = Paint.Style.STROKE
            val box = item.boundingBox
            canvas?.drawRect(box, pen)
        }
    }

}
