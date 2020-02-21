package com.dmelechow.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmelechow.R
import com.dmelechow.di.injectModules
import kotlinx.android.synthetic.main.auth_fragment.*
import org.koin.androidx.viewmodel.ext.viewModel

class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectModules()

        viewModel.authData.observe(viewLifecycleOwner, Observer { authState(it) })

        login_view.setOnClickListener {
            if (validate(username_view, password_view)) {
                viewModel.login(password_view.text.toString(), username_view.text.toString())
            }
        }
    }

    private fun authState(data: Any) {

    }

    private fun validate(
        usernameView: EditText,
        passwordView: EditText
    ): Boolean {
        if (usernameView.text.isNotEmpty()) {
            Toast.makeText(
                activity,
                activity?.resources?.getText(R.string.auth_password_is_empty),
                Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (passwordView.text.isNotEmpty()) {
            Toast.makeText(
                activity,
                activity?.resources?.getText(R.string.auth_username_is_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}
