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

val intent=Intent(CustomizedListview.this,InsertionExample::class.java)
intent?.putExtra("orderid",getOrderid)
startActivity(intent)
```
* In your 3rd Activity
```kotlin
 val bundle:Bundle = data.getExtras()
 val getOrderId:String = bundle.getString("orderid")
```
Another way
```kotlin
//your 1st activity (CustomizedListview)
val str:String=txtView.text.toString()
val i= Intent(applicationContext,SingleMenuItemActivity::class.java)
i.putExtra("message",str)
startActivity(i)

//your 2nd Activity (SingleMenuItemActivity)
val str:String = Intent.getStringExtra("message")
val i =Intent(applicationContext, InsertionExample::class.java)
i.putExtra("message", str)
startActivity(i)

//you 3rd Activity (InsertionExample)
val int:Intent=getIntent()
val str:String= int?.getStringExtra("message")
txtView.setText(str)
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

#### Why can't an activity get a fragment by calling the `findViewById()`?
> Because `findViewById()` always returns a `View` objcet and, surprisingly, fragments are not Views.

#### Why isn’t findFragmentById() an activity method like `findViewById()` is?
> That’s a good question. Fragments weren’t available before API 11, so it uses the fragment manager as a way to add a whole bunch of useful code for managing fragments, without having to pack lots of extra code into the activity base class.

#### Why don’t fragments have a `findViewById()`?
> Because fragments aren’t views or activities. 
> Instead, you need to use the fragment’s `getView()` to get a reference to the fragment’s root view, and then call the view’s `findViewById()` to get its child views.

#### Activities need to be registered in AndroidManifest.xml so that the app can use them. Do fragments?
> No. Activities need to be registered in AndroidManifest.xml, but fragments don’t.

#### When we create a list fragment, why do we choose the option for Fragment (Blank) instead of Fragment (List)?
> The Fragment (List) option produces code that’s more complex, most of which we don’t need to use. 
> The code generated by the Fragment (Blank) is simpler.

#### When should I use tabs in my app?
> Tabs work well when you want to give the user a quick way of navigation between a small number of categories.
> You would generally put each one a separate tab.

#### What if I have a large number of categories? Can I still use tabs?
> You can, but you may want to consider other forms of navigation such as navigation drawers.
> These are panels that slide out from the side of the screen.

#### You mentioned the fragment state pager adapter. What’s that?
> It’s very similar to a fragment pager adapter, except that it also handles saving and restoring a fragment’s state. 
> It uses less memory than a fragment pager adapter, as when pages aren’t visible, the fragment it displays may be destroyed.
> It’s useful if your view pager has a large number of pages.

### Bullet Points
- 1 getting started
* Versions of Android have a version number, API level, and code name.
  
* Android Studio is a special version of IntelliJ IDEA that interfaces with the Android Software Development Kit (SDK) and the Gradle build system.

* A typical Android app is composed of activities, layouts, and resource files.
  
* Layouts describe what your app looks like. They’re held in the app/src/main/res/layout folder.
  
* Activities describe what your app does, and how it interacts with the user. The activities you write are held in the app/src/main/java folder.
  
* AndroidManifest.xml contains information about the app itself. It lives in the app/src/main folder.
  
* An AVD is an Android Virtual Device. It runs in the Android emulator and mimics a physical Android device.
  
* An APK is an Android application package. It’s like a JAR file for Android apps, and contains your app’s bytecode, libraries, and resources. You install an app on a device by installing the APK.
  
* Android apps run in separate processes using the Android runtime (ART).
  
* The <TextView> element is used for displaying text.

- 2 Building interactive apps
* The <Button> element is used to add a button.
  
* The <Spinner> element is used to add a spinner, which is a drop-down list of values.
  
* All GUI components are types of view. They inherit from the Android View class.
  
* strings.xml is a String resource file. It’s used to separate out text values from the layouts and activities, and supports localization.
  
* Add a String to strings.xml using: `<string name="name">Value</string>`
  
* Reference a String in the layout using: `"@string/name"`
  
* Add an array of String values to strings.xml using:
```xml<string-array name="array">
  <item>string1</item>
  ...
  </string-array>
  ```

* Reference a string-array in the layout using: `"@array/array_name"`
  
* Make a button call a method when clicked by adding the following to the layout: `android:onClick="clickMethod"`
  There needs to be a corresponding method in the activity: `public void clickMethod(View view){ }`
  
* `R.java` is generated for you. It enables you to get references for layouts, GUI components, Strings, and other resources in your Java code.
  
* Use `findViewById()` to get a reference to a view.
  
* Use `setText()` to set the text in a view.
  
* Use `getSelectedItem()` to get the selected item in a spinner.

* Add a custom class to an Android project by going to File menu→New...→Java Class.

- 3 Multiple Activities and intents
* A task is two or more activities chained together.

* The <EditText> element defines an editable text field for entering text. It inherits from the Android View class.
 
* You can add a new activity in Android Studio by choosing File → New... → Activity.
 
* Each activity you create must have an entry in AndroidManifest.xml.
 
* An intent is a type of message that Android components use to communicate with one another.
 
* An explicit intent specifies the component the intent is targeted at. You create an explicit intent using `val intent = Intent(this, Target::class.java)`.
 
* To start an activity, call `startActivity(intent)`. If no activities are found, it throws an ActivityNotFoundException.
 
* Use the putExtra() method to add extra information to an intent.
 
* Use the getIntent() method to retrieve the intent that started the activity.
 
* Use the get*Extra() methods to retrieve extra information associated with the intent. `getStringExtra()` retrieves a String, `getIntExtra()` retrieves an int, and so on.
 
* An activity action describes a standard operational action an activity can perform. For example, to send a message,
 use `Intent.ACTION_SEND`.
 
* To create an implicit intent that specifies an action, use `val intent = Intent(action)`.
 
* To describe the type of data in an intent, use the `setType()` method.
 
* Android resolves intents based on the named component, action, type of data, and categories specified in the
 intent. It compares the contents of the intent with the intent filters in each app’s AndroidManifest.xml. 

* An activity must have a category of DEFAULT if it is to receive an implicit intent.
 
* The createChooser() method allows you to override the default Android activity chooser dialog. It lets you
 specify a title for the dialog, and doesn’t give the user the option of setting a default activity. If no activities can
 receive the intent it is passed, it displays a message. The createChooser() method returns an Intent.

* You retrieve the value of a String resource using getString(R.string.stringname);.

- 4 The Activity LifeCycle
* Each app runs in its own process by default.
  
* Only the main thread can update the user interface.
  
* Use a Handler to schedule code or post code to a different thread.
  
* A device configuration change results in the activity being destroyed and recreated.
  
* Your activity inherits the lifecycle methods from the android.app.Activity class. If you override any of these methods, you need to call up to the method in the superclass.

* `onSaveInstanceState(Bundle)` enables your activity to save its state before the activity gets destroyed. You can use the Bundle to restore state in onCreate().
  
* You add values to a Bundle using bundle.put*("name", value).
  
* You retrieve values from the bundle using bundle.get*("name").
  
* `onCreate()` and `onDestroy()` deal with the birth and death of the activity.
  
* `onRestart()`, `onStart()`, and `onStop()` deal with the visibility of the activity.
  
* `onResume()` and `onPause()` handle when the activity gains and loses the focus.

- 5 View and View Groups
* GUI components are all types of view. They are all subclasses of the android.view.View class.
  
* All layouts are subclasses of the android.view.ViewGroup class. A view group is a type of view that can contain multiple views.
  
* The layout XML file gets converted to a ViewGroup containing a hierarchical tree of views.
  
* A linear layout lists views either horizontally or vertically. You specify the direction using the android:orientation attribute.
  
* A frame layout stacks views.
  
* Use android:padding* attributes to specify how much padding you want around a view.
  
* In a linear layout, use android:layout_weight if you want a view to use up extra space in the layout.
  
* android:layout_gravity lets you say where you want views to appear in their available space.
  
* android:gravity lets you say where you want the contents to appear inside a view.
  
* `<ToggleButton>` defines a toggle button that allows you to choose between two states by clicking a button.
  
* `<Switch>` defines a switch control that behaves in the same way as a toggle button. It requires API level 14 or above.

* `<CheckBox>` defines a checkbox.
  
* To define a group of radio buttons, first use <RadioGroup> to define the radio group. Then put individual radio buttons in the radio group using <RadioButton>.
  
* Use <ImageView> to display an image.
  
* `<ImageButton>` defines a button with no text, just an image.
  
* Add scrollbars using <ScrollView> or <HorizontalScrollView>.
  
* A Toast is a pop-up message.

- 6 Constraint Layouts
* Constraint layouts are designed to work with Android Studio’s design editor. They have their own library and can be used in apps where the minimum SDK is API level 9 or above.
  
* Position views by adding constraints. Each view needs at least one horizontal and one vertical constraint.
  
* Center views by adding constraints to opposite sides of the view. Change the view’s bias to update its position between the constraints.
  
* You can change a view’s size to match its constraints if the view has constraints on opposing sides.
  
* You can specify a width:height aspect ratio for the view’s size.
  
* Clicking on the Infer Constraints button adds constraints to views based on their position in the blueprint.

- 7 ListViews and Adapters
* Sort your ideas for activities into top-level activities, category activities, and detail/edit activities. Use the category activities to navigate from the top-level activities to the detail/ edit activities.
  
* A list view displays items in a list. Add it to your layout using the <ListView> element.
  
* Use android:entries in your layout to populate the items in your list views from an array defined in strings.xml.
  
* An adapter acts as a bridge between an AdapterView and a data source. ListViews and Spinners are both types of AdapterView.
  
* An ArrayAdapter is an adapter that works with arrays.
  
* Handle click events on Buttons using android:onClick in the layout code. Handle click events elsewhere by creating a listener and  implementing its click event.

- 8 support  libraries and appbars
* You add a basic app bar by applying a theme that contains one.

* The Android Support Libraries provide backward compatibility with older versions of Android.

* The AppCompatActivity class is a type of activity that resides in the v7 AppCompat Support Library.

* In general, your activity needs to extend the AppCompatActivity class whenever you want an app bar that provides backward compatibility with older versions of Android.

* The android:theme attribute in AndroidManifest.xml specifies which theme to apply.

* You define styles in a style resource file using the <style> element. The name attribute gives the style a name. The parent attribute specifies where the style should inherit its properties from.
 
* The latest app bar features are in the Toolbar class in the v7 AppCompat Support Library. You can use a toolbar as your app bar.

* Add actions to your app bar by adding them to a menu resource file.

* Add the items in the menu resource file to the app bar by implementing the activity’s `onCreateOptionsMenu()`.

* You determine what items should do when clicked by implementing the activity’s `onOptionsItemSelected()`.

* Add an Up button to your app bar to navigate up the app’s hierarchy. Specify the hierarchy in `AndroidManifest.xml`.

* Use the ActionBar `setDisplayHomeAsUpEnabled()` to enable the Up button.

* You can share content by adding the share action provider to your app bar. Add it by including it in your menu resource file. Call its `setShareIntent()` method to pass it an intent describing the content you wish  to share.
