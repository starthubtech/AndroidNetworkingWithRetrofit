package ng.codeimpact.andelachallengeproject

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.widget.Toast

fun Toast.showToast(context: Context, message: String) {
    Toast(context)
    setText(message)
    duration = Toast.LENGTH_SHORT
    show()
}


fun ProgressDialog.showProgress(){
    show()
}
fun ProgressDialog.hideProgress(){
    hide()
}

