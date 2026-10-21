package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentAddressBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass to handle address selection.
 */
class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            val provinces =
                resources.getStringArray(R.array.provinces)

            // ✅ FIX 1: Menggunakan android.R.layout.simple_spinner_item yang benar
            val adapterProvinces = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                provinces
            )

            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProvinces.adapter = adapterProvinces

            // ✅ Akses tombol menggunakan binding.btnDone (sesuai View Binding)
            btnDone.setOnClickListener {
                findNavController().apply {
                    // Mengirim data 'address' kembali ke Fragment sebelumnya (CheckoutFragment)
                    previousBackStackEntry
                        ?.savedStateHandle?.set("address",
                            spinnerProvinces.selectedItem.toString())
                }.navigateUp() // Kembali ke CheckoutFragment
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}