## Enable Show Taps
```kotlin
Settings.System.putInt(this.contentResolver,"show_touches",1)
```

## Disable Show Taps
```kotlin
Settings.System.putInt(this.contentResolver,"show_touches",0)
```

## Change Background to Black-White vice versa
```kotlin
var counter:Int=0
fltbtn.setOnClickListener {
    if(counter.mod(2)==0){
        layout.setBackgroundColor(Color.BLACK)
        fltbtn.setImageDrawable(getDrawable(com.google.android.material.R.drawable.ic_clock_black_24dp))
        fltbtn.imageTintList= ColorStateList.valueOf(Color.rgb(255,50,50))
        Toast.makeText(this,"Night",Toast.LENGTH_SHORT).show()
    }
    else{
        layout.setBackgroundColor(Color.WHITE)
        fltbtn.imageTintList= ColorStateList.valueOf(Color.rgb(50,50,255))
        Toast.makeText(this,"Day",Toast.LENGTH_SHORT).show()
    }
    counter++
}
counter=0
```

## Check Network Status
```kotlin
    private fun checkConnection() {
        if(isOnline())
            Toast.makeText(this,"You're Online",Toast.LENGTH_SHORT)
        else
            Toast.makeText(this,"You're Offline",Toast.LENGTH_SHORT)
    }

    private fun isOnline(): Boolean {
        val cm:ConnectivityManager= getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo:NetworkInfo= cm.activeNetworkInfo!!
        return netinfo!=null && netinfo.isConnectedOrConnecting
    }
```

## Immediately Kill the activity and exit (Useful for leaving application)
```kotlin
private var backPressedTime:Long=0
 if (backPressedTime + 2000 > System.currentTimeMillis()){
     super.onBackPressed()     
     finish()
 }
 else      
     backToast.show()
```

## Whole method for above
```kotlin
private var backPressedTime:Long=0
lateinit var backToast: Toast

override fun onBackPressed() {
/*
this@MainActivity.finish()
exitProcess(0)
*/

    backToast=Toast.makeText(this,"Press again to leave",Toast.LENGTH_SHORT)
    if(backPressedTime+2000>System.currentTimeMillis()) {
        backToast.cancel()
        super.onBackPressed()
        return
    }
    else
        backToast.show()
    backPressedTime= System.currentTimeMillis()
}
```

## Custom Toast
```kotlin
fun CustomToast(
    context: Context?, text: String?, duration: Int,
    @Nullable backgroundColor: Int?,
    @Nullable textColor: Int?
) {
    val t = Toast.makeText(context, text, duration)
    if (backgroundColor != null) t.view
        ?.setBackgroundTintList(ColorStateList.valueOf(backgroundColor))
    if (textColor != null) (t.view!!.findViewById<View>(android.R.id.message) as TextView)
        .setTextColor(textColor)
    t.show()
}
```
