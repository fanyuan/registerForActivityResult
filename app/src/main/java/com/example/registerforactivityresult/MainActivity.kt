package com.example.registerforactivityresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.registerforactivityresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY_MSG = "key_msg"
    }

    private lateinit var binding: ActivityMainBinding

    // registerForActivityResult
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val launcherCallback = ActivityResultCallback<ActivityResult> { result ->
        val code = result.resultCode
        val data = result.data
        val msg = data?.getStringExtra(KEY_MSG)
        val msgContent = "code = $code msg = $msg "
        toast(msgContent)//data =$data
        binding.display.append(msgContent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),launcherCallback)
    }

    fun skip(view: View) {
        //toast("skip")
        resultLauncher.launch(Intent(this,Activity2::class.java))
    }

    private fun toast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}