package com.example.einkaufsliste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.einkaufsliste.databinding.ActivityMainBinding
import com.example.einkaufsliste.dialogs.ItemAddDialog
import com.example.einkaufsliste.dialogs.ItemRemoveDialog

class MainActivity : AppCompatActivity(), ItemAddDialog.ItemAddDialogListener, ItemRemoveDialog.ItemRemoveDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<String>

    private val shopping_list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val listView = findViewById<ListView>(R.id.einkaufsliste_items)

        adapter = ArrayAdapter(this, R.layout.einkaufsliste_item, R.id.item_text, shopping_list)

        listView.adapter = adapter

        binding.fab.setOnClickListener { view ->
            ItemAddDialog().show(supportFragmentManager, "ITEM_DIALOG")
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            ItemRemoveDialog(position, shopping_list[position]).show(supportFragmentManager, "ITEM_DIALOG")
        }
    }

    override fun onItemAddDialogPositiveClick(item: String) {
        // User taps the dialog's positive button.
        println("User added $item.")
        shopping_list.add(item)

        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Added $item to the list", Toast.LENGTH_SHORT).show()
    }

    override fun onItemRemoveDialogPositiveClick(position: Int, item: String) {
        shopping_list.removeAt(position)
        adapter.notifyDataSetChanged()

        Toast.makeText(this, "Removed $item from the list", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}