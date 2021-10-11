package at.allaboutapps.app.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import at.allaboutapps.app.R

class Utils {

    companion object {
        fun showAlert(applicationContext: Context, message: String) {
            val builder = AlertDialog.Builder(applicationContext)
            builder.setTitle(applicationContext.getString(R.string.alert_error))
            builder.setMessage(message)
            builder.setPositiveButton(applicationContext.getString(R.string.alert_okay)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            builder.show()
        }
    }
}