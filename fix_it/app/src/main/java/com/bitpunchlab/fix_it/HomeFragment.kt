package com.bitpunchlab.fix_it

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bitpunchlab.fix_it.databinding.FragmentEditBinding
import com.bitpunchlab.fix_it.databinding.FragmentHomeBinding
import java.io.IOException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

private const val PICK_PHOTO_CODE = 1046

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel: ImageViewModel by activityViewModels()

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_home, container, false)
		binding.lifecycleOwner = this
		binding.buttonLoad.setOnClickListener {
			onPickPhoto(it)
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		view.findViewById<Button>(R.id.button_load).setOnClickListener {
			findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
		}
	}

	fun onPickPhoto(view: View) {
		val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
		// If you call startActivityForResult() using an intent that no app can handle, your app will crash.
		// So as long as the result is not null, it's safe to use the intent.
		if (context?.let { intent.resolveActivity(it.packageManager) } != null) {
			startActivityForResult(intent, PICK_PHOTO_CODE)
		}
	}

	fun loadFromUri(photoUri: Uri) : Bitmap? {
		var image : Bitmap? = null
		try {
			if (Build.VERSION.SDK_INT > 27) {
				val source = context?.let { ImageDecoder.createSource(it.contentResolver, photoUri) }
				image = source?.let { ImageDecoder.decodeBitmap(it) }
			} else {
				image = MediaStore.Images.Media.getBitmap(context?.contentResolver, photoUri)
			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return image
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if ((data != null) && requestCode == PICK_PHOTO_CODE) {
			val photoUri = data.data

			viewModel.imageToEdit.value = photoUri?.let { loadFromUri(it) }
			viewModel.photoUri.value = photoUri
			//ivPreview =
			// ivPreview.setImageBitmap(selectedImage)
		}
	}
}