package au.com.willai.powerlock

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class WiAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            event.packageName == packageName // packageName is your app's package name
        ){
            lockScreen()
            backToHome()
        }
        Log.e("WiAccesibilityService","onAccessibility")


    }

    override fun onInterrupt() {
        Log.e("WiAccessibilityService", "onInterrupt")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPES_ALL_MASK
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
        }
        serviceInfo = info
    }


    fun lockScreen() {
        performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }

    fun backToHome(){
        //close the app
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
    }


}
