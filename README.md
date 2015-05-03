NeptuneCommon
=============
**Currently not stable and under heavy development!**  
Shared code between Neptune and Arno, licensed under the [MIT License]. 

* [Homepage]
* [Source]
* [Issues]
* [Community Chat]: #neptune on irc.esper.net
* [Development Chat]: #neptunedev on irc.esper.net

## Prerequisites
* [Java] 6

## Clone
The following steps will ensure your project is cloned properly.  
  1. `git clone --recursive https://github.com/NeptunePowered/NeptuneCommon.git`
  2. `cd NeptuneCommon`

## Setup
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows systems in place of any 'gradle' command.

__For [Eclipse]__  
  1. Run `gradle setupDecompWorkspace --refresh-dependencies`  
  2. Run `gradle eclipse`
  3. Import NeptuneCommon as an existing project (File > Import > General)
  4. Select the root folder for NeptuneCommon and make sure `Search for nested projects` is enabled
  5. Check NeptuneCommon when it finishes building and click **Finish**

__For [IntelliJ]__  
  1. Run `gradle setupDecompWorkspace --refresh-dependencies`  
  2. Make sure you have the Gradle plugin enabled (File > Settings > Plugins).  
  3. Click File > New > Project from Existing Sources > Gradle and select the root folder for NeptuneCommon.

[Eclipse]: http://www.eclipse.org/
[Gradle]: http://www.gradle.org/
[Homepage]: http://www.neptunepowered.org/
[IntelliJ]: http://www.jetbrains.com/idea/
[Issues]: https://github.com/NeptunePowered/NeptuneCommon/issues
[Java]: http://java.oracle.com/
[Source]: https://github.com/NeptunePowered/
[MIT License]: http://www.tldrlegal.com/license/mit-license
[Community Chat]: https://kiwiirc.com/client/irc.esper.net/?#neptune
[Development Chat]: https://kiwiirc.com/client/irc.esper.net/?#neptunedev
