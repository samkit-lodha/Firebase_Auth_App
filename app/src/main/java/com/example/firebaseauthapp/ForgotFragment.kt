package com.example.firebaseauthapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthapp.databinding.FragmentForgotBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotFragment : Fragment() {

    private lateinit var binding: FragmentForgotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_forgot,container,false)

        binding.submitButton.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.forgotEmailId.text.toString().trim { it<=' ' }) -> {
                    Toast.makeText(requireContext(),"Enter valid email address", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val emailAdd = binding.forgotEmailId.text.toString().trim { it<=' ' }

                    FirebaseAuth.getInstance().sendPasswordResetEmail(emailAdd)
                        .addOnCompleteListener({ task ->
                            if(task.isSuccessful){
                                Toast.makeText(requireContext(),
                                    "Email sent successfully to $emailAdd",
                                    Toast.LENGTH_SHORT)
                                    .show()


                                findNavController().navigate(ForgotFragmentDirections.actionForgotFragmentToLoginFragment())
                            }
                            else{
                                Toast.makeText(requireContext(),
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }

                        })
                }
            }
        }

        binding.wantLogButton.setOnClickListener {
            it.findNavController().navigate(ForgotFragmentDirections.actionForgotFragmentToLoginFragment())
        }

        return binding.root
    }
}