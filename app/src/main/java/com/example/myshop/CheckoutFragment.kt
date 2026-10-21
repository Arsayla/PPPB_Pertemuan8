package com.example.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!


    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.txtProductName.text = args.productName ?: "Product Name N/A"


        if (!args.address.isNullOrEmpty()) {
            binding.txtSelectedAddress.text = args.address
        } else {
            binding.txtSelectedAddress.text = "Pilih Provinsi..."
        }


        binding.txtSelectedAddress.setOnClickListener { // Menggunakan TextView sebagai tombol
            val action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
            findNavController().navigate(action)
        }

        // 4. Menerima hasil dari AddressFragment melalui SavedStateHandle
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("address")
            ?.observe(viewLifecycleOwner) { result ->
                // Update UI di CheckoutFragment saat AddressFragment kembali
                if (!result.isNullOrEmpty()) {
                    binding.txtSelectedAddress.text = result
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}