package com.example.firebaseauthapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.firebaseauthapp.databinding.FragmentFinishBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finish,container,false)
        val user = FirebaseAuth.getInstance().currentUser

        binding.emailadddd.setText(user?.email)
        binding.uniqueId.setText(user?.uid.toString())

        binding.backtologin.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            NavHostFragment.findNavController(this).navigate(FinishFragmentDirections.actionFinishFragmentToLoginFragment())
        }

        return binding.root
    }
}