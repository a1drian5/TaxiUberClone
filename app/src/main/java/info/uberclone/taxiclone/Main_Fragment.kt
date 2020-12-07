package info.uberclone.taxiclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main__fragment.*

class Main_Fragment : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__fragment)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_inicio -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> Toast.makeText(this,"Settings Click",Toast.LENGTH_SHORT).show()
                R.id.info -> Toast.makeText(this,"Info Click",Toast.LENGTH_SHORT).show()
                R.id.perfil -> {val intent = Intent(this,Perfil::class.java)
                    startActivity(intent) }

            }

//            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START)
//            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}