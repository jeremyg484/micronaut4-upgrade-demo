## Upgrade to Micronaut 4 with OpenRewrite

In preparation for the eventual GA release of Micronaut 4, the Micronaut team has assembled a new set of [OpenRewrite recipes](https://github.com/openrewrite/rewrite-micronaut/blob/main/src/main/resources/META-INF/rewrite/micronaut3-to-4.yml) to make upgrading from Micronaut 3 quick and simple.

These recipes automate as much as possible from our previous ["Upgrade To Micronaut Framework 4"](https://micronaut.io/2023/05/09/upgrade-to-micronaut-framework-4-0-0/) post, and we have since incorporated other breaking changes not covered there. The recipes will be continually updated whenever we discover new upgrade steps.

### Upgrading a Gradle Application to Micronaut 4.

To upgrade a Micronaut 3 application that uses Gradle for its build, some minor additions can be made to your `build.gradle` file to enable the necessary OpenRewrite Gradle tasks for the upgrade. The necessary steps are:
1. Enable the OpenRewrite Gradle plugin
2. Add the `rewrite-micronaut` dependency
3. Activate the Micronaut 3-to-4 upgrade recipe
4. Execute the newly configured Gradle tasks

A fully configured Micronaut 3 example application can be found at https://github.com/jeremyg484/micronaut4-upgrade-demo

#### Enabling the OpenRewrite Gradle plugin
The OpenRewrite Gradle plugin `org.openrewrite.rewrite` must be added to the plugins section of a Micronaut 3 app `build.gradle` as below:
```groovy
plugins {
    id("org.openrewrite.rewrite") version("latest.release")
}
```
#### Adding the `rewrite-micronaut`dependency
Next add the `rewrite` module `org.openrewrite.recipe:rewrite-micronaut` to your dependencies section in `build.gradle`:
```groovy
dependencies {
    rewrite("org.openrewrite.recipe:rewrite-micronaut:2.0.1")
}
```
* Note that the current release of the rewrite-micronaut module is pinned to upgrading to Micronaut 4.0.0-M3.
#### Activating the Micronaut 3-to-4 upgrade recipe
Finally you must make the Micronaut 4 migration recipe active by adding the following to `build.gradle`
```groovy
rewrite {
    activeRecipe("org.openrewrite.java.micronaut.Micronaut3to4Migration")
}
```
#### Executing the OpenRewrite Gradle tasks
The OpenRewrite Gradle plugin provides a set of tasks for listing and executing recipes against a configured application.

To execute the configured Micronaut 4 migration recipe and see what actions it will take **without** making any actual changes to your source code, you can execute:
```shell
./gradlew rewriteDryRun
```
If the dry run executes without any errors and you are satisfied with the changes that the OpenRewrite recipe makes, you can then apply the changes to your application source code by executing:
```shell
./gradlew rewriteRun
```
And that's it, you now should have a fully functional Micronaut 4 application! 

Note that this is not the only way to execute the recipe for your application. If you prefer not to directly modify your build file, see the [alternate approach](https://docs.openrewrite.org/running-recipes/running-rewrite-on-a-gradle-project-without-modifying-the-build) suggested by OpenRewrite.

If you encounter any issues in applying the recipe to your own application, please [raise an issue here](https://github.com/openrewrite/rewrite-micronaut/issues), and contributions via [pull request](https://github.com/openrewrite/rewrite-micronaut/pulls) are always welcome.
