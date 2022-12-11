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

### Pass the value from 1st Activity to 3rd
```kotlin
/*
 1st Activity - Customized ListView
 2nd Activity - SingleMenuItemActivity
 3rd Activity - InsertionExample
*/

val intent=Intent(CustomizedListview.this,InsertionExample.class)
intent.putExtra("orderid",getOrderid);
startActivity(intent);
```
* In your 3rd Activity
```kotlin
 val bundle:Bundle = data.getExtras();
 val getOrderId:String = bundle.getString("orderid");
```
Another way
```java
//your 1st activity (CustomizedListview)
String str:String=txtView.getText().toString();
Intent i=new Intent(getApplicationContext(),SingleMenuItemActivity.class);
i.putExtra("message",str);
startActivity(i);

//your 2nd Activity (SingleMenuItemActivity)
String str= getIntent().getStringExtra("message");
Intent i = new Intent(getApplicationContext(), InsertionExample.class);
i.putExtra("message", str);
startActivity(i);

//you 3rd Activity (InsertionExample)
Intent int=getIntent();
String str= int.getStringExtra("message");
txtView.setText(str);
```

### Create Notification
```kotlin
createNotificationChannel()
        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Awesome Notification")
            .setContentText("This is the content text") // description of notification
            .setSmallIcon(R.drawable.ic_home_current)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // customize the previously set priority if required
            .build() // to really build the notification (above are only designed the notification, not going to build without build())
        val notificationManager= NotificationManagerCompat.from(this) // manager for notification

        val notificationbtn:Button=findViewById(R.id.notificationbtn)
        notificationbtn.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
        }
private val CHANNEL_ID="channelID"
private val CHANNEL_NAME="channelName"
private val NOTIFICATION_ID=0
fun createNotificationChannel(){
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){ // O is for Orea
        val channel= NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT) // Priority for notification
            .apply { //bcoz we want to do aomething with channel
                lightColor=Color.GREEN
                enableLights(true)
            }
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // to get system service
        manager.createNotificationChannel(channel)
    }
}
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

#### Why did we have to create an event listener to get items in the list view to respond to clicks? Couldn't we have just used it `android:onClick` attribute in the layout code?
> You can only use the `android:onClick` attribute in activity layouts for buttons, or any views that are subclasses of `Button` such as `CheckBox` and `RadioButton`.
> The `ListView` class  isn't a subclass of `Button`, so using the `android:onClick` attribute won't work.
> That's why you have to implement your own listener.

#### app bars, action bars, and toolbars. Is there a difference?
> An app bar is the bar that usually appears at the top of your activities.
> It’s sometimes called an action bar because in earlier versions of Android, the only way of implementing an app bar was via the ActionBar class. 

> The ActionBar class is used behind the scenes when you add an app bar by applying a theme.
> If your app doesn’t rely on any new app bar features, this may be sufficient for your app. 

> An alternative way of adding an app bar is to implement a toolbar using the Toolbar class.
> The result looks similar to the default theme-based app bar, but it includes newer features of Android.

#### I’ve seen the <include> tag in some of the code that Android Studio has created for me. What does it do?
> The <include> tag is used to include one layout inside another. 
> Depending on what version of Android Studio you’re using and what type of project you create, Android Studio may split your layout code into one or more separate layouts.
