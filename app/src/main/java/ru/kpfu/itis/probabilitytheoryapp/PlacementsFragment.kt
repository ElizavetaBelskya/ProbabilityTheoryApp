package ru.kpfu.itis.probabilitytheoryapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.probabilitytheoryapp.databinding.FragmentPlacementsBinding

class PlacementsFragment : Fragment() {
    private lateinit var binding: FragmentPlacementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlacementsBinding.inflate(inflater, container, false)
        with (binding) {
            rgPlace.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    rb1.id -> {
                        ivPlaceWithout.visibility = View.INVISIBLE
                        ivPlaceWith.visibility = View.VISIBLE
                        button.visibility = View.VISIBLE
                        tvN.visibility = View.VISIBLE
                        tvM.visibility = View.VISIBLE
                        tilN.visibility = View.VISIBLE
                        tilM.visibility = View.VISIBLE
                        tvM.text = "m = "
                    }

                    rb2.id -> {
                        ivPlaceWith.visibility = View.INVISIBLE
                        ivPlaceWithout.visibility = View.VISIBLE
                        button.visibility = View.VISIBLE
                        tvN.visibility = View.VISIBLE
                        tvM.visibility = View.VISIBLE
                        tilN.visibility = View.VISIBLE
                        tilM.visibility = View.VISIBLE
                        tvM.text = "k = "
                    }

                    -1 -> {
                        ivPlaceWith.visibility = View.INVISIBLE
                        ivPlaceWithout.visibility = View.INVISIBLE
                        button.visibility = View.INVISIBLE
                        tvN.visibility = View.INVISIBLE
                        tvM.visibility = View.INVISIBLE
                        tilN.visibility = View.INVISIBLE
                        tilM.visibility = View.INVISIBLE
                    }
                }

            }

            button.setOnClickListener {
                if (etN.text.isNullOrEmpty() or etM.text.isNullOrEmpty()) {
                    createDialog("Внимание!", "Необходимые данные не введены")
                } else if (rgPlace.checkedRadioButtonId == rb1.id) {
                    var n = etN.text?.toString()?.toInt()?:0
                    var m = etM.text?.toString()?.toInt()?:0
                    if (n > 10000 || m > 10000) {
                        createDialog("Внимание!", "Слишком большие числа")
                    } else if ((n >= 0) && (m >= 0)) {
                        var result = Functions.placementWithRepetitions(n, m)
                        createDialog("Результат", result.toString())
                    } else {
                        createDialog("Внимание!", "Данные не корректны")
                    }

                } else if (rgPlace.checkedRadioButtonId == rb2.id) {
                    var n = etN.text?.toString()?.toInt()?:0
                    var m = etM.text?.toString()?.toInt()?:0
                    if (n > 10000 || m > 10000) {
                        createDialog("Внимание!", "Слишком большие числа")
                    } else if ((n >= 0) && (m >= 0) && (n >= m)) {
                        var result = Functions.placementWithoutRepetitions(n, m)
                        createDialog("Результат", result.toString())
                    } else {
                        createDialog("Внимание!", "Данные не корректны")
                    }
                }

            }
        }
        return binding.root
    }


    fun createDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ок") { dialog, which ->

        }
        builder.create().show()
    }



}
