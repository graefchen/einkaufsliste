package com.example.einkaufsliste.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.einkaufsliste.R

// NO. 1 Resource: https://developer.android.com/develop/ui/views/components/dialogs

class ItemAddDialog : DialogFragment() {
    // Until "onCreateDialog" we have functions that allow the Parent
    // to do the action we want it to take.
    internal lateinit var listener: ItemAddDialogListener

    interface ItemAddDialogListener {
        fun onItemAddDialogPositiveClick(item: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ItemAddDialogListener
        } catch (_: ClassCastException) {
            throw ClassCastException(("$context must implement ItemAddDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // works with this line below
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_item_add, null)
            val inputTextField = view?.findViewById<EditText>(R.id.item_add)
            builder.setTitle("Which Item would you like to add?")
                .setView(view)
                .setPositiveButton("Add",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Start
                        val input = inputTextField?.text.toString()
                        listener.onItemAddDialogPositiveClick(input)
                })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Canceled
                        getDialog()?.cancel()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }
}
