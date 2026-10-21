package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Contoh: Tombol atau aksi yang memicu navigasi ke CheckoutFragment
        binding.btnBuy.setOnClickListener {
            val productName = "Samsung M22" // Contoh data produk

            // ✅ Menggunakan Safe Args yang dihasilkan.
            // Setelah menambahkan defaultValue="" di XML,
            // Safe Args mungkin hanya memerlukan productName, tetapi jika tidak,
            // Anda harus menyediakan semua argumen yang diperlukan oleh CheckoutFragment.

            // NOTE: Cek apakah class HomeFragmentDirections sudah tergenerate.
            // Anda harus memiliki binding.btnBuy atau elemen lain di layout Anda.
            val action = HomeFragmentDirections.actionHomeFragmentToCheckoutFragment(
                productName = productName,
                address = "" // Mengisi nilai address dengan string kosong (sesuai defaultValue)
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}