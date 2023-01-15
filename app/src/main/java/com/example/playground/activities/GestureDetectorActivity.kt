package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.playground.R
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_gesture_detector.*

class GestureDetectorActivity : AppCompatActivity(),GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener,
    View.OnDragListener {

    var gestureDetectorCompat:GestureDetectorCompat?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_detector)

        gestureDetectorCompat= GestureDetectorCompat(this,this)
        gestureDetectorCompat!!.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetectorCompat!!.onTouchEvent(event!!)
        return super.onTouchEvent(event)
    }

    override fun onDown(motionEvent: MotionEvent?): Boolean {
        gesture.text="onDown"
        return true
    }

    override fun onShowPress(motionEvent: MotionEvent?) {
        gesture.text="onShowPress"
    }

    override fun onSingleTapUp(motionEvent: MotionEvent?): Boolean {
        gesture.text="onSingleTap"
        return true
    }

    override fun onScroll(motionEvent: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        gesture.text="onScroll"
        return true
    }

    override fun onLongPress(motionEvent: MotionEvent?) {
        gesture.text="onLongPress"
        val builder=View.DragShadowBuilder(imageDrag)
        imageDrag.startDrag(null,builder,null,0)
        builder.view.setOnDragListener(this)
    }

    override fun onFling(motionEvent: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        gesture.text="onFling"
        return true
    }

    override fun onSingleTapConfirmed(motionEvent: MotionEvent?): Boolean {
        gesture.text="onSingleTapConfirmed"
        return true
    }

    override fun onDoubleTap(motionEvent: MotionEvent?): Boolean {
        gesture.text="onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(motionEvent: MotionEvent?): Boolean {
        gesture.text="onDoubleTapEvent"
        return true
    }

    override fun onDrag(v: View?, dragEvent: DragEvent?): Boolean {
        when(dragEvent!!.action){

            DragEvent.ACTION_DRAG_STARTED->{
                gesture.text="Started"
                return true
            }

            DragEvent.ACTION_DRAG_ENTERED->{
                gesture.text="Entered"
                return true
            }

            DragEvent.ACTION_DRAG_EXITED->{
                gesture.text="Exited"
                return true
            }

            DragEvent.ACTION_DROP->{
                gesture.text="Dropped"
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION->{
                gesture.text="onDrag Started: \n( X:" + dragEvent.x +"\nY:" + dragEvent.y+ " )"
                return true
            }

            DragEvent.ACTION_DRAG_ENDED->{
                gesture.text="Ended"
                return true
            }

            else -> {
                gesture.text="Unknown behaviour received"
            }
        }
        return false
    }
}
