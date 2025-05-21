package com.example.einkaufsliste.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.einkaufsliste.R

// NO. 1 Ressource: https://developer.android.com/develop/ui/views/components/dialogs

class ItemAddDialog : DialogFragment() {
    // Until "onCreateDialog" we have functions that allow the Parent
    // to do the action we want it to take.
    internal lateinit var listener: ItemAddDialogListener

    interface ItemAddDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ItemAddDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement ItemAddDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setTitle("Which Item would you like to add?")
                .setView(inflater.inflate(R.layout.dialog_item_add, null))
                .setPositiveButton("Start",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Start
                        val item = inflater.inflate(R.layout.dialog_item_add, null)?.findViewById<EditText>(R.id.item_add)
                        println("INFO: ADD ITEM: ${item?.editableText.toString()}")
                        listener.onDialogPositiveClick(this)
                })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Canceled
                        listener.onDialogNegativeClick(this)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }
}
