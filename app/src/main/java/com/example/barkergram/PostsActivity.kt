package com.example.barkergram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.proto.TargetGlobal

private const val TAG = "PostsActivity"

class PostsActivity : AppCompatActivity() {


    private lateinit var firestoreDb: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)


        firestoreDb = FirebaseFirestore.getInstance()

        val postsReference = firestoreDb.collection("posts")
        postsReference.addSnapshotListener{ snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e(TAG, "Exception when querying posts", exception)
                return@addSnapshotListener
            }
            for (document in snapshot.documents){
                Log.i(TAG, "Document ${document.id}: ${document.data}")

            }
        }

        //query to firestore to get data

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_profile){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)


        }
        return super.onOptionsItemSelected(item)
    }
}