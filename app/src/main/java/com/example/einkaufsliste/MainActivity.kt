package com.example.einkaufsliste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.DialogFragment
import com.example.einkaufsliste.databinding.ActivityMainBinding
import com.example.einkaufsliste.dialogs.ItemAddDialog

class MainActivity : AppCompatActivity(), ItemAddDialog.ItemAddDialogListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            ItemAddDialog().show(supportFragmentManager, "ITEM_DIALOG")
        }
    }

    override fun onDialogPositiveClick(item: String) {
        // User taps the dialog's positive button.
        println("User added $item.")
        // TODO: Make snackbar as User feedback...
        // TODO: Add the item to a list (or even better a Database)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}