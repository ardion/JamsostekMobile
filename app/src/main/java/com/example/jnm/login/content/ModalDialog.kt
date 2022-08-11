package com.example.jnm.login.content

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.jnm.R
import com.example.jnm.databinding.ModalDialogBinding

inline fun Activity.alertDialog(
    titleResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val title =  getString(titleResource)
    val dialog = AlertDialogHelper(this, title).apply {
        func()
    }.create()
    dialog.window?.decorView?.setBackgroundResource(R.drawable.bg_modal)

    return dialog
}

@SuppressLint("InflateParams")
class AlertDialogHelper(context: Context, title: CharSequence?) {
    val rootView = ModalDialogBinding.inflate(LayoutInflater.from(context))

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(rootView.root)

    private var dialog: AlertDialog? = null


    var cancelable: Boolean = true

    init {
        rootView.tvDescInvalid.text = title
    }

    fun button(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        with(rootView.btnOk) {
            text = builder.context.getString(textResource)
            setClickListenerToDialogButton(func)
        }
    }


    fun create(): AlertDialog {
        with(rootView) {
            tvDescInvalid.goneIfTextEmpty()
            btnOk.goneIfTextEmpty()
        }
        dialog = builder
            .setCancelable(cancelable)
            .create()

        return dialog!!
    }


    private fun TextView.goneIfTextEmpty() {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}