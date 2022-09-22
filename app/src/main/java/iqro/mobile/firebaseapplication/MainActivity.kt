package iqro.mobile.firebaseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import iqro.mobile.firebaseapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.registrationBtn.setOnClickListener {
            auth
                .createUserWithEmailAndPassword("codewithakbarov@gmail.com", "123456")
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "onCreate: Successfully user created")
                    } else {
                        Log.d(TAG, "onCreate: Error while creating user")

                    }
                }
        }
        binding.loginBtn.setOnClickListener {
            auth.signInWithEmailAndPassword("codewithakbarov@gmail.com","123456")
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.d(TAG, "onCreate: User login successfully")
                    }
                    else{
                        Log.d(TAG, "onCreate: User login error")

                    }
                }
        }
        binding.signoutBtn.setOnClickListener {
            auth.signOut()
        }
    }
}