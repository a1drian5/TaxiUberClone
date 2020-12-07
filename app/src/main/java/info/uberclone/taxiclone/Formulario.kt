package info.uberclone.taxiclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_formulario.*
import java.util.regex.Pattern


class Formulario : AppCompatActivity() {



//    val user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

//        val database = FirebaseDatabase.getInstance().reference
//
//        database.child("hola123").setValue("hola")






        etDate.setOnClickListener { showDatePickerDialog() }

        btn_continuar.setOnClickListener {
            if (et_nombre.length() == 0) {
                et_nombre.error = "Ingrese un nombre"
            }
            if (et_apellido.length() == 0) {
                et_apellido.error = "Ingrese un apellido"
            }
            if (etDate.length() == 0) {
                etDate.error = "Ingrese una fecha"
            }

            if (!validarEmail(et_correo.text.toString())) {
                et_correo.setError("Email no válido")
            } else {
                val user = FirebaseAuth.getInstance().currentUser
              user!!.updateEmail(et_correo.text.toString())
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "User email address updated.")
                user.sendEmailVerification()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("TAG", "Email sent.")

                            val nombre_user = et_nombre.text.toString()
                            val apellido_user = et_apellido.text.toString()
                            val correo_user = et_correo.text.toString()
                            val fecha_user = etDate.text.toString()

                            nuevo_usuario(
                                user.uid,
                                nombre_user,
                                apellido_user,
                                correo_user,
                                fecha_user
                            )
                            val intent = Intent(this, Main_Fragment::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            }
        }
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> OnDaySelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDaySelected(day: Int, month: Int, year: Int){
        val mes = month+1
        etDate.setText("$day/$mes/$year")
        etDate.error = null
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun nuevo_usuario(userId: String, name: String, apellido: String, correo: String,fecha:String){
        val userFormulario = User_Formulario(name, apellido, correo,fecha)
        val database = FirebaseDatabase.getInstance().reference

        database.child("Users").child(userId).setValue(userFormulario)
    }

//     fun Verificar_Email(email_validando: String){
//        val credential = EmailAuthProvider.getCredential(email_validando,"")
//        FirebaseAuth.getInstance().currentUser!!.linkWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Log.d("TAG", "linkWithCredential:success")
//                    val user = task.result?.user
////                    updateUI(user)
//                } else {
//                    Log.w("TAG", "linkWithCredential:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
////                    updateUI(null)
//                }
//
//                // ...
//            }
//    }


}