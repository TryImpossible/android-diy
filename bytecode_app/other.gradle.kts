class GreetingScriptPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.task("hi") {
            doLast {
                println("Script plugins : Hi from the GreetingScriptPlugin")
            }
        }
    }
}

// Apply the plugin
apply<GreetingScriptPlugin>()