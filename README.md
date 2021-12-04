# Android-Webview-Adblock

[![current version](https://img.shields.io/badge/current%20version-1.2-blue.svg)](https://github.com/marcelbohland/Android-Webview-Adblock/releases/tag/1.2)
[![license](https://img.shields.io/badge/license-Apache%20License%202.0-red.svg)](https://github.com/marcelbohland/Android-Webview-Adblock/blob/master/LICENSE)

Adblock System for Android Webview

Example App: https://github.com/marcelbohland/Android-Webview-Adblock-Example

<br/>

How to implement:

1. Implement Adblock.java/Adblocker.kt into your Project

2. Create a raw folder in Android Studio ({projectname}\app\src\main\res\raw)

3. Add a filterlist to your raw folder

https://raw.githubusercontent.com/Openadblockserverlist/adblockserverlist/master/adblockserverlist.txt

License: https://github.com/Openadblockserverlist/adblockserverlist/blob/master/LICENSE

4. Add following to your AndroidManifest.xml

```xml
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET"/>
```

5. Add following to your layout\activity_main.xml
```xml
        <WebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
<br/>

If you're having problems... Please take a look at the example App --> https://github.com/marcelbohland/Android-Webview-Adblock-Example




# License

 Copyright 2019 Marcel Bohland

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
