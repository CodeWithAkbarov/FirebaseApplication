package iqro.mobile.firebaseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import iqro.mobile.firebaseapplication.databinding.ActivityFirestoreBinding

class FirestoreActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var messagesReference: CollectionReference
    val TAG = "TAG"

    private lateinit var binding: ActivityFirestoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()
        messagesReference = firestore.collection("messages")
        messagesReference.get().addOnSuccessListener { messageList ->
            messageList.forEach {
                Log.d(TAG, "messages: ${it.id}=>${it.data}")
            }
        }
        binding.addMessageBtn.setOnClickListener {
            val messageData = mapOf(
                "message" to "Va alaykum assalom",
                "createdAt" to (System.currentTimeMillis())
            )
            messagesReference
                .add(messageData)
                .addOnSuccessListener {
                    Log.d(TAG, "message inserted ${it.id}")
                }.addOnCanceledListener {
                    Log.d(TAG, "message error ")
                }
        }
    }
}