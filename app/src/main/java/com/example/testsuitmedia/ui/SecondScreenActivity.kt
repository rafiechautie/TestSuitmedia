package com.example.testsuitmedia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.ActivityFirstSrennBinding
import com.example.testsuitmedia.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val secondBar = binding.secondToolbar

        secondBar.setNavigationOnClickListener {
            onBackPressed()
        }

        val name =intent.getStringExtra(NAME)
        val user =intent.getStringExtra(USER)

        val selectedName = binding.tvSelectedUserName

        if (name == ""){
            binding.tvName.text = "Someone else"
        }else{
            binding.tvName.text = name
        }
        selectedName.text = user ?: "Selected User Name"
        binding.btnChooseUser.setOnClickListener(this)
    }

    companion object{
        const val NAME = "name"
        const val USER = "user"
    }



    override fun onClick(v: View) {
        when{
            v.id == R.id.btn_choose_user -> {
                val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }
}