package info.uberclone.taxiclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var btn_login_phone : Button
    lateinit var btn_login_google : Button
    val RC_SIGN_IN : Int = 1001
    val providers = arrayListOf(
        AuthUI.IdpConfig.PhoneBuilder().build(),
    )

// ...
// Initialize Firebase Auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_login_phone = findViewById(R.id.btn_phone)
        btn_login_google = findViewById(R.id.btn_google)



        btn_login_phone.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.taxi_icon) // Set logo drawable
                    .setTheme(R.style.Theme_TaxiClone) // Set theme
                    .build(),
                RC_SIGN_IN)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Welcome"+user,Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Formulario::class.java))
                finish()
            }else{
                if(response==null){
                    startActivityForResult(
                        AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.taxi_icon) // Set logo drawable
                            .setTheme(R.style.Theme_TaxiClone) // Set theme
                            .build(),
                        RC_SIGN_IN)
                }
                if(response?.error?.errorCode == ErrorCodes.NO_NETWORK){
                    return
                }
                if(response?.error?.errorCode == ErrorCodes.UNKNOWN_ERROR){
                    Toast.makeText(this,"FAIL",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}

