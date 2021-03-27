package com.bitpunchlab.fix_it

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

	var imageToEdit = MutableLiveData<Bitmap>()
	var photoUri = MutableLiveData<Uri>()

	private var _imageDrawable = MutableLiveData<Drawable>()

	val imageDrawable: LiveData<Drawable>
		get() = _imageDrawable

	fun updateImage() {
		//_imageDrawable.value = BitmapDrawable(android.content.res.Resources, imageToEdit.value)
	}




}