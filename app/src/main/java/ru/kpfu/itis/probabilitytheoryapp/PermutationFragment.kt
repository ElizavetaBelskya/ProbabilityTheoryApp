package ru.kpfu.itis.probabilitytheoryapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.probabilitytheoryapp.databinding.FragmentPermutationBinding


class PermutationFragment : Fragment() {

    private lateinit var binding: FragmentPermutationBinding

    private var list = mutableListOf<Int>();
    private var c = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPermutationBinding.inflate(inflater, container, false)
        var k = -1;
        var rep: Boolean? = null;
        with(binding) {
            rgPlace.setOnCheckedChangeListener{ group, checkedId ->
                when (checkedId) {

                    rb1.id -> {
                        ivPermWithout.visibility = View.INVISIBLE
                        ivPermWith.visibility = View.VISIBLE
                        button.visibility = View.VISIBLE
                        tvK.visibility = View.VISIBLE
                        tilK.visibility = View.VISIBLE
                        btnK.visibility = View.VISIBLE
                        tvN.visibility = View.INVISIBLE
                        tilN.visibility = View.INVISIBLE
                        rep = true
                    }

                    rb2.id -> {
                        ivPermWithout.visibility = View.VISIBLE
                        tvN.visibility = View.VISIBLE
                        tilN.visibility = View.VISIBLE
                        btnK.visibility = View.INVISIBLE
                        btnAddN.visibility = View.INVISIBLE
                        button.visibility = View.VISIBLE
                        tvK.visibility = View.INVISIBLE
                        tilK.visibility = View.INVISIBLE
                        rep = false
                    }


                    -1 -> {
                        tvK.visibility = View.INVISIBLE
                        tilK.visibility = View.INVISIBLE
                        tvN.visibility = View.INVISIBLE
                        tilN.visibility = View.INVISIBLE
                        ivPermWith.visibility = View.INVISIBLE
                        ivPermWithout.visibility = View.INVISIBLE
                        button.visibility = View.INVISIBLE
                        btnK.visibility = View.INVISIBLE
                        btnAddN.visibility = View.INVISIBLE
                        btnDeleteN.visibility = View.INVISIBLE
                        tvList.visibility = View.INVISIBLE
                        rep = null
                    }

                }

                btnK.setOnClickListener {
                    if (etK.text.isNotEmpty()) {
                        k = etK.text.toString().toInt()
                        if (k < 1) {
                            createDialog("????????????????!", "???????????? ???? ??????????????????")
                        } else if (k > 20) {
                            createDialog("????????????????!", "?????????????? ?????????????? k")
                        } else {
                            tvN.visibility = View.VISIBLE
                            tilN.visibility = View.VISIBLE
                            btnAddN.visibility = View.VISIBLE
                            tvList.visibility = View.VISIBLE
                            btnDeleteN.visibility = View.VISIBLE
                            list = mutableListOf<Int>()
                            c = 0
                            if (k%10 == 1) {
                                tvList.text = "?????????????? $k ??????????:"
                            } else if (k < 5) {
                                tvList.text = "?????????????? $k ??????????:"
                            } else {
                                tvList.text = "?????????????? $k ??????????:"
                            }
                        }
                    }
                }

                btnAddN.setOnClickListener {
                    if (etN.text.isNotEmpty() and (c <= k)) {
                        list.add(etN.text.toString().toInt())
                        tvList.text = list.toString()
                        etN.setText("")
                        c++
                    }
                }

                btnDeleteN.setOnClickListener {
                    if (list.isNotEmpty()) {
                        list.removeLast()
                        tvList.text = list.toString()
                        c--
                    }
                }

                button.setOnClickListener {
                   if (rgPlace.checkedRadioButtonId == rb1.id) {
                           if (list.size != k) {
                               createDialog("????????????????!", "???????????????????? ?????????????????? ?????????? ???? ?????????????????????????? ?????????? K")
                           } else {
                               var flag = true
                               list.forEach{
                                   if (it < 0) {
                                       flag = false
                                       createDialog("????????????????!", "???????????? ???? ??????????????????")
                                   } else if (it > 10000) {
                                       flag = false
                                       createDialog("????????????????!", "?????????????? ?????????????? ??????????")
                                   }
                               }
                               if (flag) {
                                   createDialog("??????????????????", Functions.permutationsWithRepetitions(list).toString())
                               } else {
                                   createDialog("????????????????!", "???????????? ???? ??????????????????")
                               }
                           }
                   } else if (rgPlace.checkedRadioButtonId == rb2.id) {
                       if (etN.text.isNullOrEmpty()) {
                           createDialog("????????????????!", "?????????????????????? ???????????? ???? ??????????????")
                       } else {
                           var n = etN.text?.toString()?.toInt()?:0
                           if (n > 10000) {
                               createDialog("????????????????!", "?????????????? ?????????????? ??????????")
                           } else if (n >= 0) {
                               var result = Functions.permutationsWithoutRepetitions(n);
                               createDialog("??????????????????", result.toString())
                           } else {
                               createDialog("????????????????!", "???????????? ???? ??????????????????")
                           }
                       }
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
        builder.setPositiveButton("????") { dialog, which ->
        }
        builder.create().show()
    }

}
