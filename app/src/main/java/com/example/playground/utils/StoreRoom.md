### Enable Show Taps
```kotlin
Settings.System.putInt(this.contentResolver,"show_touches",1)
```

### Disable Show Taps
```kotlin
Settings.System.putInt(this.contentResolver,"show_touches",0)
```

### Change Background to Black-White vice versa
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

### Check Network Status
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

### Immediately Kill the activity and exit (Useful for leaving application)
```kotlin
private var backPressedTime:Long=0
 if (backPressedTime + 2000 > System.currentTimeMillis()){
     super.onBackPressed()     
     finish()
 }
 else      
     backToast.show()
```

### Whole method for above
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

### Custom Toast
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

### for not defining reference in .kt file, also append `id 'kotlin-android-extensions'`
```kotlin
import kotlinx.android.synthetic.main.activity_main.*
```

## _QnA_

#### I added a String to my `strings.xml` file, but I can't see it in R.java. Why isn't it there?  
> Android Studio generates R.java when you save any changes you've made. If you've added a resource but can't see it in R.java, check that your changes have been saved
> R.java also gets updated when the app gets built. The app builds when you run the app, so running the app will also update `R.java`

#### What type of object is returned by getSelectedItem()?
> It's declared as type `object`. Because we need a string-array for the values, the actual value returned in this case is a `String`

#### Why did we've to replace the activity code that Android Studio created for us?
> IDEs such as Android Studio include functions and utilities that can save you a lot of time.
> They generate a lot of code for you, and sometimes this can be useful. But when you're learning a new language or development area such as Android, we think it's best ot learn about the fundamentals of the language rather than what th IDE genrates for you.
> This way you'll develop a greater understanding of the language.

#### Why does Android run each app inside a separate process?
> For security and stability.
> This approach prevents one app from accessing the data of another.
> It also means if one app crashes, it won't take others down with it.

#### Why have an `onCreate()` method in our activity? Why not just put that code inside a `constructor` ?
> Android needs to set up the environment for the activity after it's constructed.
> Once the environment is ready, Android calls onCreate().
> That's why code to setup the screen goes inside onCreate() instead pf constructor.
