package com.module.kotlin

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Base64
import android.widget.Toast
import com.module.kotlin.R
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_image_cropper.*
import java.io.ByteArrayOutputStream

class ImageCropperActivity : AppCompatActivity() {
    private var cropImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_cropper)

        btnImageCopper.setOnClickListener {
            CropImage.startPickImageActivity(this)
        }

        btnImageSave.setOnClickListener {
            if(imgCropper.drawable!=null){
                val bitmap = (imgCropper.drawable as BitmapDrawable).bitmap
                val urlimage = saveImageToInternalMemory(bitmap)
                toasttest("Image Save to \"$urlimage\"", this)
            }else{
                toasttest("Bitmap not valid", this)
            }
        }

        btnImageBase64.setOnClickListener {
            if(imgCropper.drawable!=null){
                val bitmap = (imgCropper.drawable as BitmapDrawable).bitmap
                val stringimageconvert = ConverToBase64(bitmap)
                Toast.makeText(this,stringimageconvert,Toast.LENGTH_LONG).show()
            }else{
                toasttest("Bitmap not valid", this)
            }
        }
    }

    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = CropImage.getPickImageResultUri(this, data)
            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                cropImageUri = imageUri
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri)
            }
        }

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                imgCropper.setImageURI(result.uri)
                Toast.makeText(this, "Image updated successfully..!!", Toast.LENGTH_LONG).show()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (cropImageUri != null && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCropImageActivity(cropImageUri!!)
        } else {
            Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Start crop image activity for the given image.
     */
    private fun startCropImageActivity(imageUri: Uri?) {
        CropImage.activity(imageUri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setMultiTouchEnabled(true)
            .start(this)
    }

    private fun ConverToBase64(bmp:Bitmap):String{
        var encodedImage = ""
        val baos = ByteArrayOutputStream()
        try {
            bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos)
            val imageBytes = baos.toByteArray()
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            Toast.makeText(this, "Error convert image", Toast.LENGTH_SHORT).show()
        }
        return encodedImage
    }
}
