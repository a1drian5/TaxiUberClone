package info.uberclone.taxiclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*

import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var handler: Handler
//    val RC_SIGN_IN : Int = 1001
//    val providers = arrayListOf(
//        AuthUI.IdpConfig.PhoneBuilder().build(),
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
//
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION}

        if(FirebaseAuth.getInstance().currentUser == null){
            handler = Handler()
            handler.postDelayed({
//                startActivityForResult(
//                    AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .setLogo(R.drawable.taxi_icon) // Set logo drawable
//                        .setTheme(R.style.Theme_TaxiClone) // Set theme
//                        .build(),
//                    RC_SIGN_IN)
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            },3000) // delay 3 sec
        }else{
            val intent = Intent(this,Main_Fragment::class.java)
            startActivity(intent)
            finish()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==RC_SIGN_IN){
//            val response = IdpResponse.fromResultIntent(data)
//            if(resultCode == RESULT_OK) {
//                val user = FirebaseAuth.getInstance().currentUser
//                Toast.makeText(this,"Welcome"+user,Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this,MainActivity::class.java))
//            }else{
//                if(response==null){
//                    startActivityForResult(
//                        AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setAvailableProviders(providers)
//                            .setLogo(R.drawable.taxi_icon) // Set logo drawable
//                            .setTheme(R.style.Theme_TaxiClone) // Set theme
//                            .build(),
//                        RC_SIGN_IN)
//                }
//                if(response?.error?.errorCode == ErrorCodes.NO_NETWORK){
//                    return
//                }
//                if(response?.error?.errorCode == ErrorCodes.UNKNOWN_ERROR){
//                    Toast.makeText(this,"FAIL",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//    }
        }

