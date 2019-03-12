package ng.codeimpact.andelachallengeproject.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Parcelable

import java.util.ArrayList

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

object ShareUtils {

    fun shareCustom(message: String, context: Context) {
        val targetShareIntents = ArrayList<Intent>()
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"

        val resInfos = context.packageManager.queryIntentActivities(shareIntent, 0)

        if (!resInfos.isEmpty()) {
            for (resInfo in resInfos) {
                val packageName = resInfo.activityInfo.packageName

                val intent = Intent()
                intent.component = ComponentName(packageName, resInfo.activityInfo.name)
                intent.action = Intent.ACTION_SEND
                intent.type = "text/*"
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.setPackage(packageName)
                targetShareIntents.add(intent)

            }

            if (!targetShareIntents.isEmpty()) {
                val chooserIntent = Intent.createChooser(targetShareIntents.removeAt(0), "Share with Friends")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toTypedArray<Parcelable>())
                context.startActivity(chooserIntent)
            } else {
            }
        }
    }
}