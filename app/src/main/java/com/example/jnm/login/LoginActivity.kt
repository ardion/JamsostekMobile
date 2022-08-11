package com.example.jnm.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jnm.R
import com.example.jnm.databinding.ActivityLoginBinding
import com.example.jnm.login.content.alertDialog
import com.example.jnm.news.presentation.NewsActivity
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable


class LoginActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    protected val disposable = CompositeDisposable()

    val defEmail = "ardionm97@gmail.com"
    val defPas = "Ardion123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        setupClickListener()


    }

    private fun setupClickListener() {
        with(viewBinding) {
            disposable.addAll(
                editTxtEmail.textChanges()
                    .skipInitialValue()
                    .map { it.isNotBlank() }
                    .subscribe { valid -> showErrorEmailMessage(!valid) },

                editTxtPass.textChanges()
                    .skipInitialValue()
                    .map { it.isNotBlank() }
                    .subscribe { valid -> showErrorPassMessage(!valid) },

                Observable.combineLatest(
                    editTxtEmail.textChanges().map { it.isNotBlank() }.distinctUntilChanged(),
                    editTxtPass.textChanges().map { it.isNotBlank() }.distinctUntilChanged(),
                    { validEmail, validPassword -> validEmail && validPassword }
                ).subscribe { valid ->

                    btnLogin.isEnabled = valid
                }
            )

            btnLogin.setOnClickListener {
                doLogin(editTxtEmail.text.toString(),editTxtPass.text.toString())
            }
        }
    }

    private fun doLogin(email:String, password:String){
        if (email==defEmail && password==defPas){
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        } else{
            alertDialog(
                R.string.title_dialog
            ) {
                button(R.string.title_ok){

                }
            }.show()

        }
    }

    private fun showErrorEmailMessage(show: Boolean) {
        viewBinding.tilEmail.error = if (show) getString(R.string.error_blank) else null
    }

    private fun showErrorPassMessage(show: Boolean) {
        viewBinding.tilPass.error = if (show) getString(R.string.error_blank) else null
    }
}