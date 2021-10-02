package com.example.firebaseauthapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthapp.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)

        binding.wantLoginButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }



        binding.registerButton.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.registerEmailId.text.toString().trim { it<=' ' }) -> {
                    Toast.makeText(requireContext(),
                        "Enter valid email address",
                        Toast.LENGTH_SHORT)
                        .show()
                }

                TextUtils.isEmpty(binding.registerPassword.text.toString().trim { it<=' ' }) -> {
                    Toast.makeText(requireContext(),
                        "Enter the password",
                        Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    val emailAdd = binding.registerEmailId.text.toString().trim { it<=' ' }
                    val passWord = binding.registerPassword.text.toString().trim { it<=' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailAdd,passWord)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if(task.isSuccessful){

                                    val firebaseUser : FirebaseUser = task.result!!.user!!

                                    Toast.makeText(requireContext(),
                                        "Successfully registered with $emailAdd and ${firebaseUser.uid}",
                                        Toast.LENGTH_SHORT)
                                        .show()

                                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
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



        return binding.root
    }

}