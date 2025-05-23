package com.example.einkaufsliste.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ItemRemoveDialog(pos: Int, n: String) : DialogFragment() {
    // Until "onCreateDialog" we have functions that allow the Parent
    // to do the action we want it to take.
    internal lateinit var listener: ItemRemoveDialogListener

    val position: Int = pos
    val name: String = n

    interface ItemRemoveDialogListener {
        fun onItemRemoveDialogPositiveClick(position: Int, item: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ItemRemoveDialogListener
        } catch (_: ClassCastException) {
            throw ClassCastException(("$context must implement ItemAddDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you really want to remove ${name}?")
                .setPositiveButton("Remove",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Removing an item
                        // here we call the function on the listener
                        // the listener can be found in "MainActivity"
                        listener.onItemRemoveDialogPositiveClick(position, name)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }
}