package dev.dna.deliveryapp.screens.register

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.screens.anything.showToastMessage
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.AppUtils.showToastMsg

private const val TAG = "RegisterAndLoginViewMod"

//@HiltViewModel
class RegisterAndLoginViewModel : ViewModel() {

    fun isEmailValid(email: String, context: Context): Boolean {
        return if (AppUtils.validEmail(email)) {
            true
        } else {
            showToastMessage(context, context.resources.getString(R.string.invalid_email_msg_lbl))
            false
        }
    }

    fun isFullNameValid(fullName: String, context: Context): Boolean {
        return if (AppUtils.validUserInput(fullName)) {
            true
        } else {
            showToastMessage(context, context.resources.getString(R.string.invalid_full_msg_lbl))
            false
        }
    }

    fun isPhoneNumberValid(phone: String, context: Context): Boolean {
        return if (AppUtils.validPhoneNumber(phone)) {
            true
        } else {
            showToastMessage(context, context.resources.getString(R.string.invalid_phone_msg_lbl))
            false
        }
    }


    fun getGoogleLoginAuth(context: Context, requestIdToken: String): GoogleSignInClient {
        Log.d(TAG, "getGoogleLoginAuth: called")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).apply {
            requestEmail()
            requestIdToken(requestIdToken)
            requestId()
            requestProfile()
        }.build()

        return GoogleSignIn.getClient(context, gso)
    }

    private fun firebaseAuthWithGoogle(
        task: Task<GoogleSignInAccount>,
        context: Context,
        onSignUpSuccess: (Boolean) -> Unit
    ) {
        Log.d(TAG, "firebaseAuthWithGoogle: login with firebase")
        val credential = GoogleAuthProvider.getCredential(task.result.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { t ->
            if (t.isSuccessful) {
                showToastMsg(context = context, "SignUp successful")
                Log.d(TAG, "firebaseAuthWithGoogle: Added to auth")
                onSignUpSuccess(true)
            } else {
                showToastMsg(context = context, "SignUp failed")
                Log.d(TAG, "firebaseAuthWithGoogle: on complete failed ", t.exception)
                t.exception?.printStackTrace()
                onSignUpSuccess(false)
            }
        }.addOnFailureListener {
            showToastMsg(context = context, "SignUp failed")
            Log.d(TAG, "firebaseAuthWithGoogle: onFailure failed ", it)
            it.printStackTrace()
            onSignUpSuccess(false)
        }
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>, context: Context, onSignUpSuccess:(Boolean)->Unit): List<String> {
        Log.d(TAG, "handleSignInResult: called")
        val user = task.result
        firebaseAuthWithGoogle(task, context = context, onSignUpSuccess)

        return listOf(
            user.id.toString(),
            user.email.toString(),
            user.displayName.toString()
        )
    }
}