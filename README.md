
<h1 align="center">
  <br>
  <img src="https://i.imgur.com/ucMiR5q.png" alt="">
</h1>



<h2 align="center">[ ~ The Galacticraft Tweaker Mod ~ ]</h2>
<h3 align="center">That's Not A CraftTweaker Addon</h3>

<p align="center">
    <a href="https://github.com/ReadOnly-Mods/GalacticTweaks/blob/dev-1.12.2/LICENSE"></a>
    <img src="https://badgen.net/github/license/micromatch/micromatch"
         alt="MIT License">
    <a href=""></a>
    <img src="https://img.shields.io/github/v/release/ReadOnly-Mods/GalacticTweaks?style=flat-square&label=Release">
    <a href=""></a>
    <img src="https://badgen.net/badge/Made%20With/Love/pink"
         alt="Made With love">
</p>
<p align="center">
<a href="https://www.curseforge.com/minecraft/mc-mods/galactictweaks">
<img src="http://cf.way2muchnoise.eu/full_galactictweaks_downloads.svg">
<a href="https://www.curseforge.com/minecraft/mc-mods/galactictweaks">
<img src="http://cf.way2muchnoise.eu/versions/galactictweaks.svg">
</p></a>

## Table of Contents

* [About](#about)
* [Issues](#issues)
* [Building](#building)
* [Contribution](#contribution)

## About

A tweaks mod for the Galacticraft Mod. As the name implies it adds various tweaks to give additional gameplay options, also contains fixes for various mechanics where needed or wanted by popular/personal opinion.

## Issues

GalacticTweaks crashing, have a suggestion, found a bug?  Create an issue now!

1. Make sure your issue has not already been answered or fixed and you are using the latest version of this mod and Galacticraft. Also think about whether your issue is a valid one before submitting it.
    * If it is already possible with vanilla, within Galacticraft, another Addon or This mod itself, the suggestion will be considered invalid.
    * Asking for a back-port to an older Minecraft version will also be considered invalid
2. Go to [the issues page](https://github.com/ReadOnly-Mods/GalacticTweaks/issues) and click [The Provided Template](https://github.com/ReadOnly-Mods/GalacticTweaks/issues/new/choose)
3. use one of the provided templates. It will also contain further details about required or useful information to add. Failure to follow the template will result in your suggestion/issue being closed
4. Click `Submit`, and wait for feedback!

Providing as many details as possible does help us to find and resolve the issue faster and also you getting a fixed version as fast as possible.

Please note that we might close any issue not matching these requirements.

## Building

1. Clone this repository via 
  - SSH `git clone git@github.com:ReadOnly-Mods/GalacticTweaks.git` or 
  - HTTPS `git clone https://github.com/ReadOnly-Mods/GalacticTweaks.git`
2. Build using the `gradlew build` command. Jar will be in `build/libs`
3. For core developer: Setup IDE
  - IntelliJ: Import as gradle project
  - Eclipse: Import as gradle project or execute gradle task `eclipse` and potentially `setupDecompWorkspace`

## Contribution

Before adding major changes, you might want to discuss them with us first.
If you are still willing to contribute to this project, you can contribute via [Pull-Request](https://help.github.com/articles/creating-a-pull-request).

The [guidelines for contributing](https://github.com/ReadOnly-Mods/GalacticTweaks/blob/dev-1.12.2/CONTRIBUTING.md) contain more detailed information about topics like the used code style and should also be considered.

Here are a few things to keep in mind that will help get your PR approved.

* A PR should be focused on content. Any PRs where the changes are only syntax will be rejected.
* Use the file you are editing as a style guide.
* Consider your feature. [Suggestion Guidelines](http://ae-mod.info/Suggestion-Guidelines/)
  - Is your suggestion already possible using Galacticraft or other Addons?
  - Make sure your feature isn't already in the works, or hasn't been rejected previously.
  - If your feature can be done by any popular mod, discuss with us first.

**Getting Started**

1. Fork this repository
2. Clone the fork via
  * SSH `git clone git@github.com:<your username>/GalacticTweaks.git` or 
  * HTTPS `git clone https://github.com/<your username>/GalacticTweaks.git`
3. It is prefered you create a new branch and base it off Dev-1.12.2 (Branches based off the realease branch will be rejected)
4. Run `gradlew setupDecompWorkspace` setup your Development Environment
5. Ensure any changes are tested prior to commiting to your new branch with `gradlew runClient`
 - 5a. If adding any dependencies for your changes, these MUST be approved prior to adding
6. Commit changes to your forked repo
7. Publish your new branch to your Github fork
8. Create a Pull-Request on GitHub
9. Wait for review
10. Squash commits for cleaner history

If you are only doing single file pull requests, GitHub supports using a quick way without the need of cloning your fork. Also read up about [syncing](https://help.github.com/articles/syncing-a-fork) if you plan to contribute on regular basis.

### English Text

`en_us.lang` is included in this repository, fixes to typos are welcome.

### Encoding

Files must be encoded as UTF-8.
