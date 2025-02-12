package com.example.mvapp.sign

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvapp.R
import com.example.mvapp.home.HomeAct
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_upload_photo.*
import java.util.*

class UploadPhotoAct : AppCompatActivity(),PermissionListener {

    val REQUEST_IMAGE_CAPTURE=1;
    var statusAdd:Boolean=false;
    lateinit var filePath:Uri;

    lateinit var storage:FirebaseStorage;
    lateinit var storageReference: StorageReference;
    lateinit var preferences: com.example.mvapp.sign.utils.Preferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_photo)

        preferences = com.example.mvapp.sign.utils.Preferences(this)
        bttnSignin.visibility = View.INVISIBLE;
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        txtSaldo.text=intent.getStringExtra("nama");


        bttnAdd.setOnClickListener({
            if(statusAdd)
            {
                statusAdd=false;
                bttnSignin.visibility=View.INVISIBLE;
                bttnAdd.setImageResource(R.drawable.add);
                imgFoto.setImageResource(R.drawable.icon_nopic);
            }
            else
            {
//                Dexter.withActivity(this)
//                    .withPermission(android.Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()

                ImagePicker.with(this)
                    .cameraOnly() //User can only capture image using Camera
//                    .galleryOnly()	//User can only galeery
                    .start()
            }
        })

        bttnBack.setOnClickListener({
            val intent = Intent(this,SignUpAct::class.java);
            startActivity(intent);
        })

        bttnSignin.setOnClickListener({
            if (filePath != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preferences.setValues("url", it.toString())

                            Log.v("tamvan", "url"+it.toString())

                            finishAffinity()
                            val intent = Intent(this,
                                HomeAct::class.java)
                            startActivity(intent)
                        }



                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                            .totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                    }
            }
        })

        bttnSignup.setOnClickListener({
            finishAffinity();
            val intent = Intent(this,HomeAct::class.java);
            startActivity(intent);
        })
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
//            }
//        }

        ImagePicker.with(this)
            .cameraOnly()	//User can only gallery
            .start()
    }

    override fun onPermissionRationaleShouldBeShown(permission: com.karumi.dexter.listener.PermissionRequest?,
                                                    token: PermissionToken?)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,"Anda Tidak Bisa Menambahkan Foto Profile",Toast.LENGTH_LONG).show();
    }

    override fun onBackPressed() {
        Toast.makeText(this,"Tergesah ? Klik tombol Upload Nanti Aja",Toast.LENGTH_LONG).show();
    }


//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode==REQUEST_IMAGE_CAPTURE)
//        {
//            if(data!=null)
//            {
//                var bitmap = data?.extras?.get("data") as Bitmap
//                statusAdd=true
//
//                filePath=data.getData()!!
//                Glide.with(this)
//                    .load(bitmap)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(imgFoto)
//
//                bttnSignin.visibility = View.VISIBLE
//                bttnAdd.setImageResource(R.drawable.trash)
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            statusAdd = true
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(imgFoto)

            bttnSignin.visibility = View.VISIBLE
            bttnAdd.setImageResource(R.drawable.trash)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
