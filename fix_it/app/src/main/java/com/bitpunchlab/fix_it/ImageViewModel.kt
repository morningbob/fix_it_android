package com.bitpunchlab.fix_it

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

	private var _imageToEdit = MutableLiveData<Bitmap>()

	val imageToEdit: LiveData<Bitmap>
		get() = _imageToEdit


}