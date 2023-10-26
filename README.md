## Nagdroid - For ethical and conscientious Android crackers
Nagdroid is an add-on nagware for those Android app pirates who still have sympathy for the app developers. \
It functions similarly to nagware in WinRAR, displaying a pop-up every time the app is opened after 30 days, with the hope that the user feels annoyed and eventually intends to purchase the app.
## Installation (AndroidManifest Method)
TODO
## Installation (DEX Method)
Make sure you have a basic understanding of reverse engineering and Smali. You can read this [documentation](https://source.android.com/docs/core/runtime/dalvik-bytecode) to learn about Smali.

1. You need to decompile the target APK using [apktool](https://github.com/iBotPeaches/Apktool) or a similar tool. Then, locate the smali file that serves as the main activity of the APK (refer to the `activity` in AndroidManifest.xml with the `android.intent.action.MAIN` action).
2. After you've found the smali file, add this code inside the `onCreate` method (replace the `A` with the register):
```smali
new-instance vA, Lid/aozora/nagdroid/Nagdroid;
invoke-direct {vA, p0}, Lid/aozora/nagdroid/Nagdroid;-><init>(Landroid/content/Context;)V
invoke-virtual {vA}, Lid/aozora/nagdroid/Nagdroid;->show()Landroid/app/AlertDialog;
move-result-object vA
```
3. Download the latest [classes.dex](https://github.com/AozoraDev/Nagdroid/releases/latest/download/classes.dex), and then decompile it using [baksmali](https://bitbucket.org/JesusFreke/smali).
4. Move all the contents of its output (usually inside the `out` folder) into the `smali` folder of the decompiled APK folder earlier.
5. Rebuild the APK using apktool and then sign it using jarsigner (using debug keystore is recommended).

## Documentation
TODO
## License
This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.