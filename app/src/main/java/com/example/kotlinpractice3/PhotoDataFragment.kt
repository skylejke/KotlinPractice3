package com.example.kotlinpractice3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class PhotoDataFragment : Fragment() {
    private lateinit var goToCameraButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotoDataListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_data, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        goToCameraButton = view.findViewById(R.id.goToCameraButton)

        // Чтение данных из файла и создание списка PhotoData
        val path = "/storage/emulated/0/Android/media/com.example.kotlinpractice3/photos"
        val file = File(path, "date.txt")
        val photoDataList = readDataFromFile(file)
        // Генерация имен фотографий в зависимости от количества данных
        for ((index, photoData) in photoDataList.withIndex()) {
            photoData.photoName = "photo_${index + 1}"
        }

        // Инициализация адаптера и установка его в RecyclerView
        adapter = PhotoDataListAdapter(photoDataList)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager


        goToCameraButton = view.findViewById(R.id.goToCameraButton)
        goToCameraButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun readDataFromFile(file: File): List<PhotoDataListAdapter.PhotoData> {
        val data = mutableListOf<PhotoDataListAdapter.PhotoData>()
        if (file.exists()) {
            try {
                val reader = BufferedReader(FileReader(file))
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    // Преобразование строки в объект PhotoData и добавление его в список
                    data.add(
                        PhotoDataListAdapter.PhotoData(
                            photoName = "",
                            photoDateTime = line ?: ""
                        )
                    )
                }
                reader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        data.removeAt(0)
        return data
    }
}