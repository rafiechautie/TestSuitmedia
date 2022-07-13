package com.example.testsuitmedia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.ActivityFirstSrennBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstSrennActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFirstSrennBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstSrennBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when{
            v.id == R.id.btn_check -> {
                var palindrome =binding.palindromeEditText.text.toString()
                if (palindrome.isEmpty()){
                    messageDialog("Invalid", "please input a text to check whether the sentence is palindrome or not")
                }else{
                    if (checkPalindrome(palindrome.replace("\\s".toRegex(), ""))){
                        messageDialog("Result", "${palindrome.trim()} is Palindrome")
                    }else{
                        messageDialog("Result", "${palindrome.trim()} not Palindrome")
                    }
                }
            }
            v.id == R.id.btn_next -> {
                var isEmptyFields = false

                var name =binding.nameEditText.text.toString()
                var palindrome = binding.palindromeEditText.text.toString()
                if (name.isEmpty()){
                    isEmptyFields = true
                    binding.nameEditText.error = "Field tidak boleh kosong"
                }
                if (palindrome.isEmpty()){
                    isEmptyFields = true
                    binding.palindromeEditText.error = "Field tidak boleh kosong"
                }

                if(!isEmptyFields){
                    val intent =Intent(this@FirstSrennActivity, SecondScreenActivity::class.java)
                    intent.putExtra(SecondScreenActivity.NAME, name.trim())
                    startActivity(intent)
                }
            }
        }
    }

    private fun checkPalindrome(text: String): Boolean {
        val reverseString = text.reversed()
        return text.equals(reverseString, ignoreCase = true)
    }

    private fun messageDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OKE") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }


}