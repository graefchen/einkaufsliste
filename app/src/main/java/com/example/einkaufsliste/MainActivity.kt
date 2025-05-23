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

    // using a simple list (i was to lazy to use a db/sqlite)
    private val shoppingList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Boring initialisation stuff ...
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // creating the listview and adding an array adapter to it with all the
        // data from the shoppingList
        val listView = findViewById<ListView>(R.id.einkaufsliste_items)
        adapter = ArrayAdapter(this, R.layout.einkaufsliste_item, R.id.item_text, shoppingList)
        listView.adapter = adapter

        // Adding the "onClickListener" on the fab (the "+" in the bottom right corner)
        binding.fab.setOnClickListener { _ ->
            ItemAddDialog().show(supportFragmentManager, "ITEM_DIALOG")
        }

        // Adding the "onClickListener" on every (new) list item
        listView.setOnItemClickListener { _, _, position, _ ->
            ItemRemoveDialog(position, shoppingList[position]).show(supportFragmentManager, "ITEM_DIALOG")
        }
    }

    // What happens when we press the positive click on the "add dialog"
    override fun onItemAddDialogPositiveClick(item: String) {
        println("User added $item.")
        shoppingList.add(item)

        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Added $item to the list", Toast.LENGTH_SHORT).show()
    }

    // What happens when we press the positive click on the "remove dialog"
    override fun onItemRemoveDialogPositiveClick(position: Int, item: String) {
        shoppingList.removeAt(position)
        adapter.notifyDataSetChanged()

        Toast.makeText(this, "Removed $item from the list", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}