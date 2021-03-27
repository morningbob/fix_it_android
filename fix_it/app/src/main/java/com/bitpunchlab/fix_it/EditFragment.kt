package com.bitpunchlab.fix_it

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
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
import androidx.navigation.fragment.findNavController
import com.bitpunchlab.fix_it.databinding.FragmentEditBinding
import com.bitpunchlab.fix_it.databinding.FragmentHomeBinding
import java.io.IOException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

private const val PICK_PHOTO_CODE = 1046

class EditFragment : Fragment() {

	private lateinit var binding: FragmentEditBinding
	private val viewModel: ImageViewModel by activityViewModels()


	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_edit, container, false)
		binding.lifecycleOwner = this

		binding.imageToEdit.setImageBitmap(viewModel.imageToEdit.value)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		view.findViewById<Button>(R.id.button_home).setOnClickListener {
			findNavController().navigate(R.id.action_EditFragment_to_HomeFragment)
		}
	}


}