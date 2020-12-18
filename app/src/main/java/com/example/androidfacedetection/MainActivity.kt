package com.example.androidfacedetection

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions


class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = FaceDetectorOptions.Builder()
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .build()

        val testImage = findViewById<ImageView>(R.id.ImageView)

        val bMap = BitmapFactory.decodeResource(resources, R.drawable.image_view)
        val mutableBitmap: Bitmap = bMap.copy(Bitmap.Config.ARGB_8888, true)
        val image = InputImage.fromBitmap(bMap, 0)

        val detector = FaceDetection.getClient(options)
        detector.process(image)
                .addOnSuccessListener {faces ->
                    val drawingView = DrawRect(applicationContext, faces)
                    drawingView.draw(Canvas(mutableBitmap))
                    runOnUiThread { testImage.setImageBitmap(mutableBitmap) }
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext,"Something went wrong!", Toast.LENGTH_SHORT).show();
                }
    }
}
