package com.bws.chillerfiller.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.chillerfiller.R
import com.bws.chillerfiller.common.ApiInterface
import com.bws.chillerfiller.common.RetrofitHelper
import com.bws.chillerfiller.itemcategory.productlist.ProductListActivity
import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.repository.AppRepository
import com.bws.chillerfiller.signup.SignUpActivity
import com.bws.chillerfiller.util.LoadingDialog

import com.bws.chillerfiller.util.Resource
import com.bws.chillerfiller.util.Resources2
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val loginRepository = LoginRepository()
        val loginFactory = LoginFactory(loginRepository, this)
        loginViewModel =
            ViewModelProvider(this, loginFactory).get(LoginViewModel::class.java)


        val content = SpannableString("Guest User")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        txtGuestUser.text = content


        btnSignUp.setOnClickListener() {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }

        btnLogin.setOnClickListener() {

            var email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

           // val isAllCheck = CheckAllFields()
           // if (isAllCheck) {
                val loginPram = RequestBodies.LoginBody(email, password)
                val loadingDialog = LoadingDialog.progressDialog(this)
                loginViewModel.loginUser(loginPram)
                loginViewModel.loginResult.observe(this, Observer {
                    when (it) {
                        is Resources2.NoInternet -> {
                            loadingDialog.hide()

                        }
                        is Resources2.Loading -> {
                            loadingDialog.show()
                        }
                        is Resources2.Success -> {
                            loadingDialog.hide()
                            val userDetails = it.data.toString()
                            //   Toast.makeText(this,userDetails,Toast.LENGTH_LONG).show()
                            System.out.println("USER DETAILS==" + "111111111111111")
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    ProductListActivity::class.java
                                )
                            )
                            this.viewModelStore.clear()
                        }
                        is Resources2.Error -> {
                            loadingDialog.hide()
                            Toast.makeText(this, "rrrrr", Toast.LENGTH_LONG).show()
                        }
                    }
                })
           // }
        }
    }


    //VALIDATE FIELDS
    fun CheckAllFields(): Boolean {
        if (edtEmail.text.length === 0) {
            edtEmail.setError("This field is required")
            return false
        }

        if (edtEmail.text.matches(emailPattern.toRegex())) {
            edtEmail.setError("Invalid email")
            return false
        }

        if (edtPassword.text.length === 0) {
            edtPassword.setError("This field is required")
            return false
        }

        if (isValidPassword("") == false) {
            edtPassword.setError("Password should beat least 0-9,a-z,A-Z,special characters")
            return false
        }
        // after all validation return true.
        return true
    }


    fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        matcher = pattern.matcher(password)
        return matcher.matches()

    }
}